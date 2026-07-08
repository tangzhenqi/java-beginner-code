package com.summary.traversal;

import java.util.Scanner;

/**
 * 计数器思想：统计字符串中大写、小写、数字字符的个数。
 *
 * char 在比较时会自动提升为 int（ASCII 码值），所以：
 *   c >= 'a' && c <= 'z'   等价于   97 <= c <= 122
 *
 *   '0' ~ '9'   48 ~ 57
 *   'A' ~ 'Z'   65 ~ 90
 *   'a' ~ 'z'   97 ~ 122
 */
public class CountCharDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String str = sc.next();

        int bigCount = 0, smallCount = 0, numCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') smallCount++;
            else if (c >= 'A' && c <= 'Z') bigCount++;
            else if (c >= '0' && c <= '9') numCount++;
        }

        System.out.println("大写：" + bigCount);
        System.out.println("小写：" + smallCount);
        System.out.println("数字：" + numCount);
    }
}
