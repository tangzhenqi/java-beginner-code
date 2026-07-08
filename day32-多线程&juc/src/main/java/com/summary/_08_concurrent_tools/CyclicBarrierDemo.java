package com.summary._08_concurrent_tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CyclicBarrier："循环栅栏"。
 * <p>
 * 当指定数量的线程都调用了 await()，栅栏才会打开，所有线程一起继续；
 * 然后栅栏自动复位，可以再用于下一轮。适合"多人组队后再出发"的分阶段并行。
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int players = 3;
        CyclicBarrier barrier = new CyclicBarrier(players,
                () -> System.out.println("==== 三人到齐，比赛开始！===="));

        for (int i = 1; i <= players; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
                    System.out.println("玩家 " + id + " 到位");
                    barrier.await();
                    System.out.println("玩家 " + id + " 已出发");
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
