package com.itheima._04_extend;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 拓展：跨时区时间换算
 * <p>
 * 场景：北京时间 2024-01-01 12:00:00，对应纽约时间是几点？
 * <p>
 * 套路：
 *   1) 用 ZonedDateTime.of(本地时间, 源时区)
 *   2) 用 withZoneSameInstant(目标时区) 换算
 *      注意区分：
 *      - withZoneSameInstant：保持瞬时点不变，调整时区（"几点"会变）
 *      - withZoneSameLocal  ：保持本地时间字段不变，仅替换时区（瞬时点会变）
 */
public class ZoneConversionDemo {
    public static void main(String[] args) {
        LocalDateTime local = LocalDateTime.of(2024, 1, 1, 12, 0, 0);
        ZoneId beijing = ZoneId.of("Asia/Shanghai");
        ZoneId newYork = ZoneId.of("America/New_York");

        ZonedDateTime bj = ZonedDateTime.of(local, beijing);
        System.out.println("北京：" + bj);

        ZonedDateTime ny1 = bj.withZoneSameInstant(newYork);
        System.out.println("纽约（同一瞬时）：" + ny1);

        ZonedDateTime ny2 = bj.withZoneSameLocal(newYork);
        System.out.println("纽约（本地不变）：" + ny2);
    }
}
