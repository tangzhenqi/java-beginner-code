package com.summary._08_concurrent_tools;

import java.util.concurrent.Semaphore;

/**
 * Semaphore："信号量 / 许可证"，用来限并发数。
 * <p>
 * 经典场景：限定数据库连接池最多 3 个并发连接，超过的线程要排队等许可。
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore parking = new Semaphore(3); // 只有 3 个车位

        for (int i = 1; i <= 8; i++) {
            int car = i;
            new Thread(() -> {
                try {
                    parking.acquire();
                    System.out.println("车 " + car + " 进入车位（剩余 " + parking.availablePermits() + "）");
                    Thread.sleep(800);
                    System.out.println("车 " + car + " 离开");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    parking.release();
                }
            }).start();
        }
    }
}
