package com.summary._03_lottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 抽奖（Callable + FutureTask）：
 * 终于让线程"有了返回值"——每个抽奖箱抽完后返回自己的最大奖项，
 * 主线程拿到两个返回值后比较出最终大奖归属。
 * <p>
 * 模板：
 *   Callable&lt;V&gt;   call() 有返回值、可抛异常
 *   FutureTask&lt;V&gt; 既是 Runnable 又能存放返回值
 *   ft.get()      阻塞直到 call() 执行完，拿到结果
 */
public class LotteryCallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<Integer> pool = new ArrayList<>(Arrays.asList(10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700));
        LotteryCallable callable = new LotteryCallable(pool);

        FutureTask<Integer> ft1 = new FutureTask<>(callable);
        FutureTask<Integer> ft2 = new FutureTask<>(callable);
        new Thread(ft1, "抽奖箱1").start();
        new Thread(ft2, "抽奖箱2").start();

        Integer max1 = ft1.get();
        Integer max2 = ft2.get();

        if (max1 == null && max2 == null) {
            System.out.println("没人抽到奖");
        } else if (max1 == null) {
            System.out.println("最大奖在抽奖箱2：" + max2 + " 元");
        } else if (max2 == null || max1 > max2) {
            System.out.println("最大奖在抽奖箱1：" + max1 + " 元");
        } else if (max1 < max2) {
            System.out.println("最大奖在抽奖箱2：" + max2 + " 元");
        } else {
            System.out.println("两个抽奖箱并列最高：" + max1 + " 元");
        }
    }

    static class LotteryCallable implements Callable<Integer> {
        private final ArrayList<Integer> pool;

        LotteryCallable(ArrayList<Integer> pool) {
            this.pool = pool;
        }

        @Override
        public Integer call() throws InterruptedException {
            ArrayList<Integer> boxList = new ArrayList<>();
            while (true) {
                synchronized (LotteryCallable.class) {
                    if (pool.isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + " 抽到的奖项：" + boxList);
                        break;
                    }
                    Collections.shuffle(pool);
                    boxList.add(pool.remove(0));
                }
                Thread.sleep(10);
            }
            return boxList.isEmpty() ? null : Collections.max(boxList);
        }
    }
}
