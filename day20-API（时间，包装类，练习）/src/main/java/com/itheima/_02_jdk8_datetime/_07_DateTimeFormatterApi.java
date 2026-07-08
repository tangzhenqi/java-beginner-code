package com.itheima._02_jdk8_datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 知识点：DateTimeFormatter（JDK8 新版格式化器）
 * <p>
 * 相对 SimpleDateFormat 的优势：
 * 1. 线程安全（不可变）。
 * 2. API 流畅，与 LocalDateTime / ZonedDateTime 配合使用。
 * <p>
 * 常用 API：
 * - static DateTimeFormatter ofPattern(String)
 * - String format(TemporalAccessor)
 * - TemporalAccessor parse(CharSequence)（一般通过 LocalDateTime.parse(text, fmt) 调用）
 */
public class _07_DateTimeFormatterApi {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EE a");

        // 格式化
        String text = fmt.format(now);
        System.out.println("格式化：" + text);

        // 也可以反向调用：now.format(fmt)
        System.out.println("流式调用：" + now.format(fmt));

        // 解析
        String src = "2024-05-01 12:30:00";
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsed = LocalDateTime.parse(src, fmt2);
        System.out.println("解析：" + parsed);
    }
}
