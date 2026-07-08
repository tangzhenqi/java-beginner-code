package com.itheima._04_extend;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * 拓展：TemporalAdjusters 时间矫正器
 * <p>
 * 配合 LocalDate.with(...) 一行代码就能获得：
 *   - 本月第一天 / 最后一天
 *   - 本月第一个星期一 / 下个星期一
 *   - 下个月第一天 / 今年最后一天 ...
 * <p>
 * 不用再写复杂的 +/- 计算了。
 */
public class TemporalAdjusterDemo {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("今天：" + today);

        System.out.println("本月第一天：" + today.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("本月最后一天：" + today.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("下月第一天：" + today.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println("今年最后一天：" + today.with(TemporalAdjusters.lastDayOfYear()));

        System.out.println("本月第一个周一：" + today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        System.out.println("下一个周五：" + today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
        System.out.println("上一个周日：" + today.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)));
    }
}
