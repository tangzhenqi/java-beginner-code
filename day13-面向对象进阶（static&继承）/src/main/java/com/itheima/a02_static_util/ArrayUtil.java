package com.itheima.a02_static_util;

/**
 * 工具类的标准范式：
 *  1. 类名见名知意（XxxUtil）。
 *  2. 构造方法私有化：防止外部 new 对象（工具类没有"对象状态"，不需要实例化）。
 *  3. 所有对外方法均为 static，直接通过 "类名.方法" 调用。
 */
public class ArrayUtil {

    private ArrayUtil() {
    }

    public static String printArr(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    public static double getAverage(double[] arr) {
        double sum = 0;
        for (double v : arr) {
            sum += v;
        }
        return sum / arr.length;
    }

    // 拓展：取最大值（工具类经常补充类似的小工具方法）
    public static int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }
}
