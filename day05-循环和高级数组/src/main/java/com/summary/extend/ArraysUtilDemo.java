package com.summary.extend;

import java.util.Arrays;

/**
 * 拓展4：java.util.Arrays 工具类
 *
 *   常用静态方法：
 *     Arrays.toString(arr)        —— 以可读字符串形式返回数组内容
 *     Arrays.sort(arr)            —— 升序排序（原地修改）
 *     Arrays.fill(arr, value)     —— 把数组全部填充为某个值
 *     Arrays.copyOf(arr, newLen)  —— 复制并返回新数组（可用于扩容）
 *     Arrays.equals(a, b)         —— 比较两个数组的内容是否相等（而非地址）
 *     Arrays.binarySearch(arr, k) —— 在已排序数组中二分查找
 *
 *   注意：直接 println(arr) 打印的是地址，要看内容请用 Arrays.toString。
 */
public class ArraysUtilDemo {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};

        // 打印内容
        System.out.println(Arrays.toString(arr)); // [3, 1, 4, 1, 5, 9, 2, 6]

        // 排序
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr)); // [1, 1, 2, 3, 4, 5, 6, 9]

        // 复制并扩容
        int[] copy = Arrays.copyOf(arr, 10);
        System.out.println(Arrays.toString(copy)); // 末尾补 0

        // 填充
        int[] zeros = new int[5];
        Arrays.fill(zeros, 7);
        System.out.println(Arrays.toString(zeros)); // [7, 7, 7, 7, 7]

        // 内容相等判断
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        System.out.println(a == b);              // false，地址不同
        System.out.println(Arrays.equals(a, b)); // true，内容相同

        // 二分查找（必须先排序）
        int index = Arrays.binarySearch(arr, 5);
        System.out.println("5 在排序后数组的索引：" + index);
    }
}
