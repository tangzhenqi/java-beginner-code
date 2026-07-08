package com.itheima.regex.crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点 10：零宽断言（Lookaround）
 * <p>
 * 这些断言只是 “看一眼”，不会把字符消费到匹配结果里——也叫 “非捕获分组” 的特殊形式。
 * <ul>
 *     <li>(?=X)  正向先行：后面紧跟 X，但 X 不计入匹配</li>
 *     <li>(?!X)  反向先行：后面不能紧跟 X</li>
 *     <li>(?:X)  非捕获组：把 X 括起来但不分配组号</li>
 * </ul>
 * Java 也支持后行断言：(?&lt;=X) 和 (?&lt;!X)，要求 X 是定长。
 */
public class C03_Lookaround {
    public static void main(String[] args) {
        String text = "Java5、Java8 和 Java11，"
                + "下一个长期支持版本是 Java17，Java17 也会逐渐登场";

        // ---------- 1. (?=...)：只要 Java 后面跟 8/11/17，匹配结果只含 "Java" ----------
        print(text, "(?i)Java(?=8|11|17)");

        // ---------- 2. (?:...)：匹配结果包含版本号，但不单独成组 ----------
        print(text, "(?i)Java(?:8|11|17)");

        // ---------- 3. (?!...)：Java 后面 不是 8/11/17 ----------
        print(text, "(?i)Java(?!8|11|17)");

        // ---------- 4. 普通捕获组对比：(8|11|17) 会占据组号 1 ----------
        Matcher m = Pattern.compile("(?i)Java(8|11|17)").matcher(text);
        while (m.find()) {
            System.out.println("整体=" + m.group(0) + ", 版本组=" + m.group(1));
        }
    }

    private static void print(String text, String regex) {
        System.out.println("---- " + regex + " ----");
        Matcher m = Pattern.compile(regex).matcher(text);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
