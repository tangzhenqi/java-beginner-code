package com.summary._04_executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newFixedThreadPool(n)：
 * 固定大小的线程池，线程数稳定在 n。提交超过 n 的任务会被放入"无界队列"等待。
 * <p>
 * 即使提交 6 个任务，控制台中真正运行的线程数也只有 3，可以观察到线程被复用。
 * <p>
 * ⚠️ 注意：内部使用的是 new LinkedBlockingQueue() 无界队列，
 *        极端场景下任务堆积会 OOM —— 生产环境建议手动用 ThreadPoolExecutor。
 */
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 6; i++) {
            pool.submit(new PrintTask());
        }

        // shutdown() 不会立即结束，但不再接受新任务，等已提交的跑完再退出
        pool.shutdown();
    }
}
