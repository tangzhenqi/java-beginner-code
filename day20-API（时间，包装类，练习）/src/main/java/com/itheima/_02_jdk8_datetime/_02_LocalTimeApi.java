package com.itheima._02_jdk8_datetime;

import java.time.LocalTime;

/**
 * 知识点：LocalTime 只表示"时-分-秒-纳秒"
 * <p>
 * 常用 API：
 * - now() / of(时, 分) / of(时, 分, 秒) / of(时, 分, 秒, 纳秒)
 * - getHour / getMinute / getSecond / getNano
 * - isBefore / isAfter
 * - withHour / withMinute / withSecond / withNano
 * - plusHours / minusHours / ...
 */
public class _02_LocalTimeApi {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println("现在：" + now);

        System.out.println("时：" + now.getHour());
        System.out.println("分：" + now.getMinute());
        System.out.println("秒：" + now.getSecond());
        System.out.println("纳秒：" + now.getNano());

        System.out.println(LocalTime.of(8, 20));
        System.out.println(LocalTime.of(8, 20, 30));
        System.out.println(LocalTime.of(8, 20, 30, 150));

        LocalTime t = LocalTime.of(8, 20, 30);
        System.out.println("isBefore: " + now.isBefore(t));
        System.out.println("withHour(10): " + now.withHour(10));
        System.out.println("plusHours(10): " + now.plusHours(10));
    }
}
