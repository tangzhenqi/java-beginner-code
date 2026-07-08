package com.itheima._01_sort;

import java.util.Arrays;

/**
 * 插入排序 (Insertion Sort)
 *
 * 核心思想：
 *   把 0~N 视为已经有序的"左手牌"，N+1~end 视为无序的"摸到的新牌"，
 *   将每张新牌从右往左与左手牌比较，找到合适位置插入。
 *
 * 复杂度：
 *   时间 O(n^2)；近乎有序时接近 O(n)。
 *   空间 O(1)，稳定。
 *
 * 适用场景：
 *   数据量小、数据基本有序，Java 内置 Arrays.sort 在小数组场景下也会切换为插入排序。
 */
public class InsertionSortDemo {
    public static void main(String[] args) {
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        insertionSort(arr);
        System.out.println("交换式: " + Arrays.toString(arr));

        int[] arr2 = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        insertionSortShift(arr2);
        System.out.println("挪移式: " + Arrays.toString(arr2));
    }

    /** 老师课堂版：先找到第一个无序起点，再用交换将元素往前推 */
    public static void insertionSort(int[] arr) {
        // 1. 找到无序部分的起始索引
        int startIndex = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                startIndex = i + 1;
                break;
            }
        }
        // 2. 从 startIndex 开始，把每个元素往前交换到正确位置
        for (int i = startIndex; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
    }

    /** 拓展：经典挪移式（少一半赋值次数），性能更好 */
    public static void insertionSortShift(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > cur) {
                arr[j + 1] = arr[j]; // 把大元素整体右移
                j--;
            }
            arr[j + 1] = cur;        // 最后空位放入待插入元素
        }
    }
}
