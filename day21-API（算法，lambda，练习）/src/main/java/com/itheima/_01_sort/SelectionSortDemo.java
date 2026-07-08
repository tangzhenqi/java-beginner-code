package com.itheima._01_sort;

import java.util.Arrays;

/**
 * 选择排序 (Selection Sort)
 *
 * 核心思想：
 *   1. 从 i=0 开始，把 i 索引与其后所有元素比较；
 *   2. 比 i 上元素小的就交换，第一轮结束后 0 索引存最小值；
 *   3. 第二轮 i=1，依此类推。
 *
 * 复杂度：
 *   时间 O(n^2)，无论是否有序都要全部比较。
 *   空间 O(1)，不稳定（直接 swap 会破坏相同元素的相对顺序）。
 */
public class SelectionSortDemo {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 3, 1};
        selectionSort(arr);
        System.out.println("基础版: " + Arrays.toString(arr));

        int[] arr2 = {2, 4, 5, 3, 1};
        selectionSortOptimized(arr2);
        System.out.println("优化版: " + Arrays.toString(arr2));
    }

    /** 标准版：边比较边交换 */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /** 拓展：先找最小值索引，每轮只交换一次（减少 swap 次数） */
    public static void selectionSortOptimized(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
