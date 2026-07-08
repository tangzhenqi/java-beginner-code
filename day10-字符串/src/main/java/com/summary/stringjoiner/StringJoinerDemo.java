package com.summary.stringjoiner;

import java.util.StringJoiner;

/**
 * 知识点 7：StringJoiner（JDK 8+）
 *
 * 专门用来"带分隔符 + 前后缀"拼接字符串：
 *   - 一个参数构造：只指定分隔符
 *       new StringJoiner(", ")
 *       结果：a, b, c
 *
 *   - 三个参数构造：分隔符 + 前缀 + 后缀
 *       new StringJoiner(", ", "[", "]")
 *       结果：[a, b, c]
 *
 * 比 StringBuilder 简洁，比 String + 高效。是"拼接打印列表"的最佳选择。
 *
 * 拓展：String.join(", ", "a", "b", "c") 也能得到 "a, b, c"。
 */
public class StringJoinerDemo {
    public static void main(String[] args) {
        // 仅分隔符
        StringJoiner sj1 = new StringJoiner("---");
        sj1.add("aaa").add("bbb").add("ccc");
        System.out.println(sj1);   // aaa---bbb---ccc

        // 分隔符 + 前后缀
        StringJoiner sj2 = new StringJoiner(", ", "[", "]");
        sj2.add("aaa").add("bbb").add("ccc");
        System.out.println(sj2);   // [aaa, bbb, ccc]
        System.out.println("长度：" + sj2.length());

        // String.join：一行搞定（拓展）
        String s = String.join(" | ", "a", "b", "c");
        System.out.println(s);     // a | b | c
    }
}
