package com.summary.scannerusage;

import java.util.Scanner;

/**
 * 知识点 4：Scanner 两套录入体系
 *
 * 体系一：nextInt() / nextDouble() / next()
 *   - 遇到 空格、Tab、回车 即停止接收，后面的内容不要。
 *
 * 体系二：nextLine()
 *   - 接收一整行，包括空格和 Tab，遇到回车才停。
 *
 * 不能混用！
 *   常见坑：先 sc.nextInt() 再 sc.nextLine()，
 *   nextInt 只读"数字"不读"回车"，遗留的回车被 nextLine 当成"空字符串"读走了。
 *
 * 解决办法（任选其一）：
 *   1. 整个程序统一使用 nextLine()，把读到的字符串再手动转换：Integer.parseInt(line)
 *   2. 在 nextInt 之后调一次 sc.nextLine() 把残留回车吃掉。
 */
public class ScannerSummary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入一个整数：");
        int num = sc.nextInt();
        // 关键：吃掉残留的回车，避免下一次 nextLine 拿到空串
        sc.nextLine();

        System.out.println("请输入一行字符串（可以带空格）：");
        String line = sc.nextLine();

        System.out.println("整数 = " + num);
        System.out.println("字符串 = " + line);
    }
}
