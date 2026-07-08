# day31 多线程 —— 知识点归纳与拓展

本项目是对 `day31-多线程/mythread` 的系统化总结，并补充了若干 day32 / JUC 相关的拓展示例。
项目以 Maven 单模块组织，JDK 8。

## 项目结构

```
day31-多线程-归纳与拓展/
├── pom.xml
└── src/main/java/com/itheima/
    ├── _01_create/        三种创建多线程的方式
    ├── _02_methods/       Thread 常用方法
    ├── _03_lifecycle/     【拓展】线程生命周期 / 6 种状态
    ├── _04_safe/          线程安全：synchronized 块 / 方法 / Lock / 【拓展】volatile
    ├── _05_deadlock/      死锁的产生与修复
    ├── _06_waitnotify/    生产者-消费者（wait / notify）
    ├── _07_blockingqueue/ 生产者-消费者（阻塞队列）
    └── _08_extension/     【拓展】AtomicInteger / CountDownLatch / ConcurrentHashMap / 线程池预览
```

每个 `XxxDemo` 都带 `main` 方法，可直接运行。

## 知识点速查

### 一、创建线程的三种方式（_01_create）
| 方式 | 关键 API | 优点 | 缺点 |
| --- | --- | --- | --- |
| 继承 Thread | `extends Thread` + 重写 `run` | 编码简单 | 已继承 Thread 无法再继承别的类 |
| 实现 Runnable | `implements Runnable` | 扩展性强，可共享同一任务 | 没有返回值 |
| 实现 Callable | `implements Callable<V>` + `FutureTask` | 有返回值，可抛异常 | 编码相对复杂 |

### 二、Thread 常用方法（_02_methods）
- `getName / setName / currentThread` —— 名称管理
- `sleep(ms)` —— 当前线程休眠
- `setPriority(1~10)` —— 优先级，仅做调度建议
- `setDaemon(true)` —— 守护线程；非守护全部结束时自动退出
- `yield()` —— 礼让 CPU（不可靠）
- `join()` —— 等待目标线程执行完毕

### 三、线程的 6 种状态（_03_lifecycle，拓展）
`NEW` → `RUNNABLE` → (`BLOCKED` / `WAITING` / `TIMED_WAITING`) → `TERMINATED`

### 四、线程安全（_04_safe）
- 同步代码块：`synchronized (锁对象) { ... }`
- 同步方法：`synchronized` 修饰方法，锁对象 = `this`（静态方法 = `类.class`）
- `Lock` 接口（JDK 5）：`ReentrantLock.lock() / unlock()`，必须 `try-finally` 释放
- 【拓展】`volatile`：保证可见性、禁止指令重排序，但不保证原子性

### 五、死锁（_05_deadlock）
- 形成条件：互斥、占有并等待、不可剥夺、循环等待
- 修复思路：所有线程按相同顺序加锁；或使用 `tryLock(timeout)`

### 六、等待唤醒机制（_06_waitnotify）
- 必须在 `synchronized` 内，且锁对象一致：
  - `lock.wait()` 释放锁并等待
  - `lock.notifyAll()` 唤醒所有等待线程
- 经典模型：生产者-消费者

### 七、阻塞队列（_07_blockingqueue）
- `ArrayBlockingQueue` / `LinkedBlockingQueue`
- `put` 满则阻塞，`take` 空则阻塞
- 把 `synchronized + wait/notify` 封装在内部，使用极其简洁

### 八、拓展内容（_08_extension）
- `AtomicInteger`：基于 CAS 的无锁自增，解决 `i++` 非原子问题
- `CountDownLatch`：让主线程等待多个子任务全部完成
- `ConcurrentHashMap`：替代 `HashMap` 的并发安全 Map
- `Executors.newFixedThreadPool`：线程池预览（详见 day32）

## 运行方式

任意 IDE 直接运行各 `XxxDemo#main`；或在项目根目录执行：

```bash
mvn -q compile exec:java -Dexec.mainClass=com.itheima._01_create.CreateThreadDemo
```
