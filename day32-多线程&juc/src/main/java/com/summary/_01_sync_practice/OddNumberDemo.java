package com.summary._01_sync_practice;

/**
 * 场景：两个线程协作打印 1~100 之间的所有奇数。
 * <p>
 * 关键：把 "判断 + 自增" 整体放进同步代码块，
 * 不然一旦多个线程同时读到同一个 number，会出现重复或漏打。
 */
public class OddNumberDemo {

    public static void main(String[] args) {
        OddTask task = new OddTask();
        new Thread(task, "线程A").start();
        new Thread(task, "线程B").start();
    }

    static class OddTask implements Runnable {
        int number = 1;

        @Override
        public void run() {
            while (true) {
                synchronized (OddTask.class) {
                    if (number > 100) {
                        break;
                    }
                    if (number % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " 打印 " + number);
                    }
                    number++;
                }
            }
        }
    }
}
