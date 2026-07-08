package com.summary._04_executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newCachedThreadPool()：
 * 没有上限的线程池。任务来了就用空闲线程，没有空闲就 new 一个新的；
 * 60 秒空闲的线程会被回收。
 * <p>
 * ⚠️ 注意：理论上能开无限多个线程，瞬间高并发可能直接打爆系统资源。
 */
public class CachedThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            pool.submit(new PrintTask());
            // 间隔提交，可观察到线程被"复用"
            Thread.sleep(50);
        }

        pool.shutdown();
    }
}
