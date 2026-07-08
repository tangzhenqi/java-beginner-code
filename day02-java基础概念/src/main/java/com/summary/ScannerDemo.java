package com.summary;

import java.util.Scanner;

/**
 * 知识点5（拓展）：键盘录入 Scanner
 *
 *   步骤：
 *     1. import java.util.Scanner;
 *     2. Scanner sc = new Scanner(System.in);
 *     3. sc.nextInt() / sc.nextDouble() / sc.next() / sc.nextLine()
 *
 *   方法区别：
 *     - next()       遇到空格/Tab/换行结束，不读分隔符
 *     - nextLine()   读取一整行，含空格，遇回车结束
 *     - nextInt()    读一个 int；后接 nextLine() 会读到上一行残留的换行
 */
public class ScannerDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入您的姓名：");
        String name = sc.next();

        System.out.println("请输入您的年龄：");
        int age = sc.nextInt();

        System.out.println("请输入您的身高：");
        double height = sc.nextDouble();

        System.out.println("欢迎你，" + name + "，年龄 " + age + "，身高 " + height);

        sc.close();
    }
}
