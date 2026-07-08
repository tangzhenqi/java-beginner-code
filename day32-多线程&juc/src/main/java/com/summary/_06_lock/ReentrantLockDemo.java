package com.summary._06_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用 ReentrantLock 改写卖票案例。
 * <p>
 * 对比 synchronized 的优势：
 * <ul>
 *   <li>可中断   lockInterruptibly()</li>
 *   <li>可超时   tryLock(time, unit)</li>
 *   <li>可公平   new ReentrantLock(true)</li>
 *   <li>多条件变量 lock.newCondition()</li>
 * </ul>
 * <p>
 * 代价：必须放在 try-finally 里 unlock，否则忘记释放会死锁。
 */
public class ReentrantLockDemo {

    private static final Lock LOCK = new ReentrantLock();
    private static int ticket = 100;

    public static void main(String[] args) {
        Runnable seller = () -> {
            while (true) {
                LOCK.lock();
                try {
                    if (ticket == 0) {
                        break;
                    }
                    ticket--;
                    System.out.println(Thread.currentThread().getName() + " 卖票，剩 " + ticket);
                } finally {
                    LOCK.unlock();
                }
            }
        };
        new Thread(seller, "窗口1").start();
        new Thread(seller, "窗口2").start();
    }
}
