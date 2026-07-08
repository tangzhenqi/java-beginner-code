package com.summary;

/**
 * 知识点8（拓展）：整数字面量的不同进制
 *
 *   十进制      直接写              100
 *   二进制      以 0b 开头           0b1100100
 *   八进制      以 0  开头           0144
 *   十六进制    以 0x 开头           0x64
 *
 * 它们最终都是同一个数值。
 */
public class NumberBaseDemo {
    public static void main(String[] args) {
        int dec = 100;
        int bin = 0b1100100;
        int oct = 0144;
        int hex = 0x64;

        System.out.println("十进制：" + dec);
        System.out.println("二进制：" + bin);
        System.out.println("八进制：" + oct);
        System.out.println("十六进制：" + hex);
    }
}
