package com.summary._01_sync_practice;

/**
 * 场景：1000 张电影票，两个窗口（线程）同时售卖，每次售出耗时 3 毫秒。
 * <p>
 * 实现方式：继承 Thread。
 * 由于每个线程是不同的对象，共享数据必须用 static 修饰，否则各持各的"票"。
 * 同步代码块的锁必须唯一，使用类的 Class 对象（MyThread.class）。
 */
public class TicketSellingDemo {

    public static void main(String[] args) {
        Ticket t1 = new Ticket();
        Ticket t2 = new Ticket();
        t1.setName("窗口1");
        t2.setName("窗口2");
        t1.start();
        t2.start();
    }

    /** 共享数据放在 static 字段。 */
    static class Ticket extends Thread {
        static int ticket = 1000;

        @Override
        public void run() {
            while (true) {
                synchronized (Ticket.class) {
                    if (ticket == 0) {
                        break;
                    }
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    ticket--;
                    System.out.println(getName() + " 卖票，还剩 " + ticket + " 张");
                }
            }
        }
    }
}
