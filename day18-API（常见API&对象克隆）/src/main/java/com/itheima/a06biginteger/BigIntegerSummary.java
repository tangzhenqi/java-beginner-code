package com.itheima.a06biginteger;

import java.math.BigInteger;
import java.util.Random;

/**
 * 知识点六：BigInteger
 * <p>
 * 用途：表示任意大小的整数（long 也装不下时）。
 * <p>
 * 创建方式：
 *   new BigInteger(int numBits, Random rnd) 随机大整数 [0, 2^numBits - 1]
 *   new BigInteger(String val)              指定的大整数（字符串必须是合法整数）
 *   new BigInteger(String val, int radix)   按指定进制解析（必须与进制匹配）
 *   BigInteger.valueOf(long val)            静态工厂；对 -16~16 有缓存
 * <p>
 * 重要特征：不可变（immutable）。所有算术运算都返回新对象，原对象不变。
 */
public class BigIntegerSummary {
    public static void main(String[] args) {
        // 1. 随机大整数
        BigInteger rnd = new BigInteger(8, new Random());
        System.out.println("随机 [0, 255] 内大整数: " + rnd);

        // 2. 通过字符串构造，可超出 long 范围
        BigInteger big = new BigInteger("99999999999999999999999999");
        System.out.println(big);

        // 3. 按进制构造："1010" 二进制 = 10
        BigInteger bin = new BigInteger("1010", 2);
        System.out.println(bin);                     // 10

        // 4. valueOf 的缓存：-16~16 返回同一对象，超出则每次新建
        System.out.println(BigInteger.valueOf(16) == BigInteger.valueOf(16));   // true
        System.out.println(BigInteger.valueOf(17) == BigInteger.valueOf(17));   // false

        // 5. 不可变性：计算结果产生新对象
        BigInteger a = BigInteger.valueOf(1);
        BigInteger b = BigInteger.valueOf(2);
        BigInteger sum = a.add(b);
        System.out.println("a = " + a + ", b = " + b + ", sum = " + sum);   // a、b 仍是 1 和 2
    }
}
