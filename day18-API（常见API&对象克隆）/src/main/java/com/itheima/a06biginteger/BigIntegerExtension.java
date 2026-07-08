package com.itheima.a06biginteger;

import java.math.BigInteger;

/**
 * 知识点六：BigInteger —— 拓展
 * <p>
 *   gcd(BigInteger val)                      最大公约数
 *   mod(BigInteger m)                        取模（结果非负，与 % 不同）
 *   modPow(BigInteger exp, BigInteger m)     (this^exp) mod m，密码学常用
 *   isProbablePrime(int certainty)           概率素数测试
 *   nextProbablePrime()                      下一个概率素数
 *   shiftLeft / shiftRight                   位移
 *   and / or / xor / not                     按位运算
 *   BigInteger.ZERO / ONE / TWO / TEN        预定义常量
 */
public class BigIntegerExtension {
    public static void main(String[] args) {
        // 1. 最大公约数
        BigInteger g = BigInteger.valueOf(12).gcd(BigInteger.valueOf(18));
        System.out.println("gcd(12,18) = " + g);                   // 6

        // 2. modPow —— 大数幂取模，比先 pow 再 mod 快得多
        BigInteger base = BigInteger.valueOf(2);
        BigInteger exp = BigInteger.valueOf(1000);
        BigInteger mod = BigInteger.valueOf(1_000_000_007);
        System.out.println("2^1000 mod 1e9+7 = " + base.modPow(exp, mod));

        // 3. 素数测试
        BigInteger n = new BigInteger("100000000000000000039");    // 一个大素数
        System.out.println(n + " 是素数？" + n.isProbablePrime(20));

        // 4. 找下一个素数
        System.out.println("100 之后的下一个素数：" + BigInteger.valueOf(100).nextProbablePrime());

        // 5. 常量
        System.out.println(BigInteger.ZERO);
        System.out.println(BigInteger.ONE);
        System.out.println(BigInteger.TEN);
    }
}
