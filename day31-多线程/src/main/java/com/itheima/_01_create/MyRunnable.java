package com.itheima._01_create;

/**
 * 方式二：实现 Runnable 接口
 * 优点：扩展性强，仍然可以继承其他类，还能实现其他接口
 * 缺点：编码相对复杂，没有返回值，不能直接抛出异常
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Runnable - " + i);
        }
    }
}
