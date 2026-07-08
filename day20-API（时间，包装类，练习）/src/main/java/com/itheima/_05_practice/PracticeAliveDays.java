package com.itheima._05_practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 练习 4：计算活了多少天
 * <p>
 * 两种实现：
 * - JDK7：SimpleDateFormat 解析生日 → 毫秒值差 / 一天的毫秒
 * - JDK8：LocalDate + ChronoUnit.DAYS.between
 */
public class PracticeAliveDays {
    public static void main(String[] args) throws ParseException {
        String birthday = "2000年1月1日";

        System.out.println("JDK7 方式：" + jdk7(birthday));
        System.out.println("JDK8 方式：" + jdk8(2000, 1, 1));
    }

    private static long jdk7(String birthday) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date bd = sdf.parse(birthday);
        long diff = System.currentTimeMillis() - bd.getTime();
        return diff / 1000 / 60 / 60 / 24;
    }

    private static long jdk8(int y, int m, int d) {
        return ChronoUnit.DAYS.between(LocalDate.of(y, m, d), LocalDate.now());
    }
}
