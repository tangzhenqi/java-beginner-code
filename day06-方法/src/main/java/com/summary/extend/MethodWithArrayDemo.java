package com.summary.extend;

import java.util.Arrays;

/**
 * 拓展3：数组作为方法参数 / 返回值
 *
 *   - 数组当参数：方法可以处理任意长度的数组；
 *   - 数组当返回值：方法可以"造"出一个数组返回给调用者。
 *
 *   优势：把数组处理逻辑封装成方法，main 中只需调用，结构清晰。
 */
public class MethodWithArrayDemo {
    public static void main(String[] args) {
        int[] arr = {11, 22, 33, 44, 55};

        System.out.println("最大值 = " + max(arr));
        System.out.println("总和   = " + sum(arr));

        // 用方法生成数组
        int[] range = range(1, 5);
        System.out.println(Arrays.toString(range)); // [1, 2, 3, 4, 5]
    }

    // 数组作为参数：求最大值
    public static int max(int[] a) {
        int m = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > m) {
                m = a[i];
            }
        }
        return m;
    }

    public static int sum(int[] a) {
        int s = 0;
        for (int v : a) {
            s += v;
        }
        return s;
    }

    // 数组作为返回值：生成 [start, end] 范围的连续整数数组
    public static int[] range(int start, int end) {
        int[] result = new int[end - start + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = start + i;
        }
        return result;
    }
}
