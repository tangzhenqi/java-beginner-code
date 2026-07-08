package com.itheima._08_extension;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 【拓展】原子类 AtomicInteger
 *
 * 问题：i++ 不是原子操作（实际是 读-改-写 三步），多线程下会出错。
 * 解法：
 *   1) synchronized / Lock 加锁
 *   2) 使用 java.util.concurrent.atomic 包下的原子类（底层基于 CAS 算法，无锁）
 *
 * 常用方法：
 *   incrementAndGet()    自增并返回新值（相当于 ++i）
 *   getAndIncrement()    返回旧值后自增（相当于 i++）
 *   addAndGet(int delta)
 *   compareAndSet(expect, update)  CAS 操作
 */
public class AtomicDemo {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.incrementAndGet();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 期望 20000，使用 AtomicInteger 一定正确
        System.out.println("最终结果 = " + counter.get());
    }
}
