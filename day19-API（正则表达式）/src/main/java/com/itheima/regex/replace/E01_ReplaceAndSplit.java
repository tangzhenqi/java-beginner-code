package com.itheima.regex.replace;

import java.util.Arrays;

/**
 * 知识点 16：String 中的正则方法 —— replaceAll / replaceFirst / split
 * <p>
 * <ul>
 *     <li>replaceAll(regex, repl)   把所有匹配到的子串替换成 repl</li>
 *     <li>replaceFirst(regex, repl) 只替换第一个匹配</li>
 *     <li>split(regex)              用匹配子串当 “分隔符” 切割</li>
 *     <li>split(regex, limit)       限制切割段数；负数保留尾部空串</li>
 * </ul>
 */
public class E01_ReplaceAndSplit {
    public static void main(String[] args) {
        String s = "小诗诗 dqwefqwfqwfwq12312 小丹丹 dqwefqwfqwfwq12312 小惠惠";

        // ---------- 1. replaceAll ----------
        // 把姓名之间的 “字母数字串”（不含下划线、不含空格）替换成 vs
        // [\\w&&[^_]]+ 表示字母数字（排除下划线）+ 至少一个
        // \\s* 兼容两侧的空格
        String result = s.replaceAll("\\s*[\\w&&[^_]]+\\s*", " vs ");
        System.out.println(result.trim()); // 小诗诗 vs 小丹丹 vs 小惠惠

        // ---------- 2. split ----------
        String[] names = s.split("\\s*[\\w&&[^_]]+\\s*");
        System.out.println(Arrays.toString(names));

        // ---------- 3. split 的 limit 细节 ----------
        String csv = "a,b,,c,,";
        System.out.println(Arrays.toString(csv.split(",")));       // [a, b, , c]   ← 默认丢弃尾部空串
        System.out.println(Arrays.toString(csv.split(",", -1)));   // [a, b, , c, , ] 保留全部
        System.out.println(Arrays.toString(csv.split(",", 3)));    // [a, b, ,c,,]  最多切 3 段

        // ---------- 4. replaceFirst ----------
        System.out.println("a1b2c3".replaceFirst("\\d", "#")); // a#b2c3
    }
}
