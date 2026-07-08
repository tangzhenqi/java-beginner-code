package com.summary.creation;

/**
 * 知识点 1：String 的 4 种创建方式
 *
 *   1. 直接赋值      String s = "abc";                  ← 进字符串常量池
 *   2. 空参 new      String s = new String();
 *   3. 字符串 new    String s = new String("abc");
 *   4. char[] new    String s = new String(new char[]{'a','b','c'});
 *   5. byte[] new    String s = new String(new byte[]{97,98,99});  ← 常用于网络字节流转字符串
 */
public class StringCreationDemo {
    public static void main(String[] args) {
        // 1) 直接赋值
        String s1 = "abc";

        // 2) 空参构造 -> 空白字符串
        String s2 = new String();

        // 3) 用一个字符串再 new 一个
        String s3 = new String("abc");

        // 4) 字符数组（后续可对数组改动再 new String，达到"修改"字符串的效果）
        char[] chs = {'a', 'b', 'c', 'd'};
        chs[0] = 'Q';
        String s4 = new String(chs);

        // 5) 字节数组（97 -> 'a'）。网络传输的数据通常是字节，要转成字符串就用它。
        byte[] bytes = {97, 98, 99, 100};
        String s5 = new String(bytes);

        System.out.println(s1);       // abc
        System.out.println("@" + s2); // @
        System.out.println(s3);       // abc
        System.out.println(s4);       // Qbcd
        System.out.println(s5);       // abcd
    }
}
