package com.itheima._02_methods;

/**
 * Thread.join()：插队线程
 *   在哪个线程里调用 t.join()，就让 t "先执行完"，再继续执行调用线程。
 *   常用于：主线程等待所有子线程结束后再做汇总。
 *
 * 拓展：join(long ms) 可以设置最长等待时间。
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("子线程 - " + i);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        }, "土豆");

        t.start();
        t.join();  // main 线程在此阻塞，等待 t 执行结束

        for (int i = 1; i <= 5; i++) {
            System.out.println("main - " + i);
        }
    }
}
