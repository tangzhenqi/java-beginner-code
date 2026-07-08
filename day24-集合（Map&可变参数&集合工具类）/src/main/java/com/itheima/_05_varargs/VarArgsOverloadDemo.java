package com.itheima._05_varargs;

/**
 * 引入可变参数前的"笨办法"：方法重载。
 *
 * 要支持 2 / 3 / 4 / ... n 个参数求和，就要写 N 个重载，可扩展性极差。
 *
 * 下一步：VarArgsArrayDemo 用数组传参；最后用可变参数一行搞定。
 */
public class VarArgsOverloadDemo {
    public static void main(String[] args) {
        System.out.println(getSum(10, 20));
        System.out.println(getSum(10, 20, 30));
        System.out.println(getSum(10, 20, 30, 40));
    }

    public static int getSum(int a, int b) { return a + b; }

    public static int getSum(int a, int b, int c) { return a + b + c; }

    public static int getSum(int a, int b, int c, int d) { return a + b + c + d; }
}
