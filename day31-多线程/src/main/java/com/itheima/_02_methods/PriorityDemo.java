package com.itheima._02_methods;

/**
 * 线程优先级：
 *   setPriority(int)  范围 1 ~ 10，默认 5
 *   getPriority()
 *
 * 注意：优先级只是给 CPU 调度的"建议"，并不保证高优先级一定先执行。
 */
public class PriorityDemo {
    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
            }
        };

        Thread t1 = new Thread(r, "低优先级");
        Thread t2 = new Thread(r, "高优先级");

        t1.setPriority(Thread.MIN_PRIORITY); // 1
        t2.setPriority(Thread.MAX_PRIORITY); // 10

        t1.start();
        t2.start();
    }
}
