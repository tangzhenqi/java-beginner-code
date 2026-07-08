package com.itheima._04_lambda;

/**
 * Lambda 表达式基本写法
 *
 * 标准格式：(参数列表) -> { 方法体 }
 *
 * 其中：
 *   - 参数列表必须与函数式接口的抽象方法保持一致；
 *   - -> 是固定语法，无任何含义；
 *   - 方法体就是匿名内部类中重写方法的方法体。
 *
 * 作用：以"更短的语法"代替"匿名内部类"，专门写给函数式接口用。
 */
public class LambdaBasicDemo {

    interface Calculator {
        int calc(int a, int b);
    }

    public static void main(String[] args) {
        // 1) 匿名内部类
        Calculator add = new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        };
        System.out.println("匿名: " + add.calc(2, 3));

        // 2) Lambda 等价写法
        Calculator sub = (int a, int b) -> {
            return a - b;
        };
        System.out.println("Lambda: " + sub.calc(5, 2));

        // 3) 把 Lambda 当作参数传递给方法
        printResult(10, 3, (a, b) -> a * b);
        printResult(10, 3, (a, b) -> a / b);
    }

    private static void printResult(int a, int b, Calculator c) {
        System.out.println("结果 = " + c.calc(a, b));
    }
}
