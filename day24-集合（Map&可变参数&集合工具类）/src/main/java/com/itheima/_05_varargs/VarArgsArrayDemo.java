package com.itheima._05_varargs;

/**
 * 演进一步：把参数都装进一个数组传过去。
 *
 * 比 N 个重载好，但调用方还要自己 new 数组，不够优雅。
 */
public class VarArgsArrayDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(getSum(arr));
    }

    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i : arr) sum += i;
        return sum;
    }
}
