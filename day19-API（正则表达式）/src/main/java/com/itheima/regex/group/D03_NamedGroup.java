package com.itheima.regex.group;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点 15（拓展）：命名分组 (?&lt;name&gt;X)
 * <p>
 * 用数字组号在表达式变复杂后非常难维护，Java 7+ 支持给分组起名字：
 * <pre>
 *   (?&lt;year&gt;\\d{4})   命名为 year
 *   m.group("year")    取出
 *   \\k&lt;year&gt;          在正则内部反向引用
 *   ${year}            在 replaceAll 替换串里引用
 * </pre>
 */
public class D03_NamedGroup {
    public static void main(String[] args) {
        String date = "今天是 2026-05-25，明天是 2026-05-26";

        Pattern p = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})");
        Matcher m = p.matcher(date);
        while (m.find()) {
            System.out.println("year=" + m.group("year")
                    + ", month=" + m.group("month")
                    + ", day=" + m.group("day"));
        }

        // ---------- 反向引用 \k<name> ----------
        // 判断 “xxx@xxx” 形式中 @ 两侧 字符串完全相同
        String mirror = "(?<word>\\w+)@\\k<word>";
        System.out.println("hello@hello".matches(mirror)); // true
        System.out.println("hello@world".matches(mirror)); // false

        // ---------- 替换串中用 ${name} ----------
        // 把 yyyy-MM-dd 改成 dd/MM/yyyy
        String replaced = date.replaceAll(
                "(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})",
                "${day}/${month}/${year}");
        System.out.println(replaced);
    }
}
