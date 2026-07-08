package com.itheima._06_waitnotify;

/**
 * 生产者：厨师
 *
 * wait()      让当前线程释放锁并等待，直到被 notify
 * notifyAll() 唤醒所有等待在该锁上的线程（推荐，避免假死）
 */
public class Cook extends Thread {

    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) break;

                if (Desk.foodFlag == 1) {
                    // 桌上有面条 -> 等吃货来吃
                    try { Desk.lock.wait(); } catch (InterruptedException ignored) {}
                } else {
                    System.out.println("厨师做了一碗面条");
                    Desk.foodFlag = 1;
                    Desk.lock.notifyAll();
                }
            }
        }
    }
}
