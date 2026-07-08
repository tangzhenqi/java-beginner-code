package com.itheima._04_safe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 卖票案例 —— 方案三：JDK 5 的 Lock 接口（手动锁）
 *
 *   void lock()    加锁
 *   void unlock()  释放锁
 *
 * 必须把 unlock 放在 finally 中，避免抛异常后锁无法释放。
 *
 * 与 synchronized 对比：
 *   - synchronized 隐式加锁/解锁，遇异常自动释放
 *   - Lock 更灵活：可中断、可设超时（tryLock）、可读写分离（ReentrantReadWriteLock）
 */
public class LockTicketDemo {

    static int ticket = 0;
    static final int TOTAL = 100;
    static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable task = () -> {
            while (true) {
                lock.lock();
                try {
                    if (ticket >= TOTAL) break;
                    try { Thread.sleep(5); } catch (InterruptedException ignored) {}
                    ticket++;
                    System.out.println(Thread.currentThread().getName() + " 卖出第 " + ticket + " 张票");
                } finally {
                    lock.unlock();
                }
            }
        };

        new Thread(task, "窗口1").start();
        new Thread(task, "窗口2").start();
        new Thread(task, "窗口3").start();
    }
}
