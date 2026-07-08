package com.itheima._03_wrapper;

/**
 * 知识点：Integer 常用静态方法
 * <p>
 * 进制转换：
 * - String toBinaryString(int)  → 二进制
 * - String toOctalString(int)   → 八进制
 * - String toHexString(int)     → 十六进制
 * - String toString(int, int)   → 任意进制
 * <p>
 * 类型转换：
 * - int parseInt(String)
 * - int parseInt(String, int radix)
 * <p>
 * 拓展：8 种包装类除 Character 外，都提供 parseXxx 方法。
 */
public class IntegerStaticApi {
    public static void main(String[] args) {
        System.out.println("二进制：" + Integer.toBinaryString(100));  // 1100100
        System.out.println("八进制：" + Integer.toOctalString(100));   // 144
        System.out.println("十六进制：" + Integer.toHexString(100));   // 64
        System.out.println("任意进制（5 进制）：" + Integer.toString(100, 5)); // 400

        // 字符串 → int
        int i = Integer.parseInt("123");
        System.out.println(i + 1); // 124

        // 字符串 → 其他类型
        boolean b = Boolean.parseBoolean("true");
        double d = Double.parseDouble("3.14");
        System.out.println(b + " / " + d);
    }
}
