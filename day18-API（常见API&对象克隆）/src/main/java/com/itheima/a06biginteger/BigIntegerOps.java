package com.itheima.a06biginteger;

import java.math.BigInteger;

/**
 * 知识点六：BigInteger —— 常用运算
 * <p>
 *   add / subtract / multiply / divide          加减乘除
 *   divideAndRemainder                          一次返回商和余数 BigInteger[]
 *   pow(int exponent)                           次幂
 *   max / min                                   最大值 / 最小值
 *   equals                                      内容比较（已重写）
 *   intValue / longValue / doubleValue          转基本类型（可能溢出失精度）
 */
public class BigIntegerOps {
    public static void main(String[] args) {
        BigInteger a = BigInteger.valueOf(10);
        BigInteger b = BigInteger.valueOf(3);

        System.out.println("a + b = " + a.add(b));
        System.out.println("a - b = " + a.subtract(b));
        System.out.println("a * b = " + a.multiply(b));
        System.out.println("a / b = " + a.divide(b));               // 3

        BigInteger[] qr = a.divideAndRemainder(b);
        System.out.println("商=" + qr[0] + "，余数=" + qr[1]);       // 3, 1

        System.out.println("a^5 = " + a.pow(5));
        System.out.println("max(a,b) = " + a.max(b));
        System.out.println("a.equals(BigInteger.TEN) = " + a.equals(BigInteger.TEN));

        // 类型转换：值合法时按 int 返回；超出 int 范围会截断（不报错，但数据错误）
        System.out.println(BigInteger.valueOf(2_147_483_648L).intValue());   // 错误的截断结果
    }
}
