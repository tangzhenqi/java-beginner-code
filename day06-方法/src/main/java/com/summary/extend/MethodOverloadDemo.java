package com.summary.extend;

/**
 * 拓展1：方法重载（Overload）
 *
 *   定义：在同一个类中，多个方法名相同，但形参列表不同（个数、类型、顺序至少一种不同），
 *         就构成重载。
 *
 *   注意点：
 *     1. 只看方法名 + 形参列表，与返回值类型、形参变量名、修饰符无关；
 *     2. 调用时编译器根据"实参"匹配最合适的重载方法；
 *     3. 经典案例：System.out.println 就是被重载了几十次，支持各种类型。
 *
 *   好处：用同一个名字表达一组"相似但参数不同"的功能。
 */
public class MethodOverloadDemo {
    public static void main(String[] args) {
        System.out.println(sum(1, 2));          // int 版本
        System.out.println(sum(1.5, 2.5));      // double 版本
        System.out.println(sum(1, 2, 3));       // 三参数版本
        System.out.println(sum("hello", "!"));  // 字符串版本
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static double sum(double a, double b) {
        return a + b;
    }

    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static String sum(String a, String b) {
        return a + b; // 字符串拼接
    }
}
