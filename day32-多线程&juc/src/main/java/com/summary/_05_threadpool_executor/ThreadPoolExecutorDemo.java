package com.summary._05_threadpool_executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 七大参数的 ThreadPoolExecutor 完整示例。
 * <p>
 * 参数含义：
 * <ol>
 *   <li>corePoolSize     - 核心线程数（常驻不回收）</li>
 *   <li>maximumPoolSize  - 最大线程数 = 核心 + 临时</li>
 *   <li>keepAliveTime    - 临时线程空闲多久就回收</li>
 *   <li>unit             - 上一项的时间单位</li>
 *   <li>workQueue        - 任务队列</li>
 *   <li>threadFactory    - 线程工厂（命名 / 守护 等）</li>
 *   <li>handler          - 任务拒绝策略</li>
 * </ol>
 *
 * 任务提交流程：核心未满→建核心；核心已满→入队；队列满且未达最大→建临时；都满→拒绝。
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,
                6,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        // 提交 9 个 = 核心 3 + 队列 3 + 临时 3，刚好打满
        for (int i = 1; i <= 9; i++) {
            int taskId = i;
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 执行任务 " + taskId);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("全部任务结束");
    }
}
