package com.itheima._02_methods;

/**
 * Thread.yield()：礼让线程
 *   暂停当前线程，让 CPU 重新调度，但调度结果不可控（可能马上又选回自己）。
 *   只是一个"建议"，并不能精确控制顺序。
 */
public class YieldDemo {
    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                Thread.yield();
            }
        };
        new Thread(r, "飞机").start();
        new Thread(r, "坦克").start();
    }
}
