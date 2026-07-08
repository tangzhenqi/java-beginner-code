package com.itheima._08_extension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 【拓展 / day32 预热】线程池 ExecutorService
 *
 * 为什么需要线程池？
 *   - 频繁创建 / 销毁线程开销大
 *   - 线程数不受控会拖垮系统
 *
 * 线程池的好处：
 *   1. 复用已创建的线程
 *   2. 控制最大并发数
 *   3. 统一管理任务队列与异常
 *
 * Executors 工厂方法（学习用，生产中建议手动 new ThreadPoolExecutor 以避免 OOM）：
 *   newFixedThreadPool(n)    固定大小
 *   newSingleThreadExecutor() 单线程
 *   newCachedThreadPool()    可弹性扩容
 *
 * 这里只是预览，详细的 7 个核心参数、4 种拒绝策略放在 day32 学习。
 */
public class ThreadPoolPreview {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            pool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 执行任务 " + taskId);
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
            });
        }

        pool.shutdown();   // 不再接收新任务，等已提交任务执行完后关闭线程池
    }
}
