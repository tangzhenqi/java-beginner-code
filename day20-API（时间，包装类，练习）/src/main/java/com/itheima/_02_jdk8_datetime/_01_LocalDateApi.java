package com.itheima._02_jdk8_datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;

/**
 * 知识点：LocalDate 只表示"年-月-日"
 * <p>
 * 常用 API：
 * - static LocalDate now() / of(year, month, day)
 * - getYear / getMonth / getMonthValue / getDayOfMonth / getDayOfYear / getDayOfWeek
 * - isBefore / isAfter / isEqual
 * - withYear / withMonth / withDayOfMonth     修改返回新对象
 * - plusXxx / minusXxx                       加减返回新对象
 * <p>
 * 拓展：
 * - 配合 MonthDay 可以快速实现"判断是否生日"。
 * - DayOfWeek 是个枚举，可以拿到中文名（结合 Locale）。
 */
public class _01_LocalDateApi {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("今天：" + today);

        LocalDate d = LocalDate.of(2023, 1, 1);

        // get 系列
        System.out.println("年：" + d.getYear());
        Month m = d.getMonth();
        System.out.println("月（枚举）：" + m + "  月份值：" + m.getValue());
        System.out.println("月（int）：" + d.getMonthValue());
        System.out.println("日：" + d.getDayOfMonth());
        System.out.println("年中第几天：" + d.getDayOfYear());

        DayOfWeek w = d.getDayOfWeek();
        System.out.println("星期（枚举）：" + w + "  值：" + w.getValue());

        // 判断、修改、加减
        System.out.println(d.isBefore(today));
        System.out.println(d.withYear(2000));
        System.out.println(d.minusYears(1));
        System.out.println(d.plusDays(1));

        // 拓展：是否今天生日
        LocalDate birthday = LocalDate.of(2000, 1, 1);
        boolean isBirthday = MonthDay.from(today).equals(MonthDay.of(birthday.getMonthValue(), birthday.getDayOfMonth()));
        System.out.println("今天是生日吗？ " + isBirthday);
    }
}
