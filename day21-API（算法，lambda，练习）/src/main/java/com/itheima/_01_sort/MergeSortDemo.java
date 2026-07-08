package com.itheima._01_sort;

import java.util.Arrays;

/**
 * 拓展：归并排序 (Merge Sort)
 *
 * 核心思想（分治）：
 *   1. 把数组拆成两半，分别排序；
 *   2. 用一个临时数组按顺序合并两个有序子数组。
 *
 * 复杂度：
 *   时间 O(n log n)（无论数据形态），空间 O(n)，稳定。
 *
 * 与快排对比：
 *   - 归并：稳定、最坏情况依旧 O(n log n)、需要额外空间；
 *   - 快排：原地、平均更快，但最坏 O(n^2)；
 *   - Java Arrays.sort(Object[]) 内部正是 TimSort（归并的变种）。
 */
public class MergeSortDemo {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) >>> 1; // 等价 (left+right)/2 且能防溢出
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        // 写回原数组
        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}
