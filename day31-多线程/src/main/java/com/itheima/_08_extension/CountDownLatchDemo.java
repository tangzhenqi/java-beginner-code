package com.itheima._08_extension;

import java.util.concurrent.CountDownLatch;

/**
 * 【拓展】CountDownLatch（倒计时门闩）
 *
 * 一个线程（或多个）调用 await() 等待，
 * 其他工作线程每完成一件事就调用 countDown()，把计数器 -1。
 * 当计数器减到 0，所有 await 的线程同时被唤醒。
 *
 * 场景：主线程等待 N 个子任务全部完成 —— 比 join 更灵活，因为可以等"任意线程"。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int workerCount = 3;
        CountDownLatch latch = new CountDownLatch(workerCount);

        for (int i = 1; i <= workerCount; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    Thread.sleep(500L * id);
                    System.out.println("子任务 " + id + " 完成");
                } catch (InterruptedException ignored) {
                } finally {
                    latch.countDown();        // 计数 -1
                }
            }, "worker-" + i).start();
        }

        System.out.println("main 等待所有子任务...");
        latch.await();                        // 阻塞直到计数器为 0
        System.out.println("全部子任务完成，main 继续执行");
    }
}
