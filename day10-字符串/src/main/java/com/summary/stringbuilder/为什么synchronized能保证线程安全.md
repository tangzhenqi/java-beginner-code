# 为什么加了 `synchronized` 就线程安全了？

要讲清这个问题，得先搞明白三件事：

1. **什么叫"线程不安全"**
2. **`synchronized` 到底做了什么**
3. **为什么它能解决问题**

---

## 一、先看"线程不安全"长什么样

假设两个线程同时往一个 `StringBuilder` 里 `append` 字符：

```java
StringBuilder sb = new StringBuilder();

// 线程 A
sb.append("A");

// 线程 B（同时执行）
sb.append("B");
```

你以为结果是 `"AB"` 或 `"BA"`，但**有可能是 `"A"`、`"B"`，甚至抛 `ArrayIndexOutOfBoundsException`**。

### 为什么？看 `append` 的简化源码

```java
public StringBuilder append(String str) {
    int len = str.length();
    ensureCapacity(count + len);              // ① 检查容量够不够
    str.getChars(0, len, value, count);       // ② 把字符复制到 value[count] 开始的位置
    count += len;                             // ③ 更新长度
    return this;
}
```

注意 `count += len` 这一步，它在 CPU 层面其实是 **3 个动作**：

1. 读取 `count` 当前值
2. 加上 `len`
3. 写回 `count`

### 两个线程交错执行的"灾难现场"

假设当前 `count = 0`，线程 A 和 B 同时执行 `append`，长度都是 1：

| 时刻 | 线程 A | 线程 B | count 实际值 |
|---|---|---|---|
| t1 | 读到 count=0 | | 0 |
| t2 | 把 'A' 写到 value[0] | | 0 |
| t3 | | 读到 count=0（还没被 A 改！） | 0 |
| t4 | | 把 'B' 写到 value[0]（**覆盖了 A！**） | 0 |
| t5 | 写回 count = 0+1 = 1 | | 1 |
| t6 | | 写回 count = 0+1 = 1 | 1 |

**结果**：`value = ['B', ...]`，`count = 1`，相当于只 append 了一个 "B"，A 的数据丢了。

这就是**线程不安全**——多个线程一起改共享变量时，操作交错导致数据错乱。

---

## 二、`synchronized` 到底做了什么？

`StringBuffer` 的 `append` 长这样：

```java
@Override
public synchronized StringBuffer append(String str) {  // ← 多了 synchronized
    toStringCache = null;
    super.append(str);
    return this;
}
```

`synchronized` 加在实例方法上，等价于：

```java
public StringBuffer append(String str) {
    synchronized (this) {        // ← 给"当前对象"加锁
        toStringCache = null;
        super.append(str);
        return this;
    }
}
```

**核心机制**：每个 Java 对象在 JVM 里都有一把"内置锁"（monitor lock）。`synchronized` 做了两件事：

1. **进入方法前**：必须先**拿到这把锁**，拿不到就**阻塞等待**
2. **方法结束后**：自动**释放锁**，让别的线程能拿

### 同样的场景，用 StringBuffer 重演

```java
StringBuffer sb = new StringBuffer();

// 线程 A：sb.append("A")
// 线程 B：sb.append("B")
```

| 时刻 | 线程 A | 线程 B |
|---|---|---|
| t1 | **拿到 sb 的锁**，进入方法 | 也想进入方法，但锁被占了，**阻塞等待** ⏸ |
| t2 | 读 count=0 | 还在等 |
| t3 | 写 value[0]='A' | 还在等 |
| t4 | count=1 | 还在等 |
| t5 | **释放锁**，返回 | |
| t6 | | **拿到锁**，进入方法 |
| t7 | | 读 count=1 |
| t8 | | 写 value[1]='B' |
| t9 | | count=2 |
| t10 | | 释放锁 |

**结果**：`value = ['A', 'B']`，`count = 2`，正确得到 `"AB"`。✅

**关键点**：两个线程对 `count`、`value` 的修改**不会再交错**了，它们必须**排队**一个接一个地执行——这就叫**互斥（mutual exclusion）**。

---

## 三、`synchronized` 提供的两大保证

线程安全其实需要三个保证，`synchronized` 一次性全包：

| 保证 | 含义 | synchronized 如何做到 |
|---|---|---|
| **原子性** | "读-改-写"这一连串操作不被打断 | 加锁后，整段代码只有一个线程在执行 |
| **可见性** | A 线程改的值，B 线程能立刻看到 | 释放锁时把数据**刷回主内存**；获取锁时从主内存**重新读取**（强制走主存，不能用 CPU 缓存的旧值） |
| **有序性** | 编译器/CPU 不会把锁内的代码乱序到锁外 | JMM 规则：锁内的指令不能重排到锁外 |

如果只解决原子性（比如只用一个标志位），可能因为**可见性**问题，B 线程看到的还是 A 的旧值——`synchronized` 同时解决了这两个问题，所以才能真正"线程安全"。

---

## 四、那为什么 StringBuilder 不加？不是更安全吗？

因为**加锁有代价**：

1. **性能开销**：每次进出方法都要争抢锁、阻塞、唤醒
2. **大多数场景用不上**：字符串拼接 90% 都在**单线程**里完成（比如方法内的局部变量）

所以 Java 的设计是：

- **StringBuffer**（JDK 1.0）：加锁，线程安全，慢
- **StringBuilder**（JDK 1.5 后补的）：不加锁，线程不安全，**单线程下快 ~30%**

**让你自己根据场景选**：单线程用 StringBuilder，多线程共享才用 StringBuffer。

---

## 五、一句话总结

> `synchronized` 给方法加了一把**互斥锁**，**强制多个线程排队执行**，避免了多线程同时修改 `count` 和 `value[]` 时的数据错乱，同时通过 JMM 的内存语义保证了**可见性**——这就是它能让 StringBuffer 线程安全的根本原因。
