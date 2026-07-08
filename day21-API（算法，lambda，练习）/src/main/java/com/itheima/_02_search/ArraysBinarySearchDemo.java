package com.itheima._02_search;

import java.util.Arrays;

/**
 * 拓展：JDK 提供的 Arrays.binarySearch
 *
 * 知识点：
 *   1. 数组必须先排序，否则结果不可预测；
 *   2. 找到返回索引；找不到返回 -(insertion point) - 1，即"应插入位置取反再 -1"；
 *      例如返回 -3，说明若插入到数组中，索引应为 2（= -(-3) - 1）。
 *   3. 这种约定保证返回值一定为负，方便快速判断是否找到。
 */
public class ArraysBinarySearchDemo {
    public static void main(String[] args) {
        int[] arr = {7, 23, 79, 81, 103, 127, 131, 147};

        int found = Arrays.binarySearch(arr, 81);
        System.out.println("查找 81 → " + found);

        int notFound = Arrays.binarySearch(arr, 100);
        System.out.println("查找 100 → " + notFound + "（即应插入到索引 " + (-notFound - 1) + "）");

        int beyond = Arrays.binarySearch(arr, 200);
        System.out.println("查找 200 → " + beyond + "（即应插入到索引 " + (-beyond - 1) + "）");
    }
}
