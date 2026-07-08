package com.itheima._02_jdk8_datetime;

import java.time.LocalDate;
import java.time.Period;

/**
 * 知识点：Period 表示"两个 LocalDate 之间相差的 年-月-日"
 * <p>
 * 注意：Period 不能处理时分秒，只针对日期。
 * <p>
 * 常用 API：
 * - static Period between(LocalDate begin, LocalDate end)   end - begin
 * - getYears / getMonths / getDays                          各部分（非总数）
 * - toTotalMonths()                                         总月数
 */
public class _08_PeriodApi {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(2000, 1, 1);

        Period p = Period.between(birthday, today);
        System.out.println("间隔对象：" + p);

        System.out.println("年：" + p.getYears());
        System.out.println("月：" + p.getMonths());
        System.out.println("日：" + p.getDays());

        System.out.println("总月数：" + p.toTotalMonths());
    }
}
