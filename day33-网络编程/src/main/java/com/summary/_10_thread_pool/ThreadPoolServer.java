package com.summary._10_thread_pool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 知识点 10：线程池 TCP 服务器（生产推荐方案）
 *
 * 七大参数：
 *   1. corePoolSize       核心线程数（常驻）
 *   2. maximumPoolSize    最大线程数
 *   3. keepAliveTime      非核心线程空闲存活时间
 *   4. unit               时间单位
 *   5. workQueue          任务阻塞队列
 *   6. threadFactory      线程工厂
 *   7. handler            拒绝策略
 *      - AbortPolicy        抛 RejectedExecutionException（默认）
 *      - CallerRunsPolicy   调用者线程自己跑
 *      - DiscardPolicy      默默丢弃
 *      - DiscardOldestPolicy 丢掉队列里最老的任务
 *
 * 任务执行流程：
 *   核心线程未满 → 创建核心线程
 *   核心已满    → 入队列
 *   队列已满    → 创建非核心线程（直到 max）
 *   全满        → 走拒绝策略
 */
public class ThreadPoolServer {
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,
                16,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        ServerSocket ss = new ServerSocket(10000);
        System.out.println("线程池服务端启动...");

        while (true) {
            Socket socket = ss.accept();
            pool.submit(new ThreadPoolClientHandler(socket));
        }
    }
}
