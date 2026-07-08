package com.itheima._03_recursion;

/**
 * 递归求 1~n 的和
 *
 * 大问题拆解小问题：
 *   sum(n) = n + sum(n - 1)
 *   sum(1) = 1  ← 出口
 */
public class RecursionSumDemo {
    public static void main(String[] args) {
        System.out.println("1~100 = " + getSum(100)); // 5050
    }

    public static int getSum(int n) {
        if (n == 1) return 1;       // 出口
        return n + getSum(n - 1);   // 规律
    }
}
