package com.summary._05_threadpool_executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 演示 4 种内置拒绝策略：
 * <ul>
 *   <li>AbortPolicy         默认；直接抛 RejectedExecutionException</li>
 *   <li>DiscardPolicy       默默丢弃</li>
 *   <li>DiscardOldestPolicy 丢弃队列最老任务，再尝试入队新任务</li>
 *   <li>CallerRunsPolicy    退回到调用者线程（如 main）自己跑，相当于"反向限流"</li>
 * </ul>
 * <p>
 * 调整下面的 handler 即可观察不同效果。
 */
public class RejectPolicyDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1,
                2,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 1; i <= 6; i++) {
            int taskId = i;
            try {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 执行任务 " + taskId);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("任务 " + taskId + " 被拒绝：" + e.getMessage());
            }
        }

        pool.shutdown();
    }
}
