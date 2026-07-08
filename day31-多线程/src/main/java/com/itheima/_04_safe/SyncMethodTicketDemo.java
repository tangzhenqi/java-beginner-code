package com.itheima._04_safe;

/**
 * 卖票案例 —— 方案二：synchronized 同步方法
 *
 * 同步方法的锁对象：
 *   - 非静态方法：this
 *   - 静态方法：当前类的字节码对象（类名.class）
 */
public class SyncMethodTicketDemo implements Runnable {

    private int ticket = 0;
    private static final int TOTAL = 100;

    @Override
    public void run() {
        while (true) {
            if (sellOne()) break;
        }
    }

    /** synchronized 修饰非静态方法 —— 锁对象是 this */
    private synchronized boolean sellOne() {
        if (ticket >= TOTAL) return true;
        try { Thread.sleep(5); } catch (InterruptedException ignored) {}
        ticket++;
        System.out.println(Thread.currentThread().getName() + " 卖出第 " + ticket + " 张票");
        return false;
    }

    public static void main(String[] args) {
        SyncMethodTicketDemo task = new SyncMethodTicketDemo();
        new Thread(task, "窗口1").start();
        new Thread(task, "窗口2").start();
        new Thread(task, "窗口3").start();
    }
}
