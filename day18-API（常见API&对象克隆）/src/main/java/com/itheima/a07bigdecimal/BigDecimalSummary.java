package com.itheima.a07bigdecimal;

import java.math.BigDecimal;

/**
 * 知识点七：BigDecimal —— 创建对象
 * <p>
 * 构造方法：
 *   new BigDecimal(double val)   不推荐：会把 double 的二进制不精确值原样保留
 *   new BigDecimal(String val)   推荐：精确表示字符串中的小数
 * <p>
 * 静态工厂：
 *   BigDecimal.valueOf(double val) 内部其实是 new BigDecimal(Double.toString(val))，避开精度问题；
 *                                  且对 0~10 的整数有缓存。
 * <p>
 * 选择建议：
 *   1. 数值在 double 范围内，且能用字符串简洁表达 ⇒ valueOf
 *   2. 数值非常大或来自字符串 ⇒ new BigDecimal(String)
 *   3. 永远不要 new BigDecimal(double)
 */
public class BigDecimalSummary {
    public static void main(String[] args) {
        // 不推荐：传 double，结果不准
        BigDecimal a = new BigDecimal(0.1);
        System.out.println("new BigDecimal(0.1) = " + a);
        // 输出一串长长的近似值

        // 推荐 1：传字符串
        BigDecimal b = new BigDecimal("0.1");
        System.out.println("new BigDecimal(\"0.1\") = " + b);   // 0.1

        // 推荐 2：valueOf
        BigDecimal c = BigDecimal.valueOf(0.1);
        System.out.println("valueOf(0.1) = " + c);              // 0.1

        // valueOf 缓存（0~10 的整数）
        System.out.println(BigDecimal.valueOf(10) == BigDecimal.valueOf(10));   // true
        System.out.println(BigDecimal.valueOf(11) == BigDecimal.valueOf(11));   // false

        // 常用常量
        System.out.println(BigDecimal.ZERO);
        System.out.println(BigDecimal.ONE);
        System.out.println(BigDecimal.TEN);
    }
}
