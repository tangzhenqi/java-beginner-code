package com.itheima._07_blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者：从阻塞队列中 take 食物。
 * 当队列为空时，take 会自动阻塞，直到有元素可取。
 */
public class Consumer extends Thread {

    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String food = queue.take();   // 空 -> 阻塞
                System.out.println("吃货吃了 " + food);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
