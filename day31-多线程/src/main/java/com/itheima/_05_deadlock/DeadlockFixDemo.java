package com.itheima._05_deadlock;

/**
 * 【拓展】死锁的修复方案 —— 让所有线程按相同顺序拿锁，破坏"循环等待"。
 *
 * 这里两个线程都先拿 A 再拿 B，永远不会出现 A 被一个线程持有、B 被另一个线程持有的局面。
 */
public class DeadlockFixDemo {

    static final Object lockA = new Object();
    static final Object lockB = new Object();

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + " 拿到 A");
                    synchronized (lockB) {
                        System.out.println(Thread.currentThread().getName() + " 拿到 B，完成一轮");
                    }
                }
            }
        };

        new Thread(task, "线程1").start();
        new Thread(task, "线程2").start();
    }
}
