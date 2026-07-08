package com.itheima._02_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 课堂练习：考虑数组中存在重复元素，返回所有匹配的索引。
 *
 * 心得：返回多个数据时，可以使用数组或者集合。
 *      - 集合：ArrayList，无需提前知道数据量；
 *      - 数组：需要先遍历一次拿到出现次数，再创建对应大小的数组。
 */
public class BasicSearchMultiIndexDemo {
    public static void main(String[] args) {
        int[] arr = {131, 127, 147, 81, 103, 23, 7, 79, 81};

        System.out.println("集合实现: " + indexesByList(arr, 81));
        System.out.println("数组实现: " + Arrays.toString(indexesByArray(arr, 81)));
    }

    /** 推荐：使用 List 返回所有索引 */
    public static List<Integer> indexesByList(int[] arr, int number) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) result.add(i);
        }
        return result;
    }

    /** 拓展：使用数组返回，需先统计次数再分配空间 */
    public static int[] indexesByArray(int[] arr, int number) {
        int count = 0;
        for (int v : arr) if (v == number) count++;
        int[] result = new int[count];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) result[k++] = i;
        }
        return result;
    }
}
