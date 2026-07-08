# day32 多线程 & JUC —— 知识点归纳与拓展

本项目是对 `day32-多线程&juc` 的系统化归纳与拓展。day32 在 day31 的基础上聚焦：

1. **多线程同步实战**：卖票、礼物、奇数（三种线程实现方式的综合练习）
2. **典型的共享资源案例**：抢红包（金额拆分）、抽奖（List 抽取）
3. **Callable / FutureTask**：第一次拿到线程"返回值"
4. **线程池**：`Executors` 工厂方法 + `ThreadPoolExecutor` 七大参数
5. **【拓展】JUC**：Lock、原子类、CountDownLatch、CyclicBarrier、Semaphore、并发集合、CompletableFuture、ThreadLocal

项目以 Maven 单模块组织，JDK 11。

## 项目结构

```
day32-多线程&juc-归纳与拓展/
├── pom.xml
└── src/main/java/com/summary/
    ├── _01_sync_practice/        三种线程实现方式综合实战
    │   ├── TicketSellingDemo.java     (对应 test1：1000 张票)
    │   ├── GiftSendingDemo.java       (对应 test2：100 份礼物)
    │   └── OddNumberDemo.java         (对应 test3：1-100 打印奇数)
    ├── _02_redpacket/            抢红包（共享数据 + BigDecimal 精度）
    │   ├── RedPacketDemo.java         (对应 test4-case1：double 简单版)
    │   └── RedPacketBigDecimalDemo.java (对应 test4-case2：BigDecimal 精确版)
    ├── _03_lottery/              抽奖（List 共享数据 + Callable 返回值）
    │   ├── LotteryRunnableDemo.java   (对应 test5：边抽边打印)
    │   ├── LotteryBoxDemo.java        (对应 test6：抽完一次性打印统计)
    │   └── LotteryCallableDemo.java   (对应 test7：用 Callable 拿"最大奖")
    ├── _04_executors/            Executors 创建线程池
    │   ├── PrintTask.java
    │   ├── FixedThreadPoolDemo.java
    │   └── CachedThreadPoolDemo.java
    ├── _05_threadpool_executor/  ThreadPoolExecutor 七大参数
    │   ├── ThreadPoolExecutorDemo.java
    │   ├── RejectPolicyDemo.java
    │   └── AvailableProcessorsDemo.java
    ├── _06_lock/                 【拓展】Lock 与 读写锁
    │   ├── ReentrantLockDemo.java
    │   └── ReadWriteLockDemo.java
    ├── _07_atomic/               【拓展】原子类
    │   ├── AtomicIntegerDemo.java
    │   └── AtomicReferenceDemo.java
    ├── _08_concurrent_tools/     【拓展】CountDownLatch / CyclicBarrier / Semaphore
    │   ├── CountDownLatchDemo.java
    │   ├── CyclicBarrierDemo.java
    │   └── SemaphoreDemo.java
    ├── _09_concurrent_collections/ 【拓展】并发集合
    │   ├── ConcurrentHashMapDemo.java
    │   └── CopyOnWriteArrayListDemo.java
    ├── _10_completable_future/   【拓展】CompletableFuture 异步编程
    │   └── CompletableFutureDemo.java
    └── _11_threadlocal/          【拓展】ThreadLocal 线程本地变量
        └── ThreadLocalDemo.java
```

每个 `XxxDemo` 都带 `main` 方法，可直接运行。

---

## 知识点速查

### 一、共享数据 + 同步代码块的"通用四步法"（_01_sync_practice）

不论卖票、送礼、打奇数，套路完全一致：

```java
while (true) {                            // 1. 死循环
    synchronized (锁对象) {                // 2. 同步代码块
        if (共享数据到末尾) break;          // 3. 判断结束
        // 4. 没到末尾：操作共享数据
    }
}
```

- **继承 Thread**：共享数据用 `static`，因为每个线程对象不同
- **实现 Runnable**：共享数据为成员变量即可，多个 Thread 共用同一个 Runnable
- **锁对象**：建议用 `XXX.class`（唯一），不要用任意字符串字面量

### 二、共享资源的分配模型（_02_redpacket）

抢红包是典型的 "**总量有限的资源分配**"：

| 比较点 | double 版（case1） | BigDecimal 版（case2） |
| --- | --- | --- |
| 精度 | 受浮点影响，可能出现 0.1+0.2≠0.3 | 精确，金融场景必选 |
| API | 直接 +, -, * | `add / subtract / multiply` |
| 保留小数 | `String.format` 等 | `setScale(2, RoundingMode.HALF_UP)` |

要点：
- 给"最后一个红包"特判，**避免 0 元红包**
- 上界 = `money - (count - 1) * MIN`，给后面的人留下"每人至少 0.01"

### 三、Callable 与 FutureTask：取得线程的"返回值"（_03_lottery）

```java
Callable<Integer> task = ...;
FutureTask<Integer> ft1 = new FutureTask<>(task);
new Thread(ft1, "抽奖箱1").start();
Integer max = ft1.get();   // 阻塞直到线程结束并返回结果
```

- `Callable<V>.call()` **有返回值，能抛异常**，弥补 `Runnable.run()` 的缺陷
- `FutureTask` 同时是 `Runnable`，所以能丢给 `Thread`
- `get()` 阻塞，可用 `get(timeout)` 限定超时

### 四、线程池（_04_executors / _05_threadpool_executor）

#### 为什么要用线程池
- 频繁 `new Thread` 创建/销毁开销大
- 线程数失控会拖垮系统
- 线程池统一管理、复用、限流

#### Executors 工厂方法（_04）

| 方法 | 说明 | 内部队列 |
| --- | --- | --- |
| `newFixedThreadPool(n)` | 固定 n 个线程 | **无界** `LinkedBlockingQueue` |
| `newCachedThreadPool()` | 线程数无上限，60s 空闲回收 | `SynchronousQueue` |
| `newSingleThreadExecutor()` | 单线程 | 无界队列 |
| `newScheduledThreadPool(n)` | 支持定时/周期任务 | `DelayedWorkQueue` |

> ⚠️ **阿里 Java 开发手册不推荐 `Executors`**：上面前两个都可能导致 OOM。生产建议直接用 `ThreadPoolExecutor`。

#### ThreadPoolExecutor 七大参数（_05）

```java
new ThreadPoolExecutor(
    corePoolSize,       // 1.核心线程数（常驻）
    maximumPoolSize,    // 2.最大线程数（含临时）
    keepAliveTime,      // 3.临时线程空闲存活时间
    TimeUnit.SECONDS,   // 4.时间单位
    new ArrayBlockingQueue<>(3), // 5.任务队列
    Executors.defaultThreadFactory(), // 6.线程工厂
    new ThreadPoolExecutor.AbortPolicy() // 7.拒绝策略
);
```

**任务来临时的处理流程**：

```
1. 核心线程未满 → 创建核心线程
2. 核心线程已满 → 入队等待
3. 队列已满且最大线程未满 → 创建临时线程
4. 队列已满且最大线程也满 → 触发拒绝策略
```

**四种内置拒绝策略**：
- `AbortPolicy`（默认）抛 `RejectedExecutionException`
- `DiscardPolicy` 默默丢弃新任务
- `DiscardOldestPolicy` 丢弃队列里最老的，再尝试入队新任务
- `CallerRunsPolicy` 由调用者线程（如 main）自己执行

**线程数经验值**：
- CPU 密集型：`核数 + 1`
- IO 密集型：`核数 * 2` 或 `核数 / (1 - 阻塞系数)`
- 通过 `Runtime.getRuntime().availableProcessors()` 拿到核数

### 五、Lock 替代 synchronized（_06_lock，拓展）

`ReentrantLock`：
- 可中断 `lockInterruptibly()`
- 可超时 `tryLock(timeout)`
- 可公平 `new ReentrantLock(true)`
- 必须 `try-finally` 中 `unlock()`

`ReentrantReadWriteLock`：**读读共享、读写互斥、写写互斥**，适合"读多写少"场景，性能远胜独占锁。

### 六、原子类（_07_atomic，拓展）

`AtomicInteger`、`AtomicLong`、`AtomicReference` 基于 **CAS（CompareAndSwap）** 实现无锁线程安全：
- `getAndIncrement()` 相当于线程安全的 `i++`
- 内部循环 CAS，避免阻塞，但极端竞争下会自旋
- ABA 问题用 `AtomicStampedReference` 解决

### 七、JUC 同步工具（_08_concurrent_tools，拓展）

| 工具 | 一句话 | 经典场景 |
| --- | --- | --- |
| `CountDownLatch` | "**减到 0** 才放行"，一次性 | 主线程等多个子任务完成 |
| `CyclicBarrier` | "**凑齐 N 人**才放行"，可循环 | 多线程分阶段并行 |
| `Semaphore` | "**许可证**"，限并发数 | 限流、连接池 |

### 八、并发集合（_09_concurrent_collections，拓展）

| 旧 / 不安全 | 推荐替代 | 原理 |
| --- | --- | --- |
| `HashMap` | `ConcurrentHashMap` | CAS + synchronized 分桶 |
| `ArrayList` | `CopyOnWriteArrayList` | 写时复制，适合"读多写少" |
| `HashSet` | `CopyOnWriteArraySet` | 同上 |
| `LinkedList` 队列 | `ConcurrentLinkedQueue` | 无锁队列 |

> 切勿把 `Collections.synchronizedXxx()` 当万金油 —— 复合操作（如"先 check 再 put"）仍然要外层加锁。

### 九、CompletableFuture：现代异步（_10，拓展）

```java
CompletableFuture
    .supplyAsync(() -> fetchUser())          // 异步获取
    .thenApply(user -> user.getName())       // 转换
    .thenAccept(System.out::println)         // 消费
    .exceptionally(ex -> { ... return null; }); // 兜底
```

- 链式 / 组合多个 Future（`thenCombine`、`allOf`、`anyOf`）
- 比裸 `FutureTask.get()` 阻塞式更灵活，是 JDK 8 新增的异步利器

### 十、ThreadLocal（_11，拓展）

- 每个线程一份独立副本，**避免参数层层透传**
- 常见用途：用户上下文、`SimpleDateFormat` 线程私有化、事务连接
- 注意：**线程池场景必须 `remove()`**，否则线程被复用时数据会"串"，且 `ThreadLocalMap` 的 Entry key 为弱引用 → value 强引用 → 易内存泄漏

---

## 运行方式

任意 IDE 中直接运行各 `XxxDemo#main`；或在项目根目录执行：

```bash
mvn -q compile exec:java -Dexec.mainClass=com.summary._01_sync_practice.TicketSellingDemo
```
