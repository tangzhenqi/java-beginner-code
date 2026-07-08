package com.itheima._01_jdk7_date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 知识点：SimpleDateFormat 的格式化与解析
 * <p>
 * 常用 API：
 * - SimpleDateFormat()               默认格式
 * - SimpleDateFormat(String pattern) 指定格式
 * - String format(Date)              日期对象 → 字符串
 * - Date parse(String)               字符串 → 日期对象（受检异常）
 * <p>
 * 常用模式字母：
 * y 年   M 月   d 日   H 24小时   h 12小时   m 分   s 秒
 * E 星期   a 上午/下午   S 毫秒
 * <p>
 * 关键细节：
 * - parse 时，构造的 pattern 必须和字符串格式 完全一致（包括分隔符）。
 * - SimpleDateFormat 是非线程安全的，多线程下要么每次 new，要么使用 ThreadLocal。
 */
public class _03_SimpleDateFormatApi {
    public static void main(String[] args) throws ParseException {
        // 1. 格式化：Date → String
        Date d = new Date(0L);
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        System.out.println("默认格式：" + sdf1.format(d));

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EE a");
        System.out.println("指定格式：" + sdf2.format(d));

        // 2. 解析：String → Date
        String text = "2023-11-11 11:11:11";
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsed = sdf3.parse(text);
        System.out.println("解析后毫秒值：" + parsed.getTime());
    }
}
