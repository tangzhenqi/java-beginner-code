package com.itheima._05_practice;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 综合练习：排序 + 查找 + Lambda 一气呵成
 *
 * 需求：
 *   1. 给定若干学生，先按"成绩降序"排序；
 *   2. 排序后用二分查找，按成绩查询第一个成绩小于等于阈值的学生位置；
 *   3. 全部用 Lambda 表达式表达比较规则。
 */
public class PracticeAllInOne {
    public static void main(String[] args) {
        Student[] arr = {
                new Student("Alice", 22, 88.5),
                new Student("Bob", 19, 75.0),
                new Student("Charlie", 25, 92.3),
                new Student("Dora", 20, 88.5),
                new Student("Eve", 23, 67.8)
        };

        // 1) 成绩降序
        Arrays.sort(arr, Comparator.comparingDouble(Student::getScore).reversed());
        System.out.println("成绩降序: ");
        for (Student s : arr) System.out.println("  " + s);

        // 2) 二分查找：找成绩等于 88.5 的学生（重复元素时返回任意一个）
        int idx = binarySearchByScoreDesc(arr, 88.5);
        System.out.println("成绩 = 88.5 的某个位置: " + idx);
        if (idx >= 0) System.out.println("对应学生: " + arr[idx]);
    }

    /**
     * 在按成绩降序排好的数组中二分查找。
     * 注意：因为是降序，所以比较逻辑要反过来。
     */
    public static int binarySearchByScoreDesc(Student[] arr, double target) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            double s = arr[mid].getScore();
            if (s == target) return mid;
            if (s > target) {
                min = mid + 1;   // 降序：比目标大的在左侧 → 往右走
            } else {
                max = mid - 1;
            }
        }
        return -1;
    }
}
