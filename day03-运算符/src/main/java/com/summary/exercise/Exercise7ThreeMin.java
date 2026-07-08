package com.summary.exercise;

import java.util.Scanner;

/**
 * 拓展练习7：键盘录入三个整数，求最小值
 *
 * 思路：用三元运算符两次比较。
 */
public class Exercise7ThreeMin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入三个整数（空格分隔）：");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int temp = a < b ? a : b;
        int min = temp < c ? temp : c;

        System.out.println("最小值：" + min);

        sc.close();
    }
}
