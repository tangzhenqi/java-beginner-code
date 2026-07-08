package com.summary.exercise;

import java.util.Scanner;

/**
 * 练习3：伟大的数字 6
 *
 * 输入两个整数 a、b：
 *   a == 6  或  b == 6  或  (a + b) 是 6 的倍数  -> true
 *   否则 -> false
 *
 * 核心：短路逻辑运算符 || 连接三个判断
 */
public class Exercise3GreatSix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个整数：");
        int a = sc.nextInt();
        System.out.println("请输入第二个整数：");
        int b = sc.nextInt();

        boolean result = a == 6 || b == 6 || (a + b) % 6 == 0;
        System.out.println(result);

        sc.close();
    }
}
