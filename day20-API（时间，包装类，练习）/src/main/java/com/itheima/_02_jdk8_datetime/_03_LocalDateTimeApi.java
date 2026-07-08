package com.itheima._02_jdk8_datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 知识点：LocalDateTime = LocalDate + LocalTime（年月日时分秒）
 * <p>
 * 既有 LocalDate 的全部 get/with/plus/minus，也有 LocalTime 的全部方法。
 * <p>
 * 拓展：
 * - toLocalDate() / toLocalTime() 拆出对应部分
 * - LocalDateTime.of(LocalDate, LocalTime) 反向组合
 */
public class _03_LocalDateTimeApi {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("现在：" + now);

        System.out.println("年：" + now.getYear());
        System.out.println("月：" + now.getMonthValue());
        System.out.println("日：" + now.getDayOfMonth());
        System.out.println("时：" + now.getHour());
        System.out.println("分：" + now.getMinute());
        System.out.println("秒：" + now.getSecond());
        System.out.println("年中第几天：" + now.getDayOfYear());
        System.out.println("星期：" + now.getDayOfWeek().getValue());

        LocalDate ld = now.toLocalDate();
        LocalTime lt = now.toLocalTime();
        System.out.println("拆分日期部分：" + ld);
        System.out.println("拆分时间部分：" + lt);

        // 反向组合
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        System.out.println("重新组合：" + ldt);
    }
}
