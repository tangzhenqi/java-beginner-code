package com.summary.exercise;

/**
 * 练习5：三个和尚求最高
 *
 * 思路：先比较前两个得到临时较大值，再与第三个比较。
 */
public class Exercise5MonkHeight {
    public static void main(String[] args) {
        int h1 = 150;
        int h2 = 210;
        int h3 = 165;

        int temp = h1 > h2 ? h1 : h2;
        int max = temp > h3 ? temp : h3;

        System.out.println("最高身高：" + max);
    }
}
