package com.summary.extend;

/**
 * 拓展2：循环嵌套
 *
 *   外层循环每执行一次，内层循环就会完整地执行一遍。
 *
 *   总执行次数 = 外层次数 × 内层次数。
 *
 *   经典应用：九九乘法表、打印图形、矩阵遍历。
 */
public class NestedLoopDemo {
    public static void main(String[] args) {
        // 九九乘法表
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + (i * j) + "\t");
            }
            System.out.println();
        }

        System.out.println("---");

        // 打印一个 5 行的右三角
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
