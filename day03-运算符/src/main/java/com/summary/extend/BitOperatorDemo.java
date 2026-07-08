package com.summary.extend;

/**
 * 拓展1：位运算符（按位操作二进制）
 *
 *   &     按位与    1&1=1，其余为 0
 *   |     按位或    0|0=0，其余为 1
 *   ^     按位异或   相同为 0，不同为 1
 *   ~     按位取反   全部位翻转
 *   <<    左移      相当于 *2^n
 *   >>    有符号右移 保留符号位
 *   >>>   无符号右移 高位补 0
 *
 * 经典应用：异或两次还原，可用于简易"加解密"。
 */
public class BitOperatorDemo {
    public static void main(String[] args) {
        System.out.println(6 & 3);    // 110 & 011 = 010 = 2
        System.out.println(6 | 3);    // 110 | 011 = 111 = 7
        System.out.println(6 ^ 3);    // 110 ^ 011 = 101 = 5
        System.out.println(~6);       // -7
        System.out.println(3 << 2);   // 12   = 3 * 2^2
        System.out.println(12 >> 2);  // 3    = 12 / 2^2

        // 异或加解密
        int data = 12345;
        int key = 9527;
        int encrypted = data ^ key;
        int decrypted = encrypted ^ key;
        System.out.println("加密：" + encrypted + "  解密：" + decrypted);
    }
}
