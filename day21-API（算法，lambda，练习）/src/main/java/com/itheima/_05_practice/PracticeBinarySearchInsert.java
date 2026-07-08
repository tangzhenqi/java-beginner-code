package com.itheima._05_practice;

/**
 * 练习：找出元素在有序数组中应当插入的位置。
 *
 * 场景：维护一个有序数组，新元素到达时既要快速判断是否存在，
 *      也要在不存在时告诉调用方应该把它放到哪个索引才能保持有序。
 *
 * 思路：基于二分查找的变体——找到时返回索引；找不到时返回 min（即插入位置）。
 */
public class PracticeBinarySearchInsert {
    public static void main(String[] args) {
        int[] arr = {7, 23, 79, 81, 103, 127, 131, 147};

        check(arr, 7);   // 已存在
        check(arr, 81);  // 已存在
        check(arr, 1);   // 比所有都小 → 0
        check(arr, 100); // 中间 → 4
        check(arr, 200); // 比所有都大 → arr.length
    }

    private static void check(int[] arr, int n) {
        int idx = searchInsertIndex(arr, n);
        System.out.println("数字 " + n + " 应位于索引 " + idx);
    }

    /**
     * @return 元素的索引（如果存在），否则返回插入位置
     */
    public static int searchInsertIndex(int[] arr, int number) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (arr[mid] == number) return mid;
            if (arr[mid] < number) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        // 退出时 min 即为应插入的位置
        return min;
    }
}
