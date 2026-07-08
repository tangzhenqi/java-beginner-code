package com.itheima.a07bigdecimal;

/**
 * 知识点七：BigDecimal —— 引子
 * <p>
 * 浮点数（float / double）采用 IEEE 754 二进制表示，
 * 0.1、0.2、0.3 等绝大多数十进制小数在二进制下都是【无限循环】，
 * 因此运算结果会出现精度丢失。
 * <p>
 * 凡是涉及金额、利率、统计、科学计算等需要精确小数的场景，
 *   绝对不要直接用 double，要用 BigDecimal。
 */
public class PrecisionProblemDemo {
    public static void main(String[] args) {
        System.out.println(0.09 + 0.01);    // 0.09999999999999999
        System.out.println(0.216 - 0.1);    // 0.11599999999999999
        System.out.println(0.226 * 0.01);   // 0.0022600000000000003
        System.out.println(0.09 / 0.1);     // 0.8999999999999999
    }
}
