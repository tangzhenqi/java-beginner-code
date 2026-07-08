package com.summary.crud;

/**
 * 删 —— 按 id 删除学生
 *
 * 思路：先按 id 找到索引，找到就把该位置置 null，找不到就提示。
 *
 * 拓展：另一种"紧凑删除"——把后面元素全部往前挪一格，避免数组中间出现 null。
 *      具体看 ExtensionDemo.shiftDelete。
 */
public class DeleteDemo {
    public static void main(String[] args) {
        Student[] arr = new Student[3];
        arr[0] = new Student(1, "zhangsan", 23);
        arr[1] = new Student(2, "lisi", 24);
        arr[2] = new Student(3, "wangwu", 25);

        int targetId = 2;
        int index = StudentArrayUtil.getIndex(arr, targetId);
        if (index < 0) {
            System.out.println("id 不存在，删除失败");
        } else {
            arr[index] = null;
            System.out.println("删除成功，剩余：");
            StudentArrayUtil.printArr(arr);
        }
    }
}
