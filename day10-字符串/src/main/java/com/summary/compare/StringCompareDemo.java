package com.summary.compare;

/**
 * 知识点 2：字符串的比较
 *
 *   ==                  比"地址"。对于 String 来说几乎不要用。
 *   equals(s)           比"内容"，区分大小写。
 *   equalsIgnoreCase(s) 比"内容"，忽略大小写（仅限英文字母 a/A，"1/一/壹" 这种不行）。
 *
 * 重要陷阱：
 *   键盘录入的字符串底层是 new 出来的（不在常量池），
 *   所以即便内容相同， sc.next() == "abc" 也是 false。
 *   要比内容，一律用 equals！
 */
public class StringCompareDemo {
    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = "Abc";

        System.out.println(s1 == s2);                // false
        System.out.println(s1.equals(s2));           // false（大小写不同）
        System.out.println(s1.equalsIgnoreCase(s2)); // true
    }
}
