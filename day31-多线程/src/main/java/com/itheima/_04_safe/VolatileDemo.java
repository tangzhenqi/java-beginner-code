package com.itheima._04_safe;

/**
 * 【拓展】volatile 关键字
 *
 * 作用：
 *   1. 保证可见性：一个线程修改了变量，其他线程立刻能看到最新值（强制读主内存）
 *   2. 禁止指令重排序
 *
 * 注意：
 *   volatile 不保证原子性，例如 i++ 这种"读-改-写"操作依然需要 synchronized 或原子类。
 *
 * 经典场景：使用一个布尔标记来通知线程停止。
 */
public class VolatileDemo {

    // 如果去掉 volatile，子线程很可能永远读不到 main 线程对 flag 的修改，从而死循环。
    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("子线程开始工作...");
            while (running) {
                // 忙等 —— 真实项目中应替换为 LockSupport.park / 阻塞队列等
            }
            System.out.println("子线程检测到停止信号，退出");
        }, "worker");

        worker.start();
        Thread.sleep(1000);
        System.out.println("main 线程发出停止信号");
        running = false;
    }
}
