package com.summary.crud;

/**
 * 改 —— 按 id 找到学生后修改其属性（这里：年龄 + 1）
 *
 * 关键点：
 *   arr[index] 拿到的"就是原对象"本身，对它调 setAge 直接生效，
 *   不需要再 arr[index] = stu 重新塞回去。
 *   （因为对象数组里存的是"对象引用"，不是"对象副本"）
 */
public class UpdateDemo {
    public static void main(String[] args) {
        Student[] arr = new Student[3];
        arr[0] = new Student(1, "zhangsan", 23);
        arr[1] = new Student(2, "lisi", 24);
        arr[2] = new Student(3, "wangwu", 25);

        int targetId = 2;
        int index = StudentArrayUtil.getIndex(arr, targetId);
        if (index < 0) {
            System.out.println("id 不存在，修改失败");
        } else {
            Student stu = arr[index];
            stu.setAge(stu.getAge() + 1);
            StudentArrayUtil.printArr(arr);
        }
    }
}
