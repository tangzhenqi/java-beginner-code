package com.itheima.a01math;

/**
 * 知识点一：Math 类 —— 拓展
 * <p>
 * 1. 取值范围 / 溢出陷阱
 *    abs(Integer.MIN_VALUE) 仍然返回 Integer.MIN_VALUE（负数），因为正方向没有对应值。
 *    JDK 1.8 提供 absExact(...)，遇到溢出会直接抛 ArithmeticException，更安全。
 * <p>
 * 2. 常用扩展 API：
 *    Math.log(x)        以 e 为底的对数
 *    Math.log10(x)      以 10 为底的对数
 *    Math.exp(x)        e 的 x 次幂
 *    Math.sin/cos/tan   三角函数（参数为弧度）
 *    Math.toRadians     角度 → 弧度
 *    Math.toDegrees     弧度 → 角度
 *    Math.signum(x)     符号函数，返回 -1.0 / 0.0 / 1.0
 *    Math.hypot(a, b)   √(a² + b²)，比手动写更精确，能避免溢出
 *    Math.floorDiv(a,b) 整数除法但向下取整（处理负数与 / 不同）
 *    Math.floorMod(a,b) 与 floorDiv 配套的余数，结果符号与除数一致
 *    Math.addExact / multiplyExact 溢出抛异常版本
 */
public class MathExtension {
    public static void main(String[] args) {
        // 1. abs 的溢出陷阱
        System.out.println(Math.abs(Integer.MIN_VALUE));  // -2147483648（依然是负数！）
        // System.out.println(Math.absExact(Integer.MIN_VALUE)); // 抛 ArithmeticException

        // 2. 对数 / 指数
        System.out.println(Math.log(Math.E));     // 1.0
        System.out.println(Math.log10(1000));     // 3.0
        System.out.println(Math.exp(1));          // ≈ 2.718...

        // 3. 三角函数（弧度制）
        System.out.println(Math.sin(Math.toRadians(30)));   // ≈ 0.5
        System.out.println(Math.cos(Math.toRadians(60)));   // ≈ 0.5

        // 4. hypot：勾股定理
        System.out.println(Math.hypot(3, 4));     // 5.0

        // 5. floorDiv vs /  的区别
        System.out.println(-7 / 2);               // -3   （向 0 取整）
        System.out.println(Math.floorDiv(-7, 2)); // -4   （向下取整）
        System.out.println(Math.floorMod(-7, 2)); // 1    （余数同除数符号）
        System.out.println(-7 % 2);               // -1

        // 6. 溢出检测
        try {
            Math.addExact(Integer.MAX_VALUE, 1);
        } catch (ArithmeticException e) {
            System.out.println("捕获到溢出: " + e.getMessage());
        }

        // 7. signum：符号
        System.out.println(Math.signum(-99.0));   // -1.0
        System.out.println(Math.signum(0.0));     //  0.0
        System.out.println(Math.signum(99.0));    //  1.0
    }
}
