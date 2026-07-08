package com.itheima._01_jdk7_date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 知识点：字符串日期格式互转
 * <p>
 * 套路：用一个 SimpleDateFormat 按原格式 parse 成 Date，
 * 再用另一个 SimpleDateFormat 按目标格式 format 成 String。
 * <p>
 * 示例：把 "2000-11-11" 转换为 "2000年11月11日"
 */
public class _04_SimpleDateFormatTransform {
    public static void main(String[] args) throws ParseException {
        String src = "2000-11-11";

        SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
        Date date = in.parse(src);

        SimpleDateFormat out = new SimpleDateFormat("yyyy年MM月dd日");
        String result = out.format(date);

        System.out.println(result); // 2000年11月11日
    }
}
