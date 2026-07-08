package com.summary._05_threadpool_executor;

/**
 * 拿到当前机器可用 CPU 核数，用于配置线程池大小。
 * <ul>
 *   <li>CPU 密集型任务：corePoolSize ≈ N + 1</li>
 *   <li>IO  密集型任务：corePoolSize ≈ N * 2 ~ N * (1 + 平均等待/计算)</li>
 * </ul>
 */
public class AvailableProcessorsDemo {
    public static void main(String[] args) {
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println("当前可用 CPU 核数：" + count);
        System.out.println("CPU 密集型推荐核心线程数：" + (count + 1));
        System.out.println("IO  密集型推荐核心线程数：" + (count * 2));
    }
}
