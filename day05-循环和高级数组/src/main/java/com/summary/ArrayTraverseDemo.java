package com.summary;

/**
 * 知识点5：数组遍历
 *
 *   开始条件：0
 *   结束条件：数组长度 - 1（最大索引）
 *
 *   惯用模板：
 *     for (int i = 0; i < arr.length; i++) {
 *         // 通过 arr[i] 访问元素
 *     }
 *
 *   IDEA 中输入"数组名.fori"会自动生成上面的模板，提高编码效率。
 *
 *   遍历可用于求和、求最值、查找、过滤等几乎所有数组操作。
 */
public class ArrayTraverseDemo {
    public static void main(String[] args) {
        int[] arr = {11, 22, 33, 44, 55};

        // 基础遍历：打印所有元素
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // 求和
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println("总和 = " + sum); // 165

        // 求最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("最大值 = " + max); // 55
    }
}
