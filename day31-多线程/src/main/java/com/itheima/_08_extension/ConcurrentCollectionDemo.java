package com.itheima._08_extension;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 【拓展】并发集合 ConcurrentHashMap
 *
 * 普通 HashMap 在多线程下既可能丢数据，也可能出现死循环（JDK7 扩容）。
 * 解决方案：
 *   1) Collections.synchronizedMap(map)   全表锁，性能差
 *   2) ConcurrentHashMap                  分段/CAS，并发性能好（推荐）
 *
 * 本例对比：100 个线程各自 put 1000 个 key，观察最终 size。
 */
public class ConcurrentCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("HashMap          最终 size = " + testMap(new HashMap<>()));
        System.out.println("ConcurrentHashMap 最终 size = " + testMap(new ConcurrentHashMap<>()));
    }

    private static int testMap(Map<String, Integer> map) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int t = 0; t < threads.length; t++) {
            final int idx = t;
            threads[t] = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    map.put("k-" + idx + "-" + i, i);
                }
            });
        }
        for (Thread th : threads) th.start();
        for (Thread th : threads) th.join();
        return map.size();   // 期望 100_000
    }
}
