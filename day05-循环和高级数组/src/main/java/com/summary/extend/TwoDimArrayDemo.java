package com.summary.extend;

/**
 * 拓展3：二维数组
 *
 *   本质：数组的数组。每个元素本身也是一个一维数组。
 *
 *   静态初始化：
 *     int[][] arr = {{1, 2, 3}, {4, 5, 6}};
 *
 *   动态初始化：
 *     int[][] arr = new int[2][3]; // 2 行 3 列
 *
 *   遍历：双层循环
 *     外层 i 控制行，内层 j 控制列。
 *
 *   常用属性：
 *     arr.length      —— 行数
 *     arr[i].length   —— 第 i 行的列数（可以不一样长，称为"锯齿数组"）
 */
public class TwoDimArrayDemo {
    public static void main(String[] args) {
        // 三个班级，每个班级若干学生的成绩
        int[][] scores = {
                {88, 92, 75},
                {70, 60},
                {99, 100, 98, 95}
        };

        for (int i = 0; i < scores.length; i++) {
            int sum = 0;
            for (int j = 0; j < scores[i].length; j++) {
                sum += scores[i][j];
            }
            System.out.println("第 " + (i + 1) + " 个班级总分 = " + sum);
        }

        // 动态初始化二维数组
        int[][] matrix = new int[3][3];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = i * 3 + j + 1;
            }
        }
        // 打印矩阵
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
