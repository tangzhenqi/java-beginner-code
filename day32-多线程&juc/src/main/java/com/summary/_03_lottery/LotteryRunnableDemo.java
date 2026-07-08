package com.summary._03_lottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 抽奖（边抽边打印）：
 * 奖池 {10,5,20,50,100,200,500,800,2,80,300,700}，
 * "抽奖箱1" 和 "抽奖箱2" 两条线程并发地从奖池里 remove(0)，并打印结果。
 * <p>
 * 共享数据是同一个 List，传给两个 Thread 共用。
 */
public class LotteryRunnableDemo {

    public static void main(String[] args) {
        ArrayList<Integer> pool = new ArrayList<>(Arrays.asList(10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700));
        LotteryTask task = new LotteryTask(pool);
        new Thread(task, "抽奖箱1").start();
        new Thread(task, "抽奖箱2").start();
    }

    static class LotteryTask implements Runnable {
        private final ArrayList<Integer> pool;

        LotteryTask(ArrayList<Integer> pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (LotteryTask.class) {
                    if (pool.isEmpty()) {
                        break;
                    }
                    Collections.shuffle(pool);
                    int prize = pool.remove(0);
                    System.out.println(Thread.currentThread().getName() + " 又产生了一个 " + prize + " 元大奖");
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
