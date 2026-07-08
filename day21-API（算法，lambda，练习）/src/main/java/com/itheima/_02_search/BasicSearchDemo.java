package com.itheima._02_search;

/**
 * 基本查找 / 顺序查找
 *
 * 核心：从 0 索引开始挨个比对，找到返回 true（或索引），找不到返回 false（或 -1）。
 * 复杂度：时间 O(n)，空间 O(1)；适用于无序数组、数据量小的场景。
 */
public class BasicSearchDemo {
    public static void main(String[] args) {
        int[] arr = {131, 127, 147, 81, 103, 23, 7, 79};
        System.out.println("是否存在 82 : " + contains(arr, 82));
        System.out.println("是否存在 23 : " + contains(arr, 23));
        System.out.println("23 的索引     : " + indexOf(arr, 23));
        System.out.println("82 的索引     : " + indexOf(arr, 82));
    }

    /** 是否存在 */
    public static boolean contains(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) return true;
        }
        return false;
    }

    /** 返回索引；未找到返回 -1（不考虑重复元素） */
    public static int indexOf(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) return i;
        }
        return -1;
    }
}
