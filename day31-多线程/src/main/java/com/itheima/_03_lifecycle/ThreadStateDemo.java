package com.itheima._03_lifecycle;

/**
 * 【拓展】线程的 6 种状态（Thread.State 枚举）：
 *
 *   NEW            新建：线程对象已创建，但还没调用 start()
 *   RUNNABLE       可运行：调用了 start()，等待 / 占用 CPU
 *   BLOCKED        阻塞：等待获取一个 synchronized 锁
 *   WAITING        无限等待：调用 wait() / join() / LockSupport.park()
 *   TIMED_WAITING  有限等待：调用 sleep(ms) / wait(ms) / join(ms)
 *   TERMINATED     终止：run() 执行完毕
 *
 * 状态迁移图（简化）：
 *      NEW --start()--> RUNNABLE <--抢到 CPU--> 执行
 *                          |  \
 *                          |   \--wait()/sleep()/join()--> WAITING / TIMED_WAITING
 *                          |                                      |
 *                          |<------ notify()/超时/被中断 ----------|
 *                          |
 *                          +--synchronized 等锁--> BLOCKED -- 抢到锁 --> RUNNABLE
 *                          |
 *                          +--run() 结束--> TERMINATED
 */
public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(500);              // 进入 TIMED_WAITING
            } catch (InterruptedException ignored) {}
        }, "t");

        System.out.println("创建后: " + t.getState());  // NEW

        t.start();
        System.out.println("启动后: " + t.getState());  // RUNNABLE

        Thread.sleep(100);
        System.out.println("sleep 中: " + t.getState()); // TIMED_WAITING

        t.join();
        System.out.println("结束后: " + t.getState());   // TERMINATED
    }
}
