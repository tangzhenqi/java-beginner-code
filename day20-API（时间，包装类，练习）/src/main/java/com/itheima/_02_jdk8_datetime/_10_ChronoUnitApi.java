package com.itheima._02_jdk8_datetime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 知识点：ChronoUnit 万能间隔单位
 * <p>
 * 优势：覆盖范围最广，年/月/周/天/时/分/秒/毫秒/微秒/纳秒/半天/十年/百年/千年/纪元 都能算。
 * <p>
 * 调用形式：ChronoUnit.DAYS.between(begin, end)
 */
public class _10_ChronoUnitApi {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime birth = LocalDateTime.of(2000, 1, 1, 0, 0, 0);

        System.out.println("年：" + ChronoUnit.YEARS.between(birth, now));
        System.out.println("月：" + ChronoUnit.MONTHS.between(birth, now));
        System.out.println("周：" + ChronoUnit.WEEKS.between(birth, now));
        System.out.println("天：" + ChronoUnit.DAYS.between(birth, now));
        System.out.println("时：" + ChronoUnit.HOURS.between(birth, now));
        System.out.println("分：" + ChronoUnit.MINUTES.between(birth, now));
        System.out.println("秒：" + ChronoUnit.SECONDS.between(birth, now));
        System.out.println("毫秒：" + ChronoUnit.MILLIS.between(birth, now));
        System.out.println("微秒：" + ChronoUnit.MICROS.between(birth, now));
        System.out.println("纳秒：" + ChronoUnit.NANOS.between(birth, now));
        System.out.println("半天：" + ChronoUnit.HALF_DAYS.between(birth, now));
        System.out.println("十年：" + ChronoUnit.DECADES.between(birth, now));
        System.out.println("百年：" + ChronoUnit.CENTURIES.between(birth, now));
        System.out.println("千年：" + ChronoUnit.MILLENNIA.between(birth, now));
        System.out.println("纪元：" + ChronoUnit.ERAS.between(birth, now));
    }
}
