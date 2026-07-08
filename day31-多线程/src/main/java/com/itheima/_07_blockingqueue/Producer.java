package com.itheima._07_blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者：往阻塞队列中 put 食物。
 * 当队列已满时，put 会自动阻塞，直到有空间。
 */
public class Producer extends Thread {

    private final BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i = 1;
        while (true) {
            try {
                String food = "面条-" + i++;
                queue.put(food);              // 满 -> 阻塞
                System.out.println("厨师放了 " + food);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
