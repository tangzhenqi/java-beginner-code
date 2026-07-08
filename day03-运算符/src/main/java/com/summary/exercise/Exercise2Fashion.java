package com.summary.exercise;

import java.util.Scanner;

/**
 * 练习2：相亲时髦度比较
 *
 * 键盘录入两个 0~10 的整数（自己 / 对方的时髦度），
 * 我比对方时髦输出 true，否则输出 false。
 */
public class Exercise2Fashion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入自己的时髦度(0~10)：");
        int my = sc.nextInt();
        System.out.println("请输入对象的时髦度(0~10)：");
        int her = sc.nextInt();

        boolean result = my > her;
        System.out.println(result);

        sc.close();
    }
}
