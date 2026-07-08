package com.itheima.a01math;

/**
 * 知识点一：Math 类
 * <p>
 * 归纳（day18 核心 API）：
 *   abs(a)       获取绝对值
 *   ceil(a)      向上取整（结果是 double，进一法）
 *   floor(a)     向下取整（结果是 double，去尾法）
 *   round(a)     四舍五入（结果是 int 或 long）
 *   max/min(a,b) 取较大/较小值
 *   pow(a,b)     a 的 b 次幂
 *   sqrt(a)      平方根
 *   cbrt(a)      立方根
 *   random()     [0.0, 1.0) 的随机 double
 * <p>
 * 特点：
 *   1. Math 是工具类，构造方法私有，所有方法都是 static，直接通过类名调用。
 *   2. Math 类位于 java.lang 包下，无需 import。
 */
public class MathSummary {
    public static void main(String[] args) {
        // 1. abs：绝对值
        System.out.println(Math.abs(-88));        // 88
        System.out.println(Math.abs(-3.14));      // 3.14

        // 2. ceil / floor：注意结果是 double
        System.out.println(Math.ceil(12.34));     // 13.0
        System.out.println(Math.ceil(-12.54));    // -12.0  （往数轴正方向进一）
        System.out.println(Math.floor(12.54));    // 12.0
        System.out.println(Math.floor(-12.34));   // -13.0  （往数轴负方向去尾）

        // 3. round：四舍五入。底层等价于 floor(a + 0.5)
        System.out.println(Math.round(12.54));    // 13
        System.out.println(Math.round(-12.54));   // -13
        System.out.println(Math.round(-12.5));    // -12  注意半数偏正向

        // 4. max / min
        System.out.println(Math.max(20, 30));     // 30
        System.out.println(Math.min(20, 30));     // 20

        // 5. pow / sqrt / cbrt
        System.out.println(Math.pow(2, 10));      // 1024.0
        System.out.println(Math.sqrt(16));        // 4.0
        System.out.println(Math.cbrt(27));        // 3.0

        // 6. random：[0.0, 1.0)，常用于生成随机范围
        //    生成 [1, 100] 的整数：
        int n = (int) (Math.random() * 100) + 1;
        System.out.println("随机数 [1, 100] = " + n);
    }
}
