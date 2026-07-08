package com.itheima._04_lambda;

import java.util.function.Function;

/**
 * Lambda 简化规则（满足条件可省略）
 *
 *   1. 参数类型可以省略：编译器会从函数式接口推断；
 *   2. 只有一个参数时，括号可以省略；
 *   3. 方法体只有一行时，{}、return、; 都可以省略（三者要么全省，要么全留）。
 *
 * 注意：可省 ≠ 必须省，团队规范优先。
 */
public class LambdaShortenDemo {
    interface Printer {
        void print(String s);
    }

    public static void main(String[] args) {
        // 完整写法
        Printer p1 = (String s) -> { System.out.println(s); };

        // 省略参数类型
        Printer p2 = (s) -> { System.out.println(s); };

        // 只有一个参数，括号也省
        Printer p3 = s -> { System.out.println(s); };

        // 方法体只有一行，{} 一起省
        Printer p4 = s -> System.out.println(s);

        p1.print("p1"); p2.print("p2"); p3.print("p3"); p4.print("p4");

        // 带返回值时的省略对比
        Function<Integer, Integer> f1 = (Integer x) -> { return x * x; };
        Function<Integer, Integer> f2 = x -> x * x; // 等价
        System.out.println(f1.apply(4));
        System.out.println(f2.apply(5));
    }
}
