package com.itheima._02_methods;

/**
 * Thread 常见方法：
 *   String getName()                返回线程名（默认为 Thread-X）
 *   void setName(String)            设置线程名
 *   static Thread currentThread()   获取当前正在执行的线程对象
 *   static void sleep(long ms)      让当前线程休眠指定毫秒数
 *
 * 拓展：JVM 启动时会自动创建若干线程，其中 main 线程负责执行 main 方法。
 */
public class NameAndSleepDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread current = Thread.currentThread();
        System.out.println("当前线程名 = " + current.getName());  // main

        Thread t = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(500);  // 当前线程休眠 0.5s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " - " + i);
            }
        });
        t.setName("飞机");
        t.start();
    }
}
