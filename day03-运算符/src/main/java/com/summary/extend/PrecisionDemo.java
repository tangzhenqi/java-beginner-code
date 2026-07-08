package com.summary.extend;

/**
 * 拓展2：精度与取模的边界问题
 *
 *   1) 浮点不精确：0.1 + 0.2 != 0.3，因 IEEE 754 二进制表示无法精确表达部分十进制小数
 *      解决方案：精确运算用 java.math.BigDecimal（在后面 API 章节学习）
 *
 *   2) Java 的 % 结果符号与"被除数"相同
 *      -7 %  3 == -1
 *       7 % -3 ==  1
 */
public class PrecisionDemo {
    public static void main(String[] args) {
        // 浮点不精确
        System.out.println(0.1 + 0.2);    // 0.30000000000000004
        System.out.println(1.1 + 1.01);   // 2.1100000000000003

        // 负数取模
        System.out.println(-7 % 3);   // -1
        System.out.println(7 % -3);   // 1
        System.out.println(-7 % -3);  // -1
    }
}
