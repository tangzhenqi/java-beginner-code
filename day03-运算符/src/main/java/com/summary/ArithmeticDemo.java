package com.summary;

/**
 * 知识点1：算术运算符  +  -  *  /  %
 *
 *   1. 整数 / 整数，结果只能是整数（小数部分丢弃）
 *   2. 含小数运算，结果可能不精确（IEEE 754 浮点）
 *   3. 取模 %：实际就是除法的余数
 *      应用：
 *        - 判断 A 是否能被 B 整除   A % B == 0
 *        - 判断奇偶                A % 2
 *        - 循环分组（如发牌）       序号 % 3
 */
public class ArithmeticDemo {
    public static void main(String[] args) {
        System.out.println(3 + 2);    // 5
        System.out.println(5 - 1);    // 4
        System.out.println(7 * 9);    // 63

        // 整数除 vs 浮点除
        System.out.println(10 / 2);   // 5
        System.out.println(10 / 3);   // 3
        System.out.println(10.0 / 3); // 3.333...

        // 取模
        System.out.println(10 % 2);   // 0
        System.out.println(10 % 3);   // 1

        // 浮点不精确（见 extend/PrecisionDemo）
        System.out.println(1.1 + 1.01);  // 2.1100000000000003
    }
}
