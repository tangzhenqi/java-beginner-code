package com.itheima._01_sort;

import java.util.Arrays;

/**
 * 冒泡排序 (Bubble Sort)
 *
 * 核心思想：
 *   1. 相邻元素两两比较，大的右移、小的左移。
 *   2. 每一轮结束后，当前未排序部分的最大值会"冒泡"到末尾。
 *   3. n 个数据共需 n-1 轮。
 *
 * 复杂度：
 *   时间 O(n^2)，最好情况 O(n)（已排好序 + 提前退出优化）
 *   空间 O(1)，稳定排序。
 */
public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 3, 1};
        bubbleSort(arr);
        System.out.println("基础版: " + Arrays.toString(arr));

        int[] arr2 = {1, 2, 3, 4, 5, 9, 8};
        bubbleSortOptimized(arr2);
        System.out.println("优化版: " + Arrays.toString(arr2));
    }

    /** 标准冒泡排序 */
    public static void bubbleSort(int[] arr) {
        // 外循环：执行 n-1 轮
        for (int i = 0; i < arr.length - 1; i++) {
            // 内循环：-1 防越界, -i 因为后面 i 个已经有序
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /** 拓展：增加 swapped 标志，若一轮内没有交换说明已有序，提前结束 */
    public static void bubbleSortOptimized(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
