package com.itheima._02_search;

/**
 * 二分查找 / 折半查找
 *
 * 前提：数组必须有序。
 * 核心：每次取中间索引 mid，比较后排除一半的查找范围。
 * 复杂度：时间 O(log n)，空间 O(1)。
 *
 * 易错点：
 *   1. mid = (min + max) / 2 在 min+max 超过 int 最大值时会溢出，
 *      工程推荐写 mid = min + (max - min) / 2 或者 (min + max) >>> 1。
 *   2. 出口条件 min > max 时退出，返回 -1。
 */
public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] arr = {7, 23, 79, 81, 103, 127, 131, 147};
        System.out.println("找 81 : " + binarySearch(arr, 81));
        System.out.println("找 150: " + binarySearch(arr, 150));
        System.out.println("找 7  : " + binarySearch(arr, 7));
    }

    public static int binarySearch(int[] arr, int number) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2; // 防溢出写法
            if (arr[mid] > number) {
                max = mid - 1;
            } else if (arr[mid] < number) {
                min = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
