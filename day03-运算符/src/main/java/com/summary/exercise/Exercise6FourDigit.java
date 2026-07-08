package com.summary.exercise;

import java.util.Scanner;

/**
 * 拓展练习6：拆分一个四位数为 个/十/百/千 位
 *
 * 公式：
 *   个位 = n % 10
 *   十位 = n / 10   % 10
 *   百位 = n / 100  % 10
 *   千位 = n / 1000 % 10
 */
public class Exercise6FourDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个四位数：");
        int n = sc.nextInt();

        int g = n % 10;
        int s = n / 10 % 10;
        int b = n / 100 % 10;
        int q = n / 1000 % 10;

        System.out.println("千位 = " + q);
        System.out.println("百位 = " + b);
        System.out.println("十位 = " + s);
        System.out.println("个位 = " + g);

        sc.close();
    }
}
