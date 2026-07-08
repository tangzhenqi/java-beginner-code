package com.itheima._01_jdk7_date;

import java.util.Calendar;
import java.util.Date;

/**
 * 知识点：Calendar 日历类
 * <p>
 * 常用 API：
 * - static Calendar getInstance()         获取系统时区对应的子类对象
 * - Date getTime() / void setTime(Date)   日历 ↔ Date 互转
 * - long getTimeInMillis() / setTimeInMillis(long)
 * - int  get(int field)                   读取某字段
 * - void set(int field, int value)        设置某字段
 * - void add(int field, int amount)       字段加/减
 * <p>
 * 关键细节：
 * 1. Calendar 是抽象类，必须通过 getInstance() 获取子类（一般是 GregorianCalendar）。
 * 2. 月份范围 0~11，使用时需要 +1 才是真实月份。
 * 3. 星期：1=星期日，2=星期一 ... 7=星期六（外国习惯星期天是一周第一天）。
 * <p>
 * 拓展技巧（查表法）：
 * 用数组把"序号"和"展示文本"对应起来，避免一堆 if-else。
 */
public class _06_CalendarApi {
    public static void main(String[] args) {
        // 1. 获取日历对象（默认当前时区、当前时间）
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(0L));

        // 2. 修改字段
        c.set(Calendar.YEAR, 2023);
        c.set(Calendar.MONTH, 8);          // 9 月
        c.set(Calendar.DAY_OF_MONTH, 10);

        // 3. 加减字段：当前月份 -1
        c.add(Calendar.MONTH, -1);

        // 4. 读取字段
        int year  = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;       // 月份需 +1
        int day   = c.get(Calendar.DAY_OF_MONTH);
        int week  = c.get(Calendar.DAY_OF_WEEK);     // 1~7
        System.out.println(year + "-" + month + "-" + day + " " + weekText(week));

        // 5. 拓展：转回 Date
        Date date = c.getTime();
        System.out.println("对应 Date：" + date);
    }

    /** 查表法把 1~7 转成中文星期 */
    private static String weekText(int index) {
        String[] arr = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return arr[index];
    }
}
