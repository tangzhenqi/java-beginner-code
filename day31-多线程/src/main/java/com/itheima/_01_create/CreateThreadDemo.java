package com.itheima._01_create;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 演示三种创建多线程的方式
 *
 * 1. 继承 Thread          —— 重写 run，直接 start()
 * 2. 实现 Runnable        —— 把任务包装进 Thread 后 start()
 * 3. 实现 Callable + FutureTask —— 可获取结果，可抛异常
 *
 * 拓展：JDK 8 之后 Runnable 是函数式接口，可以用 Lambda 表达式直接写
 */
public class CreateThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // ---- 方式一：继承 Thread ----
        MyThread t1 = new MyThread("线程1");
        t1.start();

        // ---- 方式二：实现 Runnable ----
        MyRunnable mr = new MyRunnable();
        new Thread(mr, "线程2").start();

        // ---- 方式三：Callable + FutureTask ----
        FutureTask<Integer> ft = new FutureTask<>(new MyCallable(100));
        new Thread(ft, "线程3").start();
        Integer result = ft.get();  // 阻塞等待，直到任务执行完毕
        System.out.println("线程3 求和结果 = " + result);

        // ---- 拓展：Lambda 写法 ----
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " - Lambda - " + i);
            }
        }, "线程4").start();
    }
}
