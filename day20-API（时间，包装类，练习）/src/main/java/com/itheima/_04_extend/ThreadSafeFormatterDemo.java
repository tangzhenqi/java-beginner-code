package com.itheima._04_extend;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 拓展：格式化器的线程安全
 * <p>
 * - SimpleDateFormat 内部维护可变状态（Calendar 字段），并发使用会出现：
 *   解析结果错乱、NumberFormatException、空指针等问题。
 * - DateTimeFormatter 是不可变的，天然线程安全。
 * <p>
 * 多线程场景推荐：
 *   1) 用 DateTimeFormatter（JDK8+）。
 *   2) 必须用 SimpleDateFormat 时，用 ThreadLocal 或每次 new。
 */
public class ThreadSafeFormatterDemo {

    // 线程安全：定义为静态常量即可，多线程共享
    private static final DateTimeFormatter SAFE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 非线程安全：示例用，实际生产不要这样写
    private static final SimpleDateFormat UNSAFE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 推荐写法：ThreadLocal 包一层
    private static final ThreadLocal<SimpleDateFormat> TL =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(50);

        for (int i = 0; i < 50; i++) {
            pool.submit(() -> {
                try {
                    // 线程安全
                    String s1 = LocalDateTime.now().format(SAFE);
                    // ThreadLocal 方式
                    String s2 = TL.get().format(new Date());
                    // 共享 SimpleDateFormat（不安全）：高并发下可能抛异常
                    String s3;
                    synchronized (UNSAFE) {     // 这里加锁规避演示崩溃
                        s3 = UNSAFE.format(new Date());
                    }
                    if (s1 == null || s2 == null || s3 == null) {
                        System.out.println("出现空值");
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        pool.shutdown();
        System.out.println("演示完成");
    }
}
