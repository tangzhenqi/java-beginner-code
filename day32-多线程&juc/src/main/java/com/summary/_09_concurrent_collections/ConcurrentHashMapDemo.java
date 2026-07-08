package com.summary._09_concurrent_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * HashMap 在并发下不仅会丢数据，JDK 7 还可能形成死链导致死循环。
 * ConcurrentHashMap 采用 "CAS + synchronized 分桶" 在 JDK 8 实现高效并发。
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 100;
        int loop = 1000;

        Map<Integer, Integer> unsafe = new HashMap<>();
        Map<Integer, Integer> safe = new ConcurrentHashMap<>();

        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int t = 0; t < threadCount; t++) {
            int base = t * loop;
            new Thread(() -> {
                for (int i = 0; i < loop; i++) {
                    int k = base + i;
                    unsafe.put(k, k);
                    safe.put(k, k);
                }
                latch.countDown();
            }).start();
        }
        latch.await();

        System.out.println("HashMap            size = " + unsafe.size() + " （可能 < " + (threadCount * loop) + "）");
        System.out.println("ConcurrentHashMap  size = " + safe.size() + " （应等于 " + (threadCount * loop) + "）");
    }
}
