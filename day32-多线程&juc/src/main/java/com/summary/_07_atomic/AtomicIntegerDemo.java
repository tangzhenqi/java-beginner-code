package com.summary._07_atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 经典反面教材：1000 个线程同时对一个普通 int 做 i++，结果一定小于 1000。
 * 因为 i++ 是 "读-改-写" 三步，不具备原子性。
 * <p>
 * AtomicInteger 基于 CAS（CompareAndSwap）做无锁自增，性能高且线程安全。
 */
public class AtomicIntegerDemo {

    public static void main(String[] args) throws InterruptedException {
        int n = 1000;
        CountDownLatch latch = new CountDownLatch(n);

        int[] unsafe = {0};
        AtomicInteger safe = new AtomicInteger(0);

        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                unsafe[0]++;          // 非原子 → 结果不可预测
                safe.incrementAndGet(); // CAS 原子自增 → 一定为 n
                latch.countDown();
            }).start();
        }

        latch.await();
        System.out.println("非原子 int    结果：" + unsafe[0] + "（很可能 < " + n + "）");
        System.out.println("AtomicInteger 结果：" + safe.get() + "（必然 = " + n + "）");
    }
}
