# C++ 虚函数与对象底层机制

> 本文汇总 C++ 侧的底层机制：vtable / vptr 的初始化、类型指针在构造/析构期的"逐层升降"、虚构造函数惯用法、虚析构函数、对象销毁与栈展开。
> Java 侧内容见配套文件《[[Java 虚方法表与对象底层机制]]》。
> 两种语言的逐项对比见《[[Java-vs-C++ 对比]]》。

---

## 一、vtable / vptr 的初始化

### 1. vtable 是何时、由谁初始化的

关键结论：**vtable 不是运行时生成的，而是编译期就由编译器静态构造好的。**

它作为一块只读静态数据，在编译/链接阶段就被放进可执行文件（如 `.rodata` 段）。程序加载时随之进入内存、地址确定且不可变。运行时**从不"生成" vtable**，只是让 vptr 去**指向**它。

**vtable vs vptr —— 两者生命周期完全不同：**

| | vtable | vptr |
|---|---|---|
| 何时产生 | 编译期（静态生成） | 运行期（构造时设置） |
| 数量 | 每个**类**一份 | 每个**对象**一份 |
| 存放位置 | 只读数据段（`.rodata`） | 对象内存里（通常在对象开头 8 字节） |
| 初始化者 | 编译器 / 链接器 | 构造函数 |

所以"设置 vptr 指向本类 vtable"时，那张 vtable **早已存在**，构造函数只是把对象里的 vptr 这个指针填上 vtable 的地址而已。

### 2. 编译器如何排布 vtable

对每个有虚函数的类，编译器扫描该类**最终生效**的虚函数版本，按声明顺序排成函数指针表，**槽位顺序和索引在编译期就固定死了**：

```cpp
class Base {
public:
    virtual void f();   // 槽位 0
    virtual void g();   // 槽位 1
};
class Derived : public Base {
public:
    void g() override;  // 覆盖槽位 1
    virtual void h();   // 槽位 2
};
```

编译期生成两张表（概念上）：

```
vtable for Base:        vtable for Derived:
  [0] &Base::f            [0] &Base::f       ← 继承，未覆盖
  [1] &Base::g            [1] &Derived::g    ← 覆盖，换成派生版
                          [2] &Derived::h    ← 新增
```

`g()` 永远在索引 1。虚调用 `obj->g()` 编译成大致：

```cpp
(*(obj->vptr[1]))(obj);   // 取 vptr 指向的表，跳到第 1 个槽位
```

动态分派的全部秘密：**一次查表 + 一次间接跳转**。槽位号编译期定死，具体跳到谁取决于 vptr 指向哪张表（运行期定）。

> 补充：在 Itanium ABI 里，vtable 槽位 0 之前还藏着 `offset_to_top` 和指向 `type_info` 的指针，这是 `dynamic_cast` 和 `typeid`（RTTI）的实现基础。

### 3. 完整时间线

```
编译期：     编译器为每个多态类静态构造 vtable，写入目标文件只读段
链接/加载：  vtable 随程序加载到内存，地址确定且不可变
运行期(构造)：
  1. 分配内存
  2. vptr = &本类vtable      ← 只是写一个已存在的地址
  3. 执行构造函数体
运行期(虚调用)： obj->vptr[槽位号] → 间接跳转
```

---

## 二、类型指针 (vptr) 的设置时机：构造函数序言

**结论：vptr 在每个构造函数的"序言 (prologue)"中设置，先于该构造函数的用户代码——但会随构造阶段动态调整。**

一个 C++ 对象的创建（栈上 `Child c;` 或堆上 `new Child()`），编译器实际生成类似这样的伪代码：

```cpp
void* mem = allocate(sizeof(Child));   // 1. 分配内存，此时 mem 还是裸内存

// 2. 进入构造器链
Child_constructor(mem) {
    Parent_constructor(mem) {
        // 进入 Parent 构造体前的"序言代码"：
        mem->vptr = &Parent_vtable;    // ⚠️ 设置 vptr 指向 Parent
        mem->parent_field = ...;       // Parent 字段初始化
        // 执行 Parent() { ... }，期间调用虚函数 → 走 Parent_vtable
    }
    // Parent 构造完成后的"序言代码"：
    mem->vptr = &Child_vtable;         // ⚠️ vptr 改为指向 Child
    mem->child_field = ...;            // Child 字段初始化
    // 执行 Child() { ... }，期间调用虚函数 → 走 Child_vtable
}
```

### 验证 vptr 逐层改写

```cpp
class Parent {
public:
    Parent() { std::cout << typeid(*this).name() << "\n"; }   // 输出: Parent
};
class Child : public Parent {
public:
    Child() { std::cout << typeid(*this).name() << "\n"; }    // 输出: Child
};
int main() { Child c; }
```

**输出**：
```
Parent
Child
```

`typeid(*this)` 依赖 vptr 找到 RTTI，所以在 Parent 构造器中显示 Parent，在 Child 构造器中显示 Child。**这直接证明 vptr 在每个构造器入口被改写。**

### 栈对象 vs 堆对象

无论栈上还是堆上的 C++ 对象，vptr 的设置时机都一样——都发生在构造函数序言中，**早于用户构造函数体**：

```cpp
Child c;                  // 栈：编译器在栈帧建立后调用构造器
Child* p = new Child();   // 堆：operator new 分配 + 调用构造器
```

> vptr 仅服务于虚函数分派，按需调整：C++ 没有 GC，对象的元信息是编译期固定的，vptr 只用于"该调用哪个虚函数"。构造期间子类还不完整，调用子类虚函数会出错，所以 C++ 选择**vptr 跟随"当前正在构造的层次"动态变化**。

---

## 三、为什么构造函数不能是虚的

```cpp
class Base { public: virtual Base() {} };  // 编译错误！
```

常见困惑：构造函数体明明在 vptr 设好之后才执行，为什么还不能虚？

**关键：把"分派"和"执行"分开看。** 一次虚调用其实是两步：

```
① 分派(dispatch)：读对象 vptr → 查表 → 决定跳到哪个函数   ← virtual 影响的就是这一步
② 执行：跳进去，跑函数体                                  ← "vptr 已就绪"指的是这一步
```

**virtual 的作用全在 ① 这一步，而 ① 必须发生在进入函数之前。**

再看谁设置 vptr —— **是构造函数自己（prologue）干的**。所以"设 vptr"不是"构造函数之前"，而是"构造函数一开始做的第一件事"。代入"虚构造"就死循环了：

```
想虚分派地调用构造函数：
  → 需要先读 vptr 才能决定调哪个构造函数   (① 分派)
  → 但 vptr 要靠这个构造函数的序言来设      (② 还没开始)
  → 鸡生蛋：要 ② 先发生才能 ①，要 ① 先发生才能 ②
```

对比普通虚函数 `obj->f()` 为何没问题：对象早已构造完，vptr 早就有效，进入 `f` 前直接读 vptr 选函数即可。差别就在：**普通虚调用时对象已存在、vptr 已就绪；而构造时对象正"从无到有"，vptr 此刻还没人设。**

**语义角度**：虚分派的定义是"按对象的运行时类型选出对应 override"。但构造那一刻对象类型还没确立，而且调用方永远显式写明了具体类型（`new Derived()` / `Derived d;`），根本没有"运行时选哪个"的余地，virtual 自然无从谈起。

> **推论**：在构造/析构函数内部调用虚函数不会动态分派，只调用当前类的版本（此时 vptr 指向当前类 vtable）。这是经典陷阱。底层原因：基类构造函数执行时 vptr 先被设成基类 vtable，等派生类构造体执行时才改写成派生类 vtable —— 构造链是"逐层抬升 vtable"，析构则反向"逐层下降"。
>
> **对照**：析构函数可以、且常常应该是虚的。因为析构时对象**完整存在、vptr 完全有效**，通过基类指针 `delete pBase` 正需要靠 vptr 找到正确的派生类析构。这与构造恰好相反——构造是"还没有 vptr"，析构是"vptr 还在"。

---

## 四、构造期多态：vptr 逐层抬升

C++ 在构造期间**直接关闭"派生类方向"的虚函数分派**——调用的是当前正在构造的那一层的版本。

```cpp
class Parent {
public:
    Parent() {
        std::cout << "Parent 构造\n";
        init();   // 在 C++ 里，这里调用的是 Parent::init()！
    }
    virtual void init() { std::cout << "Parent::init()\n"; }
};
class Child : public Parent {
public:
    void init() override { std::cout << "Child::init()\n"; }
};
int main() { Child c; }
```

**输出**：
```
Parent 构造
Parent::init()    ← 不是 Child::init()！
```

机制——构造的每一阶段动态修改 vptr：

```
执行 Child c 的过程：

阶段 1：进入 Parent 构造器
    ┌──────────────────────────┐
    │ vptr → Parent 的 vtable  │  ← vptr 暂时指向 Parent！
    │ Parent 字段              │
    │ Child 字段（未构造）      │
    └──────────────────────────┘
    此时 init() 通过 vtable 找到 Parent::init()

阶段 2：Parent 构造器结束，进入 Child 构造器
    ┌──────────────────────────┐
    │ vptr → Child 的 vtable   │  ← vptr 改为指向 Child
    │ Child 字段（开始构造）    │
    └──────────────────────────┘

阶段 3：Child 构造器结束，init() 才会调用 Child::init()
```

C++ 的设计者认为：在父类构造期间，**子类对象"还不存在"**——调用子类方法是语义错误。所以 C++ 通过动态调整 vptr 来强制单态。

---

## 五、析构期：vptr 逆向回退

**核心结论**：和构造对称，析构期间 vptr 会"逆向回退"——进入 `~Child` 时是 Child，进入 `~Parent` 时变回 Parent。

### 1. 析构顺序：与构造完全相反

```cpp
class Parent {
public:
    Parent()  { std::cout << "Parent ctor\n"; }
    virtual ~Parent() { std::cout << "Parent dtor, type=" << typeid(*this).name() << "\n"; }
    virtual void hello() { std::cout << "Parent::hello\n"; }
};
class Child : public Parent {
public:
    Child()  { std::cout << "Child ctor\n"; }
    ~Child() override {
        std::cout << "Child dtor, type=" << typeid(*this).name() << "\n";
        hello();   // ⚠️ 调用虚函数
    }
    void hello() override { std::cout << "Child::hello\n"; }
};
int main() {
    Parent* p = new Child();
    delete p;
}
```

**输出**：
```
Parent ctor
Child ctor
Child dtor, type=Child       ← 进入 ~Child 时 vptr 已指向 Child
Child::hello                 ← 虚调用走 Child
Parent dtor, type=Parent     ← 进入 ~Parent 时 vptr 已"回退"为 Parent
```

### 2. 编译器为析构函数生成的伪代码

```cpp
Child_destructor(Child* this) {
    // 1. 此时 vptr 已是 Child（进入前不变）
    // 2. 执行用户写的 ~Child() { ... }

    // 3. 用户代码结束后，"尾声代码"：
    this->vptr = &Parent_vtable;   // ⚠️ vptr 回退到 Parent
    // 4. 销毁 Child 自己的字段（逆序）
    // 5. 调用父类析构
    Parent_destructor(this);
}
Parent_destructor(Parent* this) {
    // 进入时 vptr 已是 Parent
    // 执行用户写的 ~Parent() { ... }，销毁 Parent 字段，内存释放（如果是 delete）
}
```

### 3. vptr 在析构期的完整时间轴

```
[t1] delete p; 触发 ~Child()      vptr → Child   typeid(*this)==Child
[t2] ~Child() 用户代码执行         vptr → Child   虚调用走 Child::xxx
[t3] ~Child() 用户代码结束         vptr → Parent  ⚠️ 编译器尾声代码改写；Child 字段销毁
[t4] ~Parent() 用户代码执行        vptr → Parent  typeid(*this)==Parent；虚调用走 Parent::xxx
[t5] ~Parent() 结束，对象内存可释放  vptr 此时已无意义
```

### 4. 析构期的虚函数陷阱（对称于构造期）

```cpp
class Parent {
public:
    virtual ~Parent() {
        cleanup();   // ⚠️ 永远调用 Parent::cleanup，即使 Child 重写
    }
    virtual void cleanup() { /* Parent's */ }
};
class Child : public Parent {
public:
    void cleanup() override { /* Child's，可能依赖 Child 字段 */ }
};
```

进入 `~Parent` 时 vptr 已被改回 Parent——这其实是**安全设计**：因为 Child 字段此刻已被销毁，再去走 `Child::cleanup` 会访问已销毁的字段。

---

## 六、对象析构的底层机制

### 1. 析构的触发时机（确定性）

C++ 的析构是 **确定性** 的，由对象的生命周期决定，编译器在编译期就插入了析构调用代码。

```cpp
{
    MyClass obj;        // 构造
    // ... 使用
}                       // 作用域结束，编译器自动插入 obj.~MyClass()
```

三种存储类型的析构时机：

| 存储类型 | 内存位置 | 析构时机 | 谁负责 |
|---------|---------|---------|--------|
| 自动（栈）| 栈 | 离开作用域 | 编译器自动插入 |
| 动态（堆）| 堆 | `delete` 调用 | 程序员手动 |
| 静态/全局 | 数据段 | 程序退出（`atexit`）| 运行时 |

### 2. 栈对象析构 —— 编译器插入代码

```cpp
// 编译器展开后
MyClass obj;
MyClass::MyClass(&obj);     // 构造调用
// ... 函数体 ...
MyClass::~MyClass(&obj);    // 编译器在所有出口插入析构
```

"所有出口"包括正常 return、break，以及异常抛出时的 **栈展开（stack unwinding）**。

### 3. 栈展开（Stack Unwinding）—— 异常安全的核心

异常抛出时，运行时需要"倒着"销毁从抛出点到 catch 点之间所有已构造的栈对象：

```cpp
void foo() {
    Resource a;          // 已构造
    Resource b;          // 已构造
    throw std::runtime_error("boom");  // b、a 被逆序析构
    Resource c;          // 未构造，不析构
}
```

底层实现（Itanium C++ ABI，Linux/macOS 通用）：

```
编译器为每个函数生成：
  1. .eh_frame 段：记录如何展开栈帧（CFI 信息）
  2. .gcc_except_table：记录 landing pad 和需要析构的对象

抛出异常时：
  __cxa_throw → _Unwind_RaiseException
    → 两阶段展开：
       Phase 1（搜索）：找到能 catch 的栈帧
       Phase 2（清理）：逐帧调用析构（执行 cleanup landing pad）
```

### 4. 析构函数内部的执行顺序（与构造严格相反）

```cpp
~Derived() {
    // 1. 执行你写的析构函数体
    // 2. 逆序析构所有非静态成员变量（编译器插入）
    // 3. 逆序析构所有直接基类（编译器插入）
    // 4. 如果是虚继承，析构虚基类
}
```

成员和基类析构由编译器自动插入，函数体只是第 1 步。

### 5. 内存释放本身

```cpp
operator delete(p);   // 默认调用全局 operator delete → 通常封装 free()
```

`free()` 把内存块还给堆分配器（glibc 的 ptmalloc/tcmalloc 等），更新空闲链表/bin 结构。内存通常不立即还给操作系统，而是留在进程堆中复用。

---

## 七、虚析构函数

虚析构核心目的：**保证通过基类指针 `delete` 派生类对象时正确调用派生类析构，避免资源泄漏。**

### 1. delete p 的底层动作

```cpp
Base* p = new Derived();
delete p;
```

`delete p` 底层做两件事：**先析构，后释放内存**。

```cpp
// 虚析构情况
p->vptr->[析构函数槽]();   // 通过 vtable 动态分派到 ~Derived()
operator delete(p);        // 释放内存
```

若析构函数不是虚的，`delete p` 静态绑定到 `~Base()`，`Derived` 成员永不被析构 → 资源泄漏（实为未定义行为）。

实现细节：编译器为多态类生成两个析构版本（Itanium ABI）：
- **complete object destructor (D1)**：析构对象本身
- **deleting destructor (D0)**：先 D1，再调用 `operator delete`

vtable 中的析构槽指向 D0。

### 2. 多态基类必须有虚析构（核心规则）

```cpp
class Base {
public:
    virtual ~Base() = default;   // 关键
};
class Derived : public Base {
    std::vector<int> bigData;
public:
    ~Derived() { /* 释放 bigData */ }
};

Base* p = new Derived();
delete p;   // 有虚析构 → 调用 ~Derived() 再 ~Base()，正确
            // 无虚析构 → 只调用 ~Base()，bigData 泄漏！（且是 UB）
```

判断准则：只要一个类**可能被作为基类**并**通过基类指针 delete**，析构就应当 `virtual`。

### 3. 常见应用场景

- **抽象基类 / 接口类**：`class IDevice { virtual ~IDevice() = default; virtual void read() = 0; };`
- **工厂模式**：`std::unique_ptr<IDevice> createDevice(...)` 返回基类智能指针，析构需虚析构正确销毁。
- **多态对象容器**：`std::vector<std::unique_ptr<Shape>>` 销毁时逐个 delete，依赖虚析构。
- **插件 / 模块系统**：`class IPlugin { virtual ~IPlugin() = default; virtual void run() = 0; };` 卸载插件时必须能正确析构。

### 4. 纯虚析构函数（特殊技巧）

想让类成为抽象类但又无其他合适纯虚函数时，可把析构声明为纯虚：

```cpp
class AbstractBase {
public:
    virtual ~AbstractBase() = 0;   // 纯虚 → 类变抽象
};
AbstractBase::~AbstractBase() { }  // 必须提供定义！派生类析构会调用它
```

这是少数"纯虚函数仍需提供实现体"的情况。

### 5. 何时**不要**用虚析构

| 情况 | 是否需要虚析构 | 原因 |
|------|--------------|------|
| 不打算被继承的类 | 否 | 加 vptr 浪费内存，破坏 POD 特性 |
| 值类型 / 不通过基类指针 delete | 否 | 没有多态删除场景 |
| 性能敏感的小对象（数学向量等）| 否 | vptr 通常增大对象 8 字节，破坏缓存友好性 |
| 作为多态基类被 delete | **是** | 否则资源泄漏 + UB |

`std::string`、`std::vector` 等标准库容器都没有虚析构，因为设计上不应被当多态基类继承。

> 折衷方案：基类不想被多态 delete 但允许继承，可把析构声明为 **protected 非虚**，从语法上禁止 `delete basePtr`。

---

## 八、深入：`Base* p = new Derived(); delete p;` 为什么"认为 p 是 Base"

### 1. 静态类型 vs 动态类型

```cpp
Base* p = new Derived();
```

| 类型种类 | 是什么 | 何时确定 | 谁知道 |
|---------|--------|---------|--------|
| **静态类型** | `Base*` | 编译期，由声明决定 | 编译器 |
| **动态类型** | `Derived` | 运行期 | 运行时（藏在对象里）|

`delete p` 是在**编译期**生成代码的，编译器那一刻**只拥有静态类型 `Base*`**，无法知道运行期实际指向什么。

### 2. 动态类型信息存在哪里

动态类型信息不在 `p` 指针变量里，而藏在对象内部的 **vptr**：

```
p (Base* 变量) ──→ ┌─────────────────────┐  ← 堆上实际对象
                   │ vptr ───────────────┼──→ Derived 的 vtable
                   │ Base 的成员          │       [ ~Derived 地址 ]
                   │ Derived 的成员       │       [ 其他虚函数  ]
                   └─────────────────────┘
```

- 非虚调用：不去读 vptr，直接按静态类型写死。
- 虚调用：先解引用 `p` 拿 vptr，再查表找真实析构。

本质：动态类型信息一直都在（在对象里），但只有虚函数机制生成的代码才会去读它。非虚析构时编译器以为对象是 `Base`，可能按 `sizeof(Base)` 处理；虚析构时通过 vtable 找到的 deleting destructor (D0) 知道完整对象大小，能正确释放整个 `Derived`。

### 3. 这是 C++ 规定的行为吗？—— 规定了什么，没规定什么

> 关键：C++ 标准规定的是 **语义（行为）**，不是 **实现机制**。

| 层面 | 内容 | 谁规定 |
|------|------|--------|
| 语义（what）| 虚→动态分派，非虚→静态分派 | **C++ 标准强制** |
| 机制（how）| vtable / vptr / 读对象头部指针 | 编译器实现自由 |

标准里从头到尾没有 "vtable"、"vptr" 这些词。标准只规定"虚函数按动态类型调用"这个**效果**，怎么实现是实现自由。虚表+虚指针只是主流编译器（GCC/Clang/MSVC）的**事实标准实现**（符合 Itanium C++ ABI 等约定）。

**重要修正：非虚析构 delete 派生对象是未定义行为。** 标准 `[expr.delete]` 规定：通过指针 `delete` 对象，若静态类型与动态类型不同，则静态类型必须是动态类型的基类，**且必须有虚析构函数**，否则 **行为未定义（UB）**。

```cpp
class Base { public: ~Base(){} };       // 非虚析构
class Derived : public Base { /*...*/ };
Base* p = new Derived();
delete p;   // ← 未定义行为，不是"定义好的只调 Base 析构"
```

"只调用 `~Base` 导致泄漏"只是 UB 最常见的一种表现，标准并未承诺这个结果——编译器理论上可以崩溃、损坏堆、看似正常运行，都属合规。

---

## 九、虚构造函数惯用法（Virtual Constructor Idiom）

C++ 没有真正的虚构造函数（见第三节），但可用虚函数返回新对象来模拟"多态地创建对象"。核心是 `clone()` 和 `create()`。

### 1. clone() —— 多态复制（原型模式 Prototype）

手里只有基类指针，但想复制出运行时真实类型的副本。

```cpp
class Shape {
public:
    virtual ~Shape() = default;
    virtual Shape* clone() const = 0;   // "虚拷贝构造"
    virtual void draw() const = 0;
};
class Circle : public Shape {
public:
    Shape* clone() const override { return new Circle(*this); }
    void draw() const override { /* ... */ }
};
class Square : public Shape {
public:
    Shape* clone() const override { return new Square(*this); }
    void draw() const override { /* ... */ }
};
void duplicate(const Shape* s) {
    Shape* copy = s->clone();   // 动态分派到正确的拷贝构造
}
```

典型用途：
- **深拷贝含多态成员的容器**：`std::vector<Shape*>` 复制时普通拷贝只复制指针（浅拷贝），必须用 `clone()` 逐个复制真实对象。
- **原型模式**：用配置好的原型对象克隆出多个实例。
- **撤销/快照（Undo/Snapshot）**：保存对象当前状态的副本。

### 2. create() —— 多态创建空白对象（工厂思想）

```cpp
class Shape {
public:
    virtual ~Shape() = default;
    virtual Shape* create() const = 0;  // "虚默认构造"
    virtual Shape* clone()  const = 0;  // 复制当前对象
};
class Circle : public Shape {
public:
    Shape* create() const override { return new Circle(); }     // 全新
    Shape* clone()  const override { return new Circle(*this); } // 副本
};
```

典型用途：
- **反序列化 / 工厂注册表**：读取类型 ID，从注册表找原型，`create()` 造对象再填充数据。
- **GUI 框架**：拖拽工具栏原型到画布，`create()` 生成新控件实例。

### 3. 现代 C++ 改进：返回智能指针

```cpp
class Shape {
public:
    virtual ~Shape() = default;
    virtual std::unique_ptr<Shape> clone() const = 0;
};
class Circle : public Shape {
public:
    std::unique_ptr<Shape> clone() const override {
        return std::make_unique<Circle>(*this);
    }
};
```

> 限制：协变返回类型（covariant return）对裸指针/引用有效，但 `unique_ptr<Circle>` 重写 `unique_ptr<Shape>` **不成立**（不同类模板实例，不算协变）。智能指针版本通常统一返回基类指针类型。
