package com.itheima._02_methods;

/**
 * 守护线程：
 *   setDaemon(true) 必须在 start() 之前调用。
 *   当所有非守护线程结束后，JVM 会强制停止守护线程。
 *
 * 形象比喻：女神（非守护）下线，备胎（守护）也就没意义了。
 *
 * 典型应用：JVM 的垃圾回收线程 (GC) 就是守护线程。
 */
public class DaemonDemo {
    public static void main(String[] args) {
        Thread goddess = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("女神 - " + i);
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            }
        }, "女神");

        Thread backup = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("备胎 - " + i);
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            }
        }, "备胎");

        backup.setDaemon(true);  // 把备胎设为守护线程

        goddess.start();
        backup.start();
    }
}
