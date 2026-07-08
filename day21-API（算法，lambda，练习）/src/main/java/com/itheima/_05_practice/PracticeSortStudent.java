package com.itheima._05_practice;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 综合练习：对学生对象数组按多种规则排序
 *
 * 知识点串联：
 *   - 用 day21 学到的"算法 + Lambda"思路
 *   - 用冒泡排序手写一遍按年龄升序，加深对算法的理解
 *   - 用 Arrays.sort + Lambda 实现按成绩降序 / 按姓名长度
 */
public class PracticeSortStudent {
    public static void main(String[] args) {
        Student[] arr = {
                new Student("Alice", 22, 88.5),
                new Student("Bob", 19, 75.0),
                new Student("Charlie", 25, 92.3),
                new Student("Dora", 20, 88.5),
                new Student("Eve", 23, 67.8)
        };

        // 1) 手写冒泡：按年龄升序
        bubbleByAge(arr);
        System.out.println("按年龄升序（冒泡手写）:");
        for (Student s : arr) System.out.println("  " + s);

        // 2) Arrays.sort + Lambda：按成绩降序
        Arrays.sort(arr, (a, b) -> Double.compare(b.getScore(), a.getScore()));
        System.out.println("按成绩降序（Lambda）:");
        for (Student s : arr) System.out.println("  " + s);

        // 3) 链式 thenComparing：成绩降序，成绩相同按姓名升序
        Arrays.sort(arr, Comparator
                .comparingDouble(Student::getScore).reversed()
                .thenComparing(Student::getName));
        System.out.println("成绩降序 + 姓名升序（链式）:");
        for (Student s : arr) System.out.println("  " + s);
    }

    /** 复用 day21 的冒泡思想，比较的不再是 int，而是 Student 的年龄字段 */
    private static void bubbleByAge(Student[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getAge() > arr[j + 1].getAge()) {
                    Student t = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = t;
                }
            }
        }
    }
}
