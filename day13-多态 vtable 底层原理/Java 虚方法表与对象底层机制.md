# Java 虚方法表与对象底层机制

> 本文汇总 Java 侧的底层机制：`super` 的本质、虚方法表 (vtable)、类型指针 (Klass Pointer)、构造/终结期的多态行为、对象销毁与 GC。
> C++ 侧内容见配套文件《[[C++ 虚函数与对象底层机制]]》。
> 两种语言的逐项对比见《[[Java-vs-C++ 对比]]》。

---

## 一、super 关键字在底层干了什么？

### 1. super 的本质

`super` **不是一个真正的对象引用**，它只是一个**编译器关键字**，用来告诉编译器："请从父类的作用域开始查找成员"。

底层来看，Java 对象在内存中只有 **一个 `this` 引用**，并没有一个独立的 "super 对象"。父类的成员是直接嵌入在子类对象内存布局中的。

### 2. 对象内存布局

```
SharPei 对象在堆内存中：
┌─────────────────────────┐
│  对象头 (Mark Word)      │
├─────────────────────────┤
│  类型指针 → SharPei.class│
├─────────────────────────┤
│  Dog 继承的字段          │  ← 父类字段
│  - name                 │
│  - age                  │
├─────────────────────────┤
│  SharPei 自己的字段      │  ← 子类字段
└─────────────────────────┘
```

父类的字段和子类字段在 **同一个对象** 里，`super` 和 `this` 指向的是**同一块内存地址**。

### 3. super.eat() 编译后的字节码

源码 `super.eat();` 经 javac 编译后大致是：

```
aload_0                              // 把 this 压入操作数栈
invokespecial Dog.eat:()V            // 特殊调用：直接调用 Dog 类的 eat 方法
```

**关键点**：
- `aload_0` 加载的是 **this**（不是什么 super 对象）
- 调用指令是 **`invokespecial`**，不是 `invokevirtual`

### 4. invokespecial vs invokevirtual

| 指令 | 用途 | 是否动态绑定 |
|------|------|-------------|
| `invokevirtual` | 普通方法调用 `this.eat()` | ✅ 运行时根据对象实际类型查方法表（多态） |
| `invokespecial` | `super.xxx()`、构造方法、私有方法 | ❌ 编译期就确定调用哪个类的方法 |

这就是为什么 `super.eat()` **能绕过多态**，强制调用父类版本：

- 普通的 `this.eat()` 会通过虚方法表（vtable）查找最终重写版本
- `super.eat()` 用 `invokespecial` **直接定位到父类方法的符号引用**，跳过多态查找

### 5. super(...) 调用父类构造器

构造方法里第一行的 `super()` 底层也是 `invokespecial`：

```
aload_0
invokespecial Dog."<init>":()V       // 调用父类构造器初始化父类字段
```

它的作用是**在同一个对象上**初始化父类继承下来的字段部分。

### 小结

| 表象 | 底层真相 |
|------|---------|
| `super` 像是另一个对象 | 实际还是 `this`，只是一个编译器标记 |
| `super.eat()` 调用父类方法 | 编译为 `invokespecial`，跳过虚方法表 |
| `super(...)` 调用父类构造器 | 在同一对象上执行父类的 `<init>` |
| 父类字段放在哪 | 嵌入在子类对象内存布局的前部 |

一句话：**`super` 只是编译器的"语法糖"，用来生成 `invokespecial` 指令，强制使用父类的方法绑定，而对象本身始终只有一个 `this`。**

---

## 二、虚方法表 (vtable)

JVM（HotSpot）也使用虚方法表实现多态，机制与 C++ 类似，但更彻底——因为 Java 中**除了 `static`、`private`、`final` 方法外，所有方法默认都是虚方法**（C++ 需要显式 `virtual`）。

### 1. 为什么需要虚方法表？

```java
Animal a = new Dog();
a.eat();   // 调用的是 Dog.eat()，不是 Animal.eat()
```

编译期只知道 `a` 的**静态类型**是 `Animal`，但运行时必须调用**实际类型** `Dog` 的方法。答案：**通过虚方法表 (vtable) 做 O(1) 查找。**

### 2. vtable 的结构

每个被 JVM 加载的类（在方法区/元空间中的 `InstanceKlass` 结构）都持有一张 vtable：

```
Animal 类的 vtable:              Dog 类的 vtable（继承 Animal）:
┌─────┬──────────────────┐      ┌─────┬──────────────────────┐
│索引 │ 方法指针          │      │索引 │ 方法指针              │
├─────┼──────────────────┤      ├─────┼──────────────────────┤
│  0  │ Animal::eat()    │      │  0  │ Dog::eat()    ← 覆盖 │
│  1  │ Animal::sleep()  │      │  1  │ Animal::sleep()      │
│  2  │ Object::toString │      │  2  │ Dog::toString() ← 覆盖│
│  3  │ Object::hashCode │      │  3  │ Object::hashCode()   │
│  …  │ …                │      │  4  │ Dog::bark()    ← 新增│
└─────┴──────────────────┘      └─────┴──────────────────────┘
```

**关键规则**：
1. **子类 vtable 复制父类 vtable 的布局**（索引位置不变）
2. **重写的方法替换对应槽位的指针**
3. **新增的方法追加到末尾**

这样：**同一个方法在父子类的 vtable 中索引相同**，可以直接按下标取，无需查找。

### 3. 对象如何找到自己的 vtable？

```
Dog 对象在堆中：
┌──────────────────┐
│ Mark Word        │  ← 锁信息、GC 信息
├──────────────────┤
│ Klass Pointer    │──→ Dog 类的 InstanceKlass ──→ vtable
├──────────────────┤
│ 实例字段          │
└──────────────────┘
```

每个对象头里有一个 **Klass Pointer**（类型指针，64 位系统压缩后通常 4 字节），指向方法区中该类的元数据，vtable 就挂在元数据上。

### 4. invokevirtual 的执行流程

当 JVM 执行 `a.eat()` 这条字节码：

```
aload_1                       // 把 a 压入栈
invokevirtual Animal.eat:()V  // 注意：符号引用是 Animal.eat
```

运行时步骤：

```
1. 从栈顶弹出对象引用 a
2. 通过 a 的 Klass Pointer 找到实际类（Dog）的 InstanceKlass
3. 在 Dog 的 vtable 中按"eat 方法的固定索引"取出方法指针
4. 跳转执行该方法
```

**核心优化**：编译期就能确定 `eat` 在 vtable 中的**索引**（比如索引 0），运行时只需一次内存访问 + 一次跳转，几乎是常数时间。

```
方法地址 = obj->klass->vtable[eat_index]
```

### 5. 哪些方法不进 vtable？

JVM 对以下方法使用**静态绑定**（编译期直接确定地址），不放入 vtable：

| 方法类型 | 调用指令 | 原因 |
|---------|---------|------|
| `static` 方法 | `invokestatic` | 不依赖对象 |
| `private` 方法 | `invokespecial` | 不能被重写 |
| `final` 方法 | `invokevirtual`（但可优化）| 不能被重写 |
| 构造方法 `<init>` | `invokespecial` | 不能被重写 |
| `super.xxx()` | `invokespecial` | 显式指定父类版本 |

这就是把方法标记为 `final` 能带来性能提升的底层原因——JIT 可以**内联**它。

### 6. 接口方法：itable（接口方法表）

接口方法不能直接用 vtable，因为：
- 一个类可以实现多个接口
- 不同类实现同一接口时，方法在各自 vtable 中的索引可能不同

JVM 用单独的 **itable (interface method table)** 处理：

```
invokeinterface List.add:(Object)Z
    ↓
1. 拿到对象的 Klass
2. 在 itable 中线性查找 List 接口对应的子表
3. 在子表中按索引取方法指针
```

`invokeinterface` 比 `invokevirtual` **慢一点**，因为多了一步"找接口对应的子表"。不过 JIT 会用**内联缓存 (Inline Cache)** 优化常见调用点，几乎追平 vtable。

### 7. JIT 的"去虚化 (Devirtualization)"

JVM 比 C++ 更激进——HotSpot 会根据运行时数据做优化：

```java
for (int i = 0; i < 10000; i++) {
    a.eat();   // 如果观察到 99% 的调用 a 都是 Dog
}
```

JIT 会生成类似这样的优化代码：

```
if (a.klass == Dog.class) {
    // 直接内联 Dog::eat() 的代码，完全省掉 vtable 查表
} else {
    // 兜底：走正常 invokevirtual
}
```

这叫 **类型守卫 + 内联缓存**，是 Java 经常能跑赢 C++ 虚函数调用的原因之一。

### 小结

| 概念 | 作用 |
|------|------|
| **vtable** | 类的虚方法表，每个槽位是方法指针，子类继承并覆盖父类槽位 |
| **Klass Pointer** | 对象头里指向类元数据的指针，是找到 vtable 的入口 |
| **invokevirtual** | 通过 vtable 索引动态分派，实现多态 |
| **invokespecial** | 不查 vtable，编译期绑定（`super`/构造器/private） |
| **itable** | 处理接口方法分派，比 vtable 多一层查找 |
| **JIT 去虚化** | 通过类型剖析把虚调用优化成直接调用 + 内联 |

**一句话**：Java 的多态 = `Klass Pointer` + `vtable 索引固定` + `invokevirtual 一次查表`，再叠加 JIT 的运行时优化。

---

## 三、类型指针 (Klass Pointer) 的设置时机

**结论：Klass Pointer 在 `new` 指令时就设置完成，先于任何构造函数调用——之后永远不变。**

### 1. 对象创建的完整阶段

```
阶段 1：分配内存（裸内存，全是零）
阶段 2：设置类型指针（让对象"知道自己是谁"）
阶段 3：字段初始化为默认值
阶段 4：执行构造函数体
阶段 5：对象可用
```

类型指针在阶段 2 设置，构造函数体在阶段 4 才开始执行——所以类型指针确实"先于构造函数"。

### 2. `new` 字节码

在 Java 中，`new Child()` 实际上是**两条**字节码：

```
new           #2   // class Child       ← 分配 + 设 Klass Pointer
dup                                     ← 复制引用
invokespecial #3  // Child.<init>:()V   ← 调用构造器
astore_1                                ← 存到局部变量 c
```

`new` 指令的执行过程：

```
1. 在 JVM 元空间中找到 Child 类（如未加载则加载）
2. 在堆中分配 Child 类大小的内存
3. 把整块内存清零（所有字段 = 默认值）
4. 设置对象头：
   ├─ Mark Word（GC、锁信息）
   └─ Klass Pointer → Child 类元数据   ← ⚠️ 这一步就设置了类型指针
5. 把对象引用压入操作数栈
```

**关键点**：在 `new` 指令执行完后，对象已经"是 Child 类型"了，但构造函数还没开始执行。在 `new` 与 `<init>` 这两条指令之间，对象处于"有类型但未构造"的状态。

### 3. 验证：构造器内 getClass() 返回的是最终类型

```java
class Parent {
    public Parent() {
        // ⚠️ 此时 this 已经是 Child 类型！
        System.out.println(this.getClass());   // 输出: class Child
    }
}
class Child extends Parent {
    public Child() { super(); }
}
```

即使在 `Parent` 的构造器内部，`this.getClass()` 返回的也是 `Child`——因为 Klass Pointer 在 `new` 时就指向 Child 了，从未改变。这也直接解释了"构造期多态陷阱"的根源。

### 4. 为什么 Java 选择"立即锁定类型"？

- 因为 Java 有 GC，GC 在任何时刻都可能扫描对象
- 如果 Klass Pointer 不正确，GC 就无法知道对象大小、字段布局
- 反射、`getClass()`、`instanceof` 也都依赖它
- 所以 Java 选择：**对象一诞生（`new`），类型就锁定**

> 一个常见误解："构造函数负责设置类型指针"。**错误**——类型指针由 `new` 字节码指令在调用 `<init>` **之前**设置。当你写的构造函数代码第一行开始执行时，类型指针已经被设置好了。

---

## 四、构造期多态陷阱

### 1. 一个真实陷阱

```java
class Parent {
    public Parent() {
        System.out.println("Parent 构造开始");
        init();   // 调用一个会被子类重写的方法
        System.out.println("Parent 构造结束");
    }
    public void init() {
        System.out.println("Parent.init()");
    }
}

class Child extends Parent {
    private String name = "小明";
    private int age;

    public Child() {
        super();
        this.age = 18;
    }
    @Override
    public void init() {
        System.out.println("Child.init(), name=" + name + ", age=" + age);
    }
}

new Child();
```

**输出**：
```
Parent 构造开始
Child.init(), name=null, age=0     ← ⚠️ name 居然是 null！
Parent 构造结束
```

### 2. 为什么会这样？

执行顺序：

```
1. new Child()
2. 调用 Child 构造器，第一行隐式 super()
3. 进入 Parent 构造器
4. Parent 构造器调用 init()
   → 因为 invokevirtual 走 vtable
   → 对象的 Klass Pointer 已经指向 Child
   → 所以调用的是 Child.init()！
5. 但此时 Child 的字段还没初始化：name 还是 null，age 还是 0
6. Parent 构造器结束
7. 回到 Child 构造器，才开始执行字段初始化和构造体
```

**核心矛盾**：
- 多态是按"对象实际类型"分派的（Klass Pointer 一开始就指向 Child）
- 但子类字段的初始化在父类构造器**之后**才执行
- 结果：在父类构造器里调用的子类方法，看到的是**未初始化的子类字段**

### 3. 内存视角

```
new Child() 的瞬间，堆内存状态：
┌────────────────────────┐
│ Klass Pointer → Child  │  ← 类型指针一开始就指向 Child
├────────────────────────┤
│ Parent 字段 = 默认值     │
├────────────────────────┤
│ Child 字段:            │
│   name = null          │  ← 还没初始化！
│   age = 0              │
└────────────────────────┘
        ↓
父类构造器执行 init() → 走 vtable → 找到 Child.init() → 访问 null 字段
```

### 4. 字节码验证

```java
public Child() {
    super();           // invokespecial Parent.<init>
    this.name = "小明"; // 注意：字段初始化在 super() 之后
    this.age = 18;
}
```

字节码顺序揭示了真相：**`super()` 在子类字段赋值之前执行**。

### 5. 字段初始化也算在构造期

```java
class Parent {
    private int x = readX();   // 字段初始化也算在构造期
    public Parent() {
        System.out.println("Parent ctor, x=" + x);
    }
    public int readX() { return 100; }
}
class Child extends Parent {
    private int factor = 10;
    @Override
    public int readX() { return factor; }   // 此时 factor 是 0！
}
new Child();   // 输出：Parent ctor, x=0
```

执行顺序：`new` 设好 Klass Pointer → 进 `Parent.<init>` → 执行 `x = readX()` → 走 vtable 调到 `Child.readX()` → 返回 `factor`，但 `factor` 还是 0 → `x = 0`。

### 6. 最佳实践：永远不要在构造函数中调用可被重写的方法

```java
// ❌ 错误示范
class Parent {
    public Parent() { init(); }   // 这会调用子类的重写版本
    public void init() { ... }
}

// ✅ 做法 1：把方法声明为 final
class Parent {
    public Parent() { init(); }
    public final void init() { ... }  // 不能被重写，安全
}

// ✅ 做法 2：声明为 private
class Parent {
    public Parent() { init(); }
    private void init() { ... }   // 子类无法重写，invokespecial 调用
}

// ✅ 做法 3：要求外部显式调用
class Parent {
    public Parent() { /* 不调用 init */ }
    public void init() { ... }
}
Child c = new Child();
c.init();   // 等对象完全构造好后再调用
```

这也是 IDEA 会对"构造器中调用可重写方法"发出警告的原因：`Overridable method call in constructor`。

### 7. 防御性编码清单

1. 构造器调用的方法，是不是 `private` / `final` / `static`？
2. 如果不是，子类重写后会不会访问未初始化字段？
3. 能否把初始化逻辑挪到**工厂方法**里，对象构造完成后再调用？

---

## 五、对象终结时 Klass Pointer 的行为

### 1. Klass Pointer 在整个对象生命周期都不变

```
对象创建 [new]      └─ Klass Pointer → Child   ⚠️ 此时锁定
对象使用            └─ Klass Pointer → Child
不可达              └─ Klass Pointer → Child
进入 finalize() 队列 └─ Klass Pointer → Child
finalize() 执行     └─ Klass Pointer → Child   ⚠️ 仍然是 Child
GC 回收内存         └─ Klass Pointer 随内存一起释放
```

**Java 永远不会"回退" Klass Pointer**——因为：
- 没有"逆向构造链"的概念，`finalize()` 不会沿继承链自动调用
- GC 需要 Klass Pointer 来知道对象大小、字段布局以正确扫描和回收
- 反射、`getClass()` 在任何时刻都必须返回真实类型

### 2. finalize() 中的类型行为

```java
class Parent {
    @Override
    protected void finalize() {
        System.out.println("Parent.finalize, type=" + getClass().getSimpleName());
    }
}
class Child extends Parent {
    @Override
    protected void finalize() {
        System.out.println("Child.finalize, type=" + getClass().getSimpleName());
        // 不会自动调用 super.finalize()！
    }
}
new Child();
System.gc();
System.runFinalization();
```

**输出**：`Child.finalize, type=Child`

- 只调用 `Child.finalize()`，不会自动链式调用 `Parent.finalize()`
- 全程 `getClass()` 返回 `Child`
- 没有"vptr 回退"这种事

### 3. 现代 Java：Cleaner 与 PhantomReference

`finalize()` 自 Java 9 起被标记为 deprecated，推荐使用 `Cleaner`：

```java
class Resource implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    private final Cleaner.Cleanable cleanable;

    public Resource() {
        // ⚠️ State 不能持有外层 Resource 的引用，否则永远不会被回收
        this.cleanable = cleaner.register(this, new State());
    }
    private static class State implements Runnable {
        @Override public void run() { /* 清理 */ }
    }
}
```

在 Cleaner 的清理回调中：原对象已经不可达，Cleaner 拿到的只是 State；原对象的 Klass Pointer 仍然有效，直到 GC 真正回收内存那一刻；但用户代码已无法访问原对象——这是 Java 设计上的安全保证。

---

## 六、对象销毁与垃圾回收

### 1. 根本区别：没有"析构"，只有"垃圾回收"

Java **没有析构函数**。对象的销毁由 **垃圾回收器（GC）** 异步、非确定地完成。你**无法知道也无法控制**对象何时被回收。

```java
{
    MyObject obj = new MyObject();  // 堆上分配
}  // 作用域结束 → obj 引用失效，但对象不会立即销毁！
```

离开作用域只意味着**引用变量失效**，对象本身仅成为"GC 可达性分析中的不可达对象"，等待将来某次 GC。

### 2. 可达性分析（Reachability Analysis）

JVM 通过 **GC Roots** 判断对象是否存活，而非引用计数：

```
GC Roots 包括：
  - 栈帧中的局部变量、参数
  - 静态字段
  - JNI 引用
  - 活跃线程、锁对象等

从 Roots 出发做可达性遍历（标记），不可达的对象 = 垃圾，可被回收
```

这从根本上解决了引用计数无法处理的 **循环引用** 问题：A↔B 互相引用但都从 Roots 不可达，照样被回收。

### 3. 一次 GC 中对象销毁的底层流程

以现代分代收集器（如 G1）为例：

```
1. 标记（Mark）：从 GC Roots 遍历，标记所有可达对象
2. 处理引用：处理 SoftReference / WeakReference / PhantomReference
3. （若有 finalizer）将待终结对象放入 finalization 队列
4. 清除/复制（Sweep/Copy/Compact）：
   - 标记-清除：把死对象内存标记为空闲
   - 标记-复制：把活对象复制到另一区域，整片回收旧区域
   - 标记-整理：移动活对象使其紧凑，回收尾部
```

关键洞察：在**复制式收集**中，"死对象"根本**不被触碰**——GC 只搬走活对象，整块旧内存直接视为可用。所以**销毁一个对象的成本可能为零**（不像 C++ 每个对象都要执行析构 + free）。

### 4. finalize() —— 已废弃的"伪析构"

```java
@Override
protected void finalize() throws Throwable {
    // 对象被回收前可能被调用——但不保证一定调用！
}
```

底层流程的问题：

```
有 finalize() 的对象在 GC 时：
  1. 第一次 GC 发现不可达 → 不能立即回收
  2. 放入 ReferenceQueue，由低优先级 Finalizer 线程异步执行 finalize()
  3. finalize() 中对象可能"复活"（重新被引用）
  4. 至少需要两次 GC 周期才能真正回收
```

问题清单：**不保证执行、执行时机不定、可能让对象复活、拖慢 GC、单线程 Finalizer 易成瓶颈**。因此 `finalize()` 在 Java 9 被标记 `@Deprecated`，Java 18 进一步推进移除计划。

### 5. 现代替代方案

**try-with-resources（最接近 C++ 的 RAII）**

```java
try (FileInputStream fis = new FileInputStream("f")) {
    // 使用
}  // 编译器自动插入 fis.close()，确定性，类似 C++ 栈析构
```

它是**编译期语法糖**，编译器展开为 try-finally：

```java
FileInputStream fis = new FileInputStream("f");
try {
    // 使用
} finally {
    if (fis != null) fis.close();
}
```

**Cleaner —— 基于 PhantomReference 的兜底清理**

```java
private static final Cleaner cleaner = Cleaner.create();
// 对象被 GC 后，Cleaner 线程执行清理动作（不复活、不拖慢主 GC）
```

相比 `finalize()`：不会复活对象、不拖慢主 GC、机制更可控。

> 因为 GC 只擅长回收内存，对其他资源（文件句柄、socket、锁）既不及时也不可靠——所以 Java 中"非内存资源"必须用 `try-with-resources` 显式管理。
