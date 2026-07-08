package com.itheima._01_jdk7_date;

import java.util.Date;

/**
 * 知识点：JDK7 的 Date 类
 * <p>
 * 常用 API：
 * - public Date()               创建当前时间的 Date 对象
 * - public Date(long date)      使用毫秒值（自 1970-01-01 00:00:00 GMT 起）创建 Date
 * - public void setTime(long)   修改毫秒值
 * - public long getTime()       获取毫秒值
 * <p>
 * 拓展：
 * - Date 的 toString() 输出的是当前时区的时间，但底层存储的是 UTC 毫秒值
 * - 1 秒 = 1000 毫秒，1 天 = 1000L * 60 * 60 * 24 毫秒（注意 long 防止溢出）
 */
public class _01_DateApi {
    public static void main(String[] args) {
        // 1. 当前时间
        Date now = new Date();
        System.out.println("当前时间：" + now);

        // 2. 时间原点 1970-01-01 00:00:00 UTC（北京时间为 1970-01-01 08:00:00）
        Date origin = new Date(0L);
        System.out.println("时间原点：" + origin);

        // 3. setTime / getTime
        origin.setTime(1000L);
        System.out.println("加 1 秒：" + origin);
        System.out.println("毫秒值：" + origin.getTime());

        // 4. 拓展：在时间原点基础上加一年（使用 long 防溢出）
        Date oneYearLater = new Date(0L);
        oneYearLater.setTime(oneYearLater.getTime() + 1000L * 60 * 60 * 24 * 365);
        System.out.println("时间原点之后一年：" + oneYearLater);

        // 5. 拓展：当前时间的毫秒值也可以直接用 System.currentTimeMillis() 获取（性能更好）
        System.out.println("System.currentTimeMillis(): " + System.currentTimeMillis());
    }
}
