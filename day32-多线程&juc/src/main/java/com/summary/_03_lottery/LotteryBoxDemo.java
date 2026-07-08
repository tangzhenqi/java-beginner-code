package com.summary._03_lottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 抽奖（一次性统计版）：抽完之后再一次性打印各自的统计。
 * <p>
 * 关键技巧：boxList 定义在 run() 方法里（栈上局部变量），
 * 这样每个线程都各持各的统计结果，互不干扰，无需 ThreadLocal。
 */
public class LotteryBoxDemo {

    public static void main(String[] args) {
        ArrayList<Integer> pool = new ArrayList<>(Arrays.asList(10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700));
        LotteryBoxTask task = new LotteryBoxTask(pool);
        new Thread(task, "抽奖箱1").start();
        new Thread(task, "抽奖箱2").start();
    }

    static class LotteryBoxTask implements Runnable {
        private final ArrayList<Integer> pool;

        LotteryBoxTask(ArrayList<Integer> pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            ArrayList<Integer> boxList = new ArrayList<>();
            while (true) {
                synchronized (LotteryBoxTask.class) {
                    if (pool.isEmpty()) {
                        int sum = boxList.stream().mapToInt(Integer::intValue).sum();
                        int max = boxList.isEmpty() ? 0 : Collections.max(boxList);
                        System.out.println("在此次抽奖过程中，" + Thread.currentThread().getName()
                                + " 总共产生了 " + boxList.size() + " 个奖项，分别为：" + boxList
                                + "，最高奖项为 " + max + " 元，总计 " + sum + " 元");
                        break;
                    }
                    Collections.shuffle(pool);
                    boxList.add(pool.remove(0));
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
