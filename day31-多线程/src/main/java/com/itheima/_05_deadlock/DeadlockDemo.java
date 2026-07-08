package com.itheima._05_deadlock;

/**
 * 死锁：两个或多个线程互相持有对方需要的锁，导致永久阻塞。
 *
 * 形成条件（缺一不可）：
 *   1. 互斥使用
 *   2. 持有并等待
 *   3. 不可剥夺
 *   4. 循环等待  <-- 实际编码中最容易避免的一个
 *
 * 解决思路：
 *   - 保证所有线程以相同的顺序获取多把锁（见 a05deadlock.DeadlockFixDemo）
 *   - 使用 Lock.tryLock(timeout) 设置超时，避免无限等待
 */
public class DeadlockDemo {

    static final Object lockA = new Object();
    static final Object lockB = new Object();

    public static void main(String[] args) {

        // 线程 1：先拿 A 再拿 B
        new Thread(() -> {
            while (true) {
                synchronized (lockA) {
                    System.out.println("线程1：拿到 A，等待 B...");
                    synchronized (lockB) {
                        System.out.println("线程1：拿到 B，执行完毕");
                    }
                }
            }
        }, "线程1").start();

        // 线程 2：先拿 B 再拿 A —— 循环等待 -> 死锁
        new Thread(() -> {
            while (true) {
                synchronized (lockB) {
                    System.out.println("线程2：拿到 B，等待 A...");
                    synchronized (lockA) {
                        System.out.println("线程2：拿到 A，执行完毕");
                    }
                }
            }
        }, "线程2").start();
    }
}
