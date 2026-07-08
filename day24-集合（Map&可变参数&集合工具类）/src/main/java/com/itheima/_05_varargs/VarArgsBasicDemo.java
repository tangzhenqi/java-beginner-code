package com.itheima._05_varargs;

/**
 * 可变参数（JDK5 引入）。
 *
 * 格式：类型...名字，例如 int... args
 *
 * 特点：
 *   - 方法形参的个数可以是 0、1、2、3 ... 任意个
 *   - 底层就是一个数组，Java 自动帮你创建好
 *   - 调用时直接传任意多个参数，也可以传一个数组
 */
public class VarArgsBasicDemo {
    public static void main(String[] args) {
        // 传 0 个
        System.out.println("0 个: " + getSum());
        // 传任意多个
        System.out.println("3 个: " + getSum(1, 2, 3));
        System.out.println("10个: " + getSum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        // 也可以直接传一个数组
        int[] arr = {100, 200, 300};
        System.out.println("数组: " + getSum(arr));
    }

    public static int getSum(int... args) {
        // 底层就是 int[] args
        int sum = 0;
        for (int i : args) sum += i;
        return sum;
    }
}
