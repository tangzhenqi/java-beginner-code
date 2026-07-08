package com.summary;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 知识点4：BigDecimal —— 解决小数精度问题
 *
 *   背景：
 *     double / float 是二进制浮点，0.1 等小数无法精确表示，
 *     所以 0.1 + 0.2 == 0.30000000000000004 而不是 0.3。
 *     涉及金额、商品价格、利率等场景，绝对不能用 double 直接计算。
 *
 *   解决方案：java.math.BigDecimal
 *     1. 构造：new BigDecimal("3.14")    —— 推荐用字符串入参；
 *                new BigDecimal(3.14)     —— 不推荐，会把 double 的误差带进来；
 *     2. 运算：add、subtract、multiply、divide；
 *     3. 除不尽时必须指定保留位数和舍入模式：
 *        divide(other, 保留位数, RoundingMode.XXX);
 *
 *   常用舍入模式：
 *     RoundingMode.HALF_UP   —— 四舍五入（常用）
 *     RoundingMode.UP        —— 向远离 0 的方向进位
 *     RoundingMode.DOWN      —— 向 0 的方向截断
 *     RoundingMode.HALF_EVEN —— 银行家舍入（金融首选）
 */
public class BigDecimalDemo {
    public static void main(String[] args) {
        // 浮点误差现象
        System.out.println(0.1 + 0.2); // 0.30000000000000004

        // 用 BigDecimal 精确运算
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("0.2");
        System.out.println(a.add(b)); // 0.3

        // 除法：-10 / 3 保留两位，向上取整
        BigDecimal bd1 = new BigDecimal("-10");
        BigDecimal bd2 = new BigDecimal("3");
        BigDecimal result = bd1.divide(bd2, 2, RoundingMode.UP);
        System.out.println(result); // -3.34

        // 注意：BigDecimal 是不可变对象，所有运算都返回新对象，不会改原值
        BigDecimal x = new BigDecimal("5");
        x.add(new BigDecimal("1")); // 没有接收返回值，x 还是 5
        System.out.println(x);      // 5
    }
}
