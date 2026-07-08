package com.summary.stringbuilder;

/**
 * 知识点 6：StringBuilder（可变字符序列）
 *
 * 为什么有它？
 *   String 不可变 -> 每次 + 拼接都会 new 一个新对象，循环里拼 100 万次 = 创建 100 万个对象，性能极差。
 *   StringBuilder 内部维护一个 char[]，append 直接改这个数组，性能高。
 *
 * 常用方法：
 *   append(任何类型)   末尾追加，并返回自身（支持链式）
 *   reverse()         反转
 *   length()          长度
 *   toString()        变回 String
 *
 * 顺带说一句：
 *   StringBuilder 已经重写了 toString()，所以 System.out.println(sb) 打印的是内容而不是地址。
 */
public class StringBuilderBasic {
    public static void main(String[] args) {
        // 链式调用
        StringBuilder sb = new StringBuilder()
                .append("aaa")
                .append("bbb")
                .append(123)
                .append(true);

        System.out.println(sb);              // aaabbb123true
        System.out.println(sb.length());     // 13

        sb.reverse();
        System.out.println(sb);              // eurt321bbbaaa

        String str = sb.toString();          // StringBuilder -> String
        System.out.println(str);
    }
}
