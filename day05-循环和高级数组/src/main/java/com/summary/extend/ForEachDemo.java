package com.summary.extend;

/**
 * 拓展1：增强 for 循环（foreach）
 *
 *   格式：
 *     for (数据类型 变量名 : 数组或集合) {
 *         // 直接使用变量名访问元素
 *     }
 *
 *   优点：语法简洁，不需要关心索引，避免越界。
 *
 *   缺点：
 *     1. 拿不到当前元素的下标；
 *     2. 不能反向遍历或跳跃遍历；
 *     3. 不能在遍历中通过赋值修改原数组元素（对引用类型，仍可修改对象内部状态）。
 *
 *   IDEA 输入"数组名.for"会自动生成这种写法。
 */
public class ForEachDemo {
    public static void main(String[] args) {
        int[] arr = {11, 22, 33, 44, 55};

        // 增强 for 遍历
        for (int num : arr) {
            System.out.println(num);
        }

        // 注意：这里 num 只是临时变量，给 num 重新赋值不会改变数组原值
        for (int num : arr) {
            num = 0;
        }
        System.out.println(arr[0]); // 仍然是 11

        // 字符串数组也可以
        String[] names = {"小明", "小红", "小刚"};
        for (String name : names) {
            System.out.println("hello " + name);
        }
    }
}
