package com.itheima._05_practice;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * 练习 5：判断闰年
 * <p>
 * 思路：把 3 月 1 日往前推一天，看是 2 月 28 日（平年）还是 29 日（闰年）。
 * 也可以直接用 LocalDate.isLeapYear()。
 * <p>
 * 数学规则（参考）：能被 4 整除但不能被 100 整除，或能被 400 整除 → 闰年。
 */
public class PracticeLeapYear {
    public static void main(String[] args) {
        System.out.println("2000 闰年？" + isLeapByJdk7(2000));
        System.out.println("2001 闰年？" + isLeapByJdk7(2001));
        System.out.println("2024 闰年？" + isLeapByJdk8(2024));
        System.out.println("1900 闰年？" + isLeapByJdk8(1900));
        System.out.println("2000 闰年？(规则) " + isLeapByRule(2000));
    }

    private static boolean isLeapByJdk7(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.MARCH, 1);   // Calendar 月份从 0 开始：2 表示 3 月
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.get(Calendar.DAY_OF_MONTH) == 29;
    }

    private static boolean isLeapByJdk8(int year) {
        return LocalDate.of(year, 1, 1).isLeapYear();
    }

    private static boolean isLeapByRule(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
