package com.itheima._06_waitnotify;

/**
 * 消费者：吃货
 */
public class Foodie extends Thread {

    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) break;

                if (Desk.foodFlag == 0) {
                    // 桌上没面条 -> 等厨师做
                    try { Desk.lock.wait(); } catch (InterruptedException ignored) {}
                } else {
                    Desk.count--;
                    System.out.println("吃货在吃面条，还能再吃 " + Desk.count + " 碗");
                    Desk.foodFlag = 0;
                    Desk.lock.notifyAll();
                }
            }
        }
    }
}
