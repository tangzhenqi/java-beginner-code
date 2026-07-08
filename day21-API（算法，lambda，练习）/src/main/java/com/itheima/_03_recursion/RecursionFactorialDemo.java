package com.itheima._03_recursion;

/**
 * 递归求阶乘
 *   n! = n * (n - 1)!
 *   1! = 1  ← 出口
 *
 * 心得：每次递归调用，参数必须更靠近出口（n - 1），否则永远到不了出口。
 */
public class RecursionFactorialDemo {
    public static void main(String[] args) {
        System.out.println("5! = " + factorial(5));   // 120
        System.out.println("10! = " + factorial(10)); // 3628800
    }

    public static long factorial(int n) {
        if (n == 1) return 1;
        return (long) n * factorial(n - 1);
    }
}
