package com.itheima._01_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序 (Quick Sort)
 *
 * 核心思想（"挖坑法"）：
 *   1. 选定基准数（一般取范围内第一个元素）；
 *   2. 一个指针 end 从右往左找比基准数小的；一个指针 start 从左往右找比基准数大的，找到就交换；
 *   3. 当 start == end 时，把基准数归位到该位置；
 *   4. 对基准数左右两侧子数组递归。
 *
 * 复杂度：
 *   平均 O(n log n)，最坏 O(n^2)（数组已经有序时）。
 *   空间 O(log n)（递归栈），不稳定。
 */
public class QuickSortDemo {
    public static void main(String[] args) {
        int[] arr = {1, 1, 6, 2, 7, 9, 3, 4, 5, 1, 10, 8};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快排结果: " + Arrays.toString(arr));

        // 拓展：和其他排序对比 100w 数据
        long t1 = benchmark("快速排序", QuickSortDemo::quickSortEntry, 100_000);
        long t2 = benchmark("冒泡排序", BubbleSortDemo::bubbleSort, 100_000);
        System.out.println("快速排序耗时(ms): " + t1);
        System.out.println("冒泡排序耗时(ms): " + t2);
    }

    public static void quickSort(int[] arr, int i, int j) {
        int start = i;
        int end = j;
        if (start >= end) return; // 递归出口

        int baseNumber = arr[i];

        while (start != end) {
            // 从后往前找比基准数小的
            while (end > start && arr[end] >= baseNumber) end--;
            // 从前往后找比基准数大的
            while (end > start && arr[start] <= baseNumber) start++;
            // 交换
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
        // 基准数归位
        arr[i] = arr[start];
        arr[start] = baseNumber;

        quickSort(arr, i, start - 1);
        quickSort(arr, start + 1, j);
    }

    // ----------- 拓展：性能对比工具 -----------
    private static void quickSortEntry(int[] arr) { quickSort(arr, 0, arr.length - 1); }

    @FunctionalInterface
    interface SortFn { void sort(int[] arr); }

    private static long benchmark(String name, SortFn fn, int size) {
        int[] arr = new int[size];
        Random r = new Random(42);
        for (int i = 0; i < arr.length; i++) arr[i] = r.nextInt();
        long start = System.currentTimeMillis();
        fn.sort(arr);
        return System.currentTimeMillis() - start;
    }
}
