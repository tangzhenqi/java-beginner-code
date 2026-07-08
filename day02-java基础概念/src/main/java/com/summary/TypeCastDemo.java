package com.summary;

/**
 * 知识点4：数据类型转换
 *
 * 一、自动类型提升（隐式转换）：小范围 -> 大范围
 *      byte/short/char -> int -> long -> float -> double
 *      （byte/short/char 参与算术运算时会先自动提升为 int）
 *
 * 二、强制类型转换（显式转换）：大范围 -> 小范围
 *      格式：  目标类型 变量名 = (目标类型) 被转的数据;
 *      风险：可能丢失精度，也可能数据溢出
 *
 * 三、字符与数字
 *      char 参与运算时按 ASCII / Unicode 编码转 int
 *      'A' = 65   'a' = 97   '0' = 48
 */
public class TypeCastDemo {
    public static void main(String[] args) {
        // 自动提升：int -> double
        int a = 10;
        double d = a;
        System.out.println(d);        // 10.0

        // 强制转换：double -> int，小数部分被截断（不四舍五入）
        double pi = 3.99;
        int p = (int) pi;
        System.out.println(p);        // 3

        // 字符运算自动提升为 int
        char c = 'A';
        System.out.println(c + 0);    // 65

        // 强转可能溢出
        int big = 200;
        byte overflow = (byte) big;   // byte 最大 127，溢出
        System.out.println(overflow); // -56
    }
}
