package com.itheima._02_jdk8_datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 知识点：ZonedDateTime 带时区的日期时间
 * <p>
 * 常用 API：
 * - static ZonedDateTime now()                     当前时间（含系统默认时区）
 * - static ZonedDateTime of(年,月,日,时,分,秒,纳秒,ZoneId)   按指定时间构造
 * - static ZonedDateTime ofInstant(Instant, ZoneId)
 * - ZonedDateTime withXxx(...)                     修改年/月/日/...
 * - ZonedDateTime plusXxx(...) / minusXxx(...)     加减
 */
public class _05_ZonedDateTimeApi {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("当前：" + now);

        ZonedDateTime t1 = ZonedDateTime.of(2023, 10, 1,
                11, 12, 12, 0, ZoneId.of("Asia/Shanghai"));
        System.out.println("指定：" + t1);

        ZonedDateTime t2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.of("Asia/Shanghai"));
        System.out.println("由 Instant 构造：" + t2);

        System.out.println("withYear(2000)：" + t2.withYear(2000));
        System.out.println("minusYears(1) ：" + t2.minusYears(1));
        System.out.println("plusYears(1)  ：" + t2.plusYears(1));
    }
}
