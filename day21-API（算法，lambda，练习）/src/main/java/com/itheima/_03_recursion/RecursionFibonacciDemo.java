package com.itheima._03_recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 拓展：斐波那契数列
 *
 *   f(1) = 1, f(2) = 1
 *   f(n) = f(n - 1) + f(n - 2)
 *
 * 朴素递归的问题：
 *   f(5) = f(4) + f(3)
 *        = (f(3) + f(2)) + (f(2) + f(1))
 *   f(3) 计算了两次，f(2) 计算了多次 → 时间复杂度 O(2^n)。
 *
 * 优化：记忆化（Memoization），把已计算结果缓存起来 → O(n)。
 */
public class RecursionFibonacciDemo {
    public static void main(String[] args) {
        System.out.println("朴素递归 f(10) = " + fib(10));
        System.out.println("记忆化   f(50) = " + fibMemo(50, new HashMap<>()));
        // 朴素递归算 f(50) 会非常慢，记忆化几乎瞬间得出结果。
    }

    /** 朴素递归 */
    public static long fib(int n) {
        if (n <= 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /** 拓展：记忆化递归 */
    public static long fibMemo(int n, Map<Integer, Long> cache) {
        if (n <= 2) return 1;
        if (cache.containsKey(n)) return cache.get(n);
        long result = fibMemo(n - 1, cache) + fibMemo(n - 2, cache);
        cache.put(n, result);
        return result;
    }
}
