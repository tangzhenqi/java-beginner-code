package com.itheima.a07bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 知识点七：BigDecimal —— 常用运算
 * <p>
 *   add / subtract / multiply / divide      加减乘除
 *   divide(val, scale, roundingMode)        除法可控制精度和舍入模式
 *   pow(int n)                              次幂
 * <p>
 * 舍入模式（RoundingMode）常用：
 *   HALF_UP     四舍五入（最常用）
 *   HALF_DOWN   五舍六入
 *   HALF_EVEN   银行家舍入（IEEE 默认）
 *   UP / DOWN   向远离 0 / 向 0 取
 *   CEILING / FLOOR  向正无穷 / 向负无穷
 * <p>
 * 注意：除不尽时若不传 scale 和 RoundingMode，会抛 ArithmeticException。
 */
public class BigDecimalOps {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("0.09");
        BigDecimal b = new BigDecimal("0.01");

        // 加减乘除 —— 精确
        System.out.println("a + b = " + a.add(b));         // 0.10
        System.out.println("a - b = " + a.subtract(b));    // 0.08
        System.out.println("a * b = " + a.multiply(b));    // 0.0009
        System.out.println("a / b = " + a.divide(b));      // 9 —— 这里恰好除得尽

        // 除不尽必须指定 scale 与舍入模式
        BigDecimal x = new BigDecimal("10");
        BigDecimal y = new BigDecimal("3");
        System.out.println("10/3 保留 2 位 (HALF_UP)  = " + x.divide(y, 2, RoundingMode.HALF_UP));   // 3.33
        System.out.println("10/3 保留 4 位 (HALF_DOWN)= " + x.divide(y, 4, RoundingMode.HALF_DOWN)); // 3.3333

        // 次幂
        System.out.println("0.1^3 = " + new BigDecimal("0.1").pow(3));   // 0.001
    }
}
