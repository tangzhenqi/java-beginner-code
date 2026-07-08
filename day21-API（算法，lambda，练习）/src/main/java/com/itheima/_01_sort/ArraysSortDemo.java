package com.itheima._01_sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 拓展：JDK 提供的排序工具 java.util.Arrays.sort
 *
 * 知识点：
 *   1. 基本类型数组：Arrays.sort(int[]) 使用双轴快排（Dual-Pivot QuickSort）；
 *   2. 引用类型数组：Arrays.sort(T[]) 使用 TimSort（归并的优化版本，稳定）；
 *   3. 部分排序：Arrays.sort(arr, from, to) 只排序 [from, to) 区间；
 *   4. 自定义比较器：Arrays.sort(T[], Comparator)；
 *   5. 倒序常用技巧：使用 Integer[] + Comparator.reverseOrder()。基本类型 int[] 不能直接倒序。
 */
public class ArraysSortDemo {
    public static void main(String[] args) {
        // 1. 基本类型升序
        int[] arr = {5, 2, 9, 1, 3};
        Arrays.sort(arr);
        System.out.println("基本类型升序: " + Arrays.toString(arr));

        // 2. 部分排序
        int[] arr2 = {5, 2, 9, 1, 3};
        Arrays.sort(arr2, 1, 4); // 只排序索引 1~3
        System.out.println("部分排序: " + Arrays.toString(arr2));

        // 3. 引用类型 + 自定义比较器：字符串按长度升序，长度相同按字典序
        String[] words = {"banana", "fig", "apple", "kiwi", "pear"};
        Arrays.sort(words, (a, b) -> {
            int cmp = a.length() - b.length();
            return cmp != 0 ? cmp : a.compareTo(b);
        });
        System.out.println("字符串自定义排序: " + Arrays.toString(words));

        // 4. 引用类型倒序
        Integer[] nums = {5, 2, 9, 1, 3};
        Arrays.sort(nums, Comparator.reverseOrder());
        System.out.println("Integer 倒序: " + Arrays.toString(nums));
    }
}
