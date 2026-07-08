package com.itheima._02_jdk8_datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 知识点：Instant 时间戳（瞬时点）
 * <p>
 * Instant 是绝对时间，永远以 UTC 表示，等价于 JDK7 Date 但更精确（纳秒）且不可变。
 * <p>
 * 常用 API：
 * - static Instant now()                              当前时间
 * - static Instant ofEpochMilli(long) / ofEpochSecond 通过毫秒/秒构造
 * - ZonedDateTime atZone(ZoneId)                      加时区，转成本地日期时间
 * - boolean isBefore/isAfter(Instant)                 比较
 * - Instant plusXxx/minusXxx                          加减
 * <p>
 * 拓展：JDK8 时间类都是不可变的，加减操作会返回新对象。
 */
public class _06_InstantApi {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println("当前 Instant：" + now);

        Instant origin = Instant.ofEpochMilli(0L);
        System.out.println("时间原点：" + origin);

        // Instant + ZoneId → ZonedDateTime
        ZonedDateTime zdt = now.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("加上时区：" + zdt);

        // 判断
        Instant a = Instant.ofEpochMilli(0L);
        Instant b = Instant.ofEpochMilli(1000L);
        System.out.println("a.isBefore(b): " + a.isBefore(b));
        System.out.println("a.isAfter(b):  " + a.isAfter(b));

        // 加减（返回新对象，原对象不变）
        Instant t = Instant.ofEpochMilli(3000L);
        System.out.println("t.minusSeconds(1): " + t.minusSeconds(1));
        System.out.println("t 本身不变： " + t);
    }
}
