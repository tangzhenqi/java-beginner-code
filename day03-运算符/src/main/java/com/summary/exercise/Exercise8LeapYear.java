package com.summary.exercise;

import java.util.Scanner;

/**
 * 拓展练习8：判断闰年（综合逻辑运算）
 *
 * 规则：
 *   能被 4 整除且不能被 100 整除   ->   闰年
 *   或能被 400 整除               ->   闰年
 *   其他                          ->   平年
 *
 * 表达式：
 *   (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
 */
public class Exercise8LeapYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入年份：");
        int year = sc.nextInt();

        boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println(year + (leap ? " 是闰年" : " 是平年"));

        sc.close();
    }
}
