package com.summary.crud;

/**
 * 增 —— 添加学生
 *
 * 步骤：
 *   1. 唯一性判断：id 已存在则不允许添加
 *   2. 判断数组是否已存满：
 *        没存满 -> 直接放到第一个 null 的位置（位置 = getCount(arr)）
 *        存满了 -> 先扩容，再放到末尾
 */
public class AddDemo {
    public static void main(String[] args) {
        Student[] arr = new Student[3];
        arr[0] = new Student(1, "zhangsan", 23);
        arr[1] = new Student(2, "lisi", 24);

        Student newStu = new Student(3, "wangwu", 25);

        if (StudentArrayUtil.contains(arr, newStu.getId())) {
            System.out.println("id 已存在，添加失败");
            return;
        }

        int count = StudentArrayUtil.getCount(arr);
        if (count == arr.length) {
            // 满了 -> 扩容
            arr = StudentArrayUtil.expand(arr);
        }
        arr[count] = newStu;

        StudentArrayUtil.printArr(arr);
    }
}
