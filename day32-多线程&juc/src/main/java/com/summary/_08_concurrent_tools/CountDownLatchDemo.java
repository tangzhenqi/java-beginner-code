package com.summary._08_concurrent_tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CountDownLatch："倒计时门闩"。
 * <p>
 * 场景：主线程要等所有子任务都跑完，再汇总。
 * await() 阻塞直到计数减到 0；每个子任务结束时 countDown()。
 * <p>
 * ⚠️ 一次性，不能复位。要循环使用，请看 CyclicBarrierDemo。
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        int taskCount = 5;
        CountDownLatch latch = new CountDownLatch(taskCount);

        for (int i = 1; i <= taskCount; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1500));
                    System.out.println("子任务 " + id + " 完成");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        System.out.println("主线程等待所有子任务...");
        latch.await();
        System.out.println("所有子任务完成，开始汇总！");
    }
}
