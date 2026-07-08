package com.summary._04_executors;

/**
 * 通用的打印任务：用于演示线程池如何"复用线程"。
 * 注意观察控制台中线程名（pool-1-thread-N），同一个线程会被反复使用。
 */
public class PrintTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " --- " + i);
        }
    }
}
