package com.itheima._01_create;

/**
 * 方式一：继承 Thread 类
 * 优点：编码简单
 * 缺点：已经继承了 Thread，无法再继承别的类（Java 单继承）
 */
public class MyThread extends Thread {

    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + " - HelloWorld - " + i);
        }
    }
}
