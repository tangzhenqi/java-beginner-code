package com.summary._01_sync_practice;

/**
 * 场景：100 份礼物，两人同时赠送，当剩余不足 10 份时停止赠送。
 * <p>
 * 实现方式：实现 Runnable。
 * 同一个 Runnable 对象会被多个 Thread 共享，所以共享数据可以放在成员变量上，无需 static。
 */
public class GiftSendingDemo {

    public static void main(String[] args) {
        GiftTask task = new GiftTask();
        new Thread(task, "窗口1").start();
        new Thread(task, "窗口2").start();
    }

    static class GiftTask implements Runnable {
        int count = 100;

        @Override
        public void run() {
            while (true) {
                synchronized (GiftTask.class) {
                    if (count < 10) {
                        System.out.println("礼物还剩 " + count + " 份，不再赠送");
                        break;
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + " 赠送礼物，还剩 " + count + " 份");
                }
            }
        }
    }
}
