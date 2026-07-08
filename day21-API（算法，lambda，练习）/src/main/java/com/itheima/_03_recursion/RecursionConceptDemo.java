package com.itheima._03_recursion;

/**
 * 递归概念演示
 *
 * 1. 递归：方法直接或间接地调用自身。
 * 2. 必须具备的两要素：
 *    a) 出口：什么条件下不再递归（return）；
 *    b) 规律：每次递归如何更靠近出口。
 * 3. 没有出口或参数不向出口靠近，会无限递归 → StackOverflowError。
 *
 * 运行 main 会抛出 StackOverflowError，仅做演示。
 */
public class RecursionConceptDemo {
    public static void main(String[] args) {
        try {
            infiniteRecursion(0);
        } catch (StackOverflowError e) {
            System.out.println("捕获到 StackOverflowError，说明递归没有出口！");
        }
    }

    /** 反例：没有出口的递归，每次调用都把栈帧压入 JVM 栈，最终爆栈 */
    private static void infiniteRecursion(int depth) {
        infiniteRecursion(depth + 1);
    }
}
