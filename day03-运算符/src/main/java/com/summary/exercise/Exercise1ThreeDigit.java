package com.summary.exercise;

import java.util.Scanner;

/**
 * 练习1：拆分一个三位数为 个位/十位/百位
 *
 * 公式：
 *   个位  = 数字 % 10
 *   十位  = 数字 / 10 % 10
 *   百位  = 数字 / 100 % 10
 */
public class Exercise1ThreeDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个三位数：");
        int number = sc.nextInt();

        int ge = number % 10;
        int shi = number / 10 % 10;
        int bai = number / 100 % 10;
        System.out.println("百位 = " + bai);
        System.out.println("十位 = " + shi);
        System.out.println("个位 = " + ge);

        sc.close();
    }
}
