package com.summary.stringbuilder;

import java.util.Scanner;

/**
 * 链式编程
 *
 * "当一个方法的返回值仍然是这个对象（或同类对象），就可以接着调下一个方法"。
 *   - StringBuilder 的 append/reverse 返回自身，所以可以一直 . 下去。
 *   - String 的所有"修改"方法返回新 String，所以 String 也能链式。
 */
public class ChainCallDemo {
    public static void main(String[] args) {
        // String 的链式
        int len = getString().substring(1).replace("A", "Q").length();
        System.out.println("长度：" + len);

        // StringBuilder 的链式
        String result = new StringBuilder()
                .append("hello ")
                .append("world")
                .reverse()
                .toString();
        System.out.println(result);   // dlrow olleh
    }

    public static String getString() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        return sc.next();
    }
}
