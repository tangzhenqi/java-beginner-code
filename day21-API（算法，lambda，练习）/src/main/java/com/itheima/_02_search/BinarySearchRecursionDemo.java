package com.itheima._02_search;

/**
 * 拓展：使用递归实现二分查找
 *
 * 注意：递归只是写法上的等价形式，时间复杂度依旧 O(log n)，但会多出 O(log n) 的栈空间。
 */
public class BinarySearchRecursionDemo {
    public static void main(String[] args) {
        int[] arr = {7, 23, 79, 81, 103, 127, 131, 147};
        System.out.println("找 81 : " + binarySearch(arr, 81, 0, arr.length - 1));
        System.out.println("找 150: " + binarySearch(arr, 150, 0, arr.length - 1));
    }

    public static int binarySearch(int[] arr, int number, int min, int max) {
        if (min > max) return -1;            // 递归出口
        int mid = min + (max - min) / 2;
        if (arr[mid] == number) return mid;
        if (arr[mid] > number) {
            return binarySearch(arr, number, min, mid - 1);
        } else {
            return binarySearch(arr, number, mid + 1, max);
        }
    }
}
