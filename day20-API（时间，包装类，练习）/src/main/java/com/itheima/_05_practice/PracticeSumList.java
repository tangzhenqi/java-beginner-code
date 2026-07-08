package com.itheima._05_practice;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 练习 1：键盘录入 1~100 之间的整数添加到集合，直到累加和超过 200 为止。
 * <p>
 * 涉及知识点：
 * - 集合 + 自动装箱：list.add(int) 实际是 add(Integer.valueOf(int))
 * - Integer.parseInt 字符串转整数
 * - Scanner.nextLine 统一接收整行输入（避免空格/回车的坑）
 */
public class PracticeSumList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入一个 1~100 的整数：");
            String line = sc.nextLine();
            int num;
            try {
                num = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("非整数，重输");
                continue;
            }
            if (num < 1 || num > 100) {
                System.out.println("超出范围，重输");
                continue;
            }
            list.add(num); // 自动装箱
            int sum = sum(list);
            System.out.println("当前累计：" + sum);
            if (sum > 200) {
                System.out.println("累计超过 200，结束。集合：" + list);
                break;
            }
        }
    }

    private static int sum(ArrayList<Integer> list) {
        int s = 0;
        for (Integer i : list) s += i; // 遍历时自动拆箱
        return s;
    }
}
