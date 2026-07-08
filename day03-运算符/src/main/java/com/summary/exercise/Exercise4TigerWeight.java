package com.summary.exercise;

import java.util.Scanner;

/**
 * 练习4：判断两只老虎体重是否相同
 *
 * 用三元运算符返回 "相同" / "不同"
 */
public class Exercise4TigerWeight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一只老虎的体重：");
        int w1 = sc.nextInt();
        System.out.println("请输入第二只老虎的体重：");
        int w2 = sc.nextInt();

        String result = w1 == w2 ? "相同" : "不同";
        System.out.println(result);

        sc.close();
    }
}
