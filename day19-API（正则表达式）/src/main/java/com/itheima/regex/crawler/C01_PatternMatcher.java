package com.itheima.regex.crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点 8：Pattern + Matcher —— 从大串里 “找” 子串
 * <p>
 * 与 String.matches 的对比：
 * <ul>
 *     <li>String.matches(regex)：整串匹配，返回 boolean</li>
 *     <li>Pattern + Matcher    ：在大串中 “定位 + 抽取” 所有符合规则的子串</li>
 * </ul>
 * 三步走：
 * <pre>
 *   1. Pattern p = Pattern.compile(regex);   // 把正则 “编译” 成对象（可复用，避免重复解析）
 *   2. Matcher m = p.matcher(text);          // 把大串交给匹配器
 *   3. while (m.find()) { m.group(); }       // 循环找下一个匹配，group() 取出本次匹配的子串
 * </pre>
 * find() 内部维护一个游标（起始/结束索引），每调用一次就向后推进。
 */
public class C01_PatternMatcher {
    public static void main(String[] args) {
        String text = "Java 自从 95 年问世以来，经历了很多版本，"
                + "目前企业中用的最多的是 Java8 和 Java11，"
                + "下一个长期支持版本是 Java17，Java17 也会逐渐登上历史舞台";

        Pattern p = Pattern.compile("Java\\d{0,2}");
        Matcher m = p.matcher(text);

        // 1) 找所有匹配
        while (m.find()) {
            // group() = group(0)，整体匹配的子串
            // start() / end() 给出在大串中的位置
            System.out.println(m.group() + "  [" + m.start() + "," + m.end() + ")");
        }

        System.out.println("--------");

        // 2) 拓展：分组捕获 group(n)
        //   把版本号单独捕获出来
        Pattern p2 = Pattern.compile("(Java)(\\d{0,2})");
        Matcher m2 = p2.matcher(text);
        while (m2.find()) {
            // group(0) 整体；group(1) 第 1 个 ()；group(2) 第 2 个 ()
            System.out.println("整体=" + m2.group(0)
                    + ", 名称=" + m2.group(1)
                    + ", 版本=" + m2.group(2));
        }
    }
}
