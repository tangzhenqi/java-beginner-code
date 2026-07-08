package com.itheima.a07bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 知识点七：BigDecimal —— 拓展（实际开发中的高频坑）
 * <p>
 * 1. compareTo vs equals
 *    BigDecimal 的 equals 会同时比较【值】和【精度】（scale），
 *      "1.0".equals("1.00") 返回 false。
 *    业务上比较"数值相等"应使用 compareTo() == 0。
 * <p>
 * 2. setScale(scale, roundingMode)
 *    重新设置精度（保留几位小数），并指定舍入模式。
 * <p>
 * 3. stripTrailingZeros + toPlainString
 *    去掉尾部的 0；toPlainString 避免输出成科学计数法 1E+2。
 * <p>
 * 4. 链式操作仍是不可变的：每次返回新对象，原对象不变。
 */
public class BigDecimalExtension {
    public static void main(String[] args) {
        BigDecimal x = new BigDecimal("1.0");
        BigDecimal y = new BigDecimal("1.00");

        System.out.println("x.equals(y)      = " + x.equals(y));        // false
        System.out.println("x.compareTo(y)=0 = " + (x.compareTo(y) == 0));   // true

        // setScale：四舍五入到 2 位
        BigDecimal price = new BigDecimal("19.876");
        System.out.println("setScale = " + price.setScale(2, RoundingMode.HALF_UP));   // 19.88

        // stripTrailingZeros 可能得到科学计数法
        BigDecimal v = new BigDecimal("100.00").stripTrailingZeros();
        System.out.println("toString      = " + v);                     // 1E+2
        System.out.println("toPlainString = " + v.toPlainString());     // 100

        // 不可变：链式不会影响原对象
        BigDecimal base = new BigDecimal("10");
        BigDecimal r = base.add(BigDecimal.ONE).multiply(new BigDecimal("2"));
        System.out.println("base=" + base + ", r=" + r);                // 10, 22
    }
}
