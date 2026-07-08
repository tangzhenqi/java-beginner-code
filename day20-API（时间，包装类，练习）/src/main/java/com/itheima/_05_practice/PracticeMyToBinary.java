package com.itheima._05_practice;

/**
 * 练习 3：自实现 Integer.toBinaryString 的效果
 * <p>
 * 核心算法：不断除以 2，把每次余数倒序拼接起来，直到商为 0。
 * 拓展：把 2 改成任意进制，就能实现 toAnyBase。
 */
public class PracticeMyToBinary {
    public static void main(String[] args) {
        System.out.println(toBinary(0));       // 0
        System.out.println(toBinary(6));       // 110
        System.out.println(toBinary(100));     // 1100100
        System.out.println(toBase(100, 16));   // 64
        System.out.println(toBase(100, 8));    // 144
    }

    public static String toBinary(int number) {
        if (number == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (number != 0) {
            sb.insert(0, number % 2);
            number /= 2;
        }
        return sb.toString();
    }

    /** 拓展：任意 2~16 进制 */
    public static String toBase(int number, int base) {
        if (base < 2 || base > 16) throw new IllegalArgumentException("base 不合法");
        if (number == 0) return "0";
        char[] digits = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        while (number != 0) {
            sb.insert(0, digits[number % base]);
            number /= base;
        }
        return sb.toString();
    }
}
