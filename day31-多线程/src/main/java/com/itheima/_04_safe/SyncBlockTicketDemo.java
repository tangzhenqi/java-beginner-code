package com.itheima._04_safe;

/**
 * 卖票案例 —— 方案一：synchronized 同步代码块
 *
 * 同步代码块格式：
 *   synchronized (锁对象) {
 *       // 需要同步的代码
 *   }
 *
 * 锁对象的选择：
 *   - 继承 Thread 的方式：建议使用 类名.class，保证所有线程用同一把锁
 *   - 实现 Runnable 的方式：使用 this 即可（因为只有一个 Runnable 对象）
 */
public class SyncBlockTicketDemo {

    static int ticket = 0;            // 共享数据
    static final int TOTAL = 100;

    public static void main(String[] args) {
        Runnable task = () -> {
            while (true) {
                synchronized (SyncBlockTicketDemo.class) {
                    if (ticket >= TOTAL) break;
                    try { Thread.sleep(5); } catch (InterruptedException ignored) {}
                    ticket++;
                    System.out.println(Thread.currentThread().getName() + " 卖出第 " + ticket + " 张票");
                }
            }
        };

        new Thread(task, "窗口1").start();
        new Thread(task, "窗口2").start();
        new Thread(task, "窗口3").start();
    }
}
