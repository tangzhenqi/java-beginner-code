package com.itheima._05_varargs;

/**
 * 可变参数的几个小细节。
 *
 *   1. 一个方法形参中最多只能写一个可变参数
 *        理解：可变参数像个"大胖子"，能吃多少吃多少，写两个就分不清边界
 *
 *   2. 如果除了可变参数还有其他形参，可变参数必须写在最后
 *        getSum(int a, int... args)        OK
 *        getSum(int... args, int a)        编译错误
 *
 *   3. 调用时实参是 null 或一个数组都可以
 */
public class VarArgsRulesDemo {
    public static void main(String[] args) {
        // 普通参数 + 可变参数
        System.out.println(getSum(1, 2, 3, 4, 5));
        System.out.println(getSum(1));        // 可变参数可以传 0 个

        // 也可以传数组
        int[] tail = {10, 20, 30};
        System.out.println(getSum(100, tail));
    }

    public static int getSum(int a, int... args) {
        int sum = a;
        for (int i : args) sum += i;
        return sum;
    }
}
