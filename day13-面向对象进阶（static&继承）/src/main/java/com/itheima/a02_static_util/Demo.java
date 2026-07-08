package com.itheima.a02_static_util;

public class Demo {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println(ArrayUtil.printArr(arr1));

        double[] arr2 = {1.5, 3.7, 4.9, 5.8, 6.6};
        System.out.println("平均值 = " + ArrayUtil.getAverage(arr2));

        System.out.println("最大值 = " + ArrayUtil.max(arr1));

        // ArrayUtil u = new ArrayUtil(); // 编译报错：构造方法是 private
    }
}
