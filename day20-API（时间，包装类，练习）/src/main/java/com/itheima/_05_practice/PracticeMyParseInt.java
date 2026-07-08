package com.itheima._05_practice;

/**
 * 练习 2：自实现 Integer.parseInt 的效果
 * <p>
 * 要求：
 * - 字符串内只能是数字字符
 * - 最少 1 位、最多 10 位
 * - 不能以 0 开头
 * <p>
 * 思路：先用正则把异常数据过滤，再按位累加：number = number*10 + (c - '0')
 */
public class PracticeMyParseInt {
    public static void main(String[] args) {
        System.out.println(myParseInt("0"));       // 异常
        System.out.println(myParseInt("123"));     // 123
        System.out.println(myParseInt("9999999999"));// 异常 (大于 int 范围，演示长度限制)
        System.out.println(myParseInt("abc"));     // 异常
    }

    /** 返回 -1 代表非法（演示用，实际可以抛异常） */
    public static int myParseInt(String str) {
        if (str == null || !str.matches("[1-9]\\d{0,9}")) {
            System.out.println("非法输入：" + str);
            return -1;
        }
        int number = 0;
        for (int i = 0; i < str.length(); i++) {
            int digit = str.charAt(i) - '0';
            number = number * 10 + digit;
        }
        return number;
    }
}
