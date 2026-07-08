package com.summary.clazz;

/**
 * 顺带说一句：你以前用的 String 就是 Java 官方写好的一个"类"，
 *            字符串字面量 "abc" 就是一个 String 对象。
 *
 * 既然是对象，自然可以"对象名.方法名()"调用方法：
 *   s.length()        长度
 *   s.toUpperCase()   全部转大写
 *   s.toLowerCase()   全部转小写
 *
 * 这正是"类与对象"思想的应用——你以后写的 Phone、GirlFriend、Student，
 * 用法和 String 是一模一样的。
 */
public class StringApiDemo {
    public static void main(String[] args) {
        String s = "Hello, World!";

        System.out.println("长度：" + s.length());
        System.out.println("大写：" + s.toUpperCase());
        System.out.println("小写：" + s.toLowerCase());
    }
}
