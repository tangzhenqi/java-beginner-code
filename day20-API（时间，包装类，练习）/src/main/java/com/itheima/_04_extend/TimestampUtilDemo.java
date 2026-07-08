package com.itheima._04_extend;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 拓展：时间戳与各 API 的相互转换
 * <p>
 * 常见的"互通"路线：
 *   long(毫秒) <-> Date <-> Instant <-> LocalDateTime / ZonedDateTime
 */
public class TimestampUtilDemo {
    public static void main(String[] args) {
        // 1. 当前毫秒值
        long millis = System.currentTimeMillis();
        System.out.println("毫秒值：" + millis);

        // 2. millis ↔ Date
        Date date = new Date(millis);
        System.out.println("Date：" + date);
        long backToMillis = date.getTime();
        System.out.println("Date → 毫秒值：" + backToMillis);

        // 3. millis ↔ Instant
        Instant instant = Instant.ofEpochMilli(millis);
        System.out.println("Instant：" + instant);
        System.out.println("Instant → 毫秒值：" + instant.toEpochMilli());

        // 4. Date ↔ Instant（JDK8 桥梁方法）
        Instant fromDate = date.toInstant();
        Date fromInstant = Date.from(instant);
        System.out.println("Date↔Instant 完成：" + fromDate + " / " + fromInstant);

        // 5. Instant ↔ LocalDateTime（必须指定时区）
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("LocalDateTime：" + ldt);
        Instant back = ldt.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println("LocalDateTime → Instant：" + back);
    }
}
