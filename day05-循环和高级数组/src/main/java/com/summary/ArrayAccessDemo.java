package com.summary;

/**
 * 知识点4：数组元素的访问与修改
 *
 *   通过"索引"来访问，索引从 0 开始。
 *
 *   读取：数组名[索引]
 *   修改：数组名[索引] = 新值;   // 覆盖之后原值丢失
 *
 *   常用属性：数组名.length —— 数组的长度（不是方法，没有小括号）。
 *   最大索引就是 length - 1。
 */
public class ArrayAccessDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        // 读取
        System.out.println(arr[0]); // 1
        System.out.println(arr[4]); // 5

        // 修改
        arr[0] = 100;
        System.out.println(arr[0]); // 100，原值 1 被覆盖

        // length 属性
        System.out.println("数组长度：" + arr.length); // 5
        System.out.println("最大索引：" + (arr.length - 1)); // 4
    }
}
