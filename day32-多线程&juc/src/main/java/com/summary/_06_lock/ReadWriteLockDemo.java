package com.summary._06_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock：读读共享、读写互斥、写写互斥。
 * <p>
 * "读多写少"场景（如缓存、配置中心）下，比独占锁性能高得多。
 */
public class ReadWriteLockDemo {

    private static final ReentrantReadWriteLock RW_LOCK = new ReentrantReadWriteLock();
    private static int cache = 0;

    public static void main(String[] args) {
        // 4 个读线程
        for (int i = 0; i < 4; i++) {
            new Thread(ReadWriteLockDemo::read, "读" + i).start();
        }
        // 2 个写线程
        for (int i = 0; i < 2; i++) {
            int v = i + 1;
            new Thread(() -> write(v * 100), "写" + i).start();
        }
    }

    private static void read() {
        RW_LOCK.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 读到 " + cache);
            sleep(100);
        } finally {
            RW_LOCK.readLock().unlock();
        }
    }

    private static void write(int v) {
        RW_LOCK.writeLock().lock();
        try {
            cache = v;
            System.out.println(Thread.currentThread().getName() + " 写入 " + cache);
            sleep(100);
        } finally {
            RW_LOCK.writeLock().unlock();
        }
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
