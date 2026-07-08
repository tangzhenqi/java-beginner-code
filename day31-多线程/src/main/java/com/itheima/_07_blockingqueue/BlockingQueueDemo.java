package com.itheima._07_blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用阻塞队列优雅地实现 生产者-消费者
 *
 * BlockingQueue 的核心方法：
 *   put(e)   队列满则阻塞
 *   take()   队列空则阻塞
 *   offer(e, timeout, unit) / poll(timeout, unit) 带超时版本
 *
 * 常用实现：
 *   ArrayBlockingQueue   底层数组，必须指定容量，有界
 *   LinkedBlockingQueue  底层链表，可选容量，默认无界（Integer.MAX_VALUE）
 *
 * 优势：把"加锁 + wait/notify"封装在内部，使用者只关心 put / take，代码极其简洁。
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        new Producer(queue).start();
        new Consumer(queue).start();
    }
}
