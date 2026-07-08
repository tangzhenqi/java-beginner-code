package com.itheima._02_jdk8_datetime;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 知识点：Duration 表示"两个时间点之间的时长"
 * <p>
 * 与 Period 的区别：
 * - Period 处理 LocalDate，关注年月日。
 * - Duration 处理 LocalDateTime / Instant，关注时分秒纳秒。
 * <p>
 * 常用 API：
 * - static Duration between(begin, end)    end - begin
 * - toDays / toHours / toMinutes / toMillis / toNanos
 */
public class _09_DurationApi {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime birth = LocalDateTime.of(2000, 1, 1, 0, 0, 0);

        Duration d = Duration.between(birth, now);
        System.out.println("间隔对象：" + d);
        System.out.println("天：" + d.toDays());
        System.out.println("小时：" + d.toHours());
        System.out.println("分：" + d.toMinutes());
        System.out.println("毫秒：" + d.toMillis());
        System.out.println("纳秒：" + d.toNanos());
    }
}
