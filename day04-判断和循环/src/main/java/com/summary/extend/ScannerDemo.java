package com.summary.extend;

import java.util.Scanner;

/**
 * 拓展4：键盘录入 Scanner 与健壮性写法
 *
 *   1. Scanner 是包装 System.in 的工具类，位于 java.util 包，需要 import。
 *   2. 常用方法：nextInt()、nextDouble()、next()、nextLine()。
 *   3. 录入前先用 hasNextXxx() 判断，避免因输入非法字符抛出
 *      InputMismatchException。
 *   4. Scanner 实现了 AutoCloseable，建议使用 try-with-resources 自动关闭，
 *      避免资源泄漏。
 */
public class ScannerDemo {
    public static void main(String[] args) {
        // try-with-resources 写法：括号里声明的资源会在 try 块结束时自动 close
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("请输入你的年龄（整数）：");
            if (!sc.hasNextInt()) {
                System.out.println("输入有误，必须是整数！");
                return;
            }
            int age = sc.nextInt();
            if (age < 0 || age > 150) {
                System.out.println("年龄超出合理范围！");
                return;
            }
            if (age >= 18) {
                System.out.println("成年人");
            } else {
                System.out.println("未成年");
            }
        }
    }
}
