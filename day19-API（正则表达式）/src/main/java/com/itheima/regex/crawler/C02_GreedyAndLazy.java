package com.itheima.regex.crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点 9：贪婪匹配 vs 非贪婪匹配
 * <p>
 * 默认情况下 + * {n,m} 都是 “贪婪” 的——尽可能多地匹配。
 * 在数量词后加 ? 变成 “非贪婪”——尽可能少地匹配。
 * <pre>
 *   ab+    贪婪：abbbbb...   抓取 b 越多越好
 *   ab+?   非贪婪：ab        只抓 1 个 b 就停
 * </pre>
 * 典型场景：解析 HTML / 字符串里的 “一对包裹符号”，避免抓过头。
 */
public class C02_GreedyAndLazy {
    public static void main(String[] args) {
        String text = "abbbbbbbbbbb 中间 ab 末尾 abbbb";

        System.out.println("---- 贪婪 ab+ ----");
        print(text, "ab+");

        System.out.println("---- 非贪婪 ab+? ----");
        print(text, "ab+?");

        // ---------- 拓展：解析 <b>...</b> 标签 ----------
        String html = "<b>Java</b> 和 <b>Regex</b>";

        System.out.println("---- 贪婪：<b>.+</b>（会把两个标签连起来抓） ----");
        print(html, "<b>.+</b>");

        System.out.println("---- 非贪婪：<b>.+?</b>（每个标签单独抓） ----");
        print(html, "<b>.+?</b>");
    }

    private static void print(String text, String regex) {
        Matcher m = Pattern.compile(regex).matcher(text);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
