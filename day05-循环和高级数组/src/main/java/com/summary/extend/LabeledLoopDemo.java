package com.summary.extend;

/**
 * 拓展5：带标签的循环跳转
 *
 *   普通的 break / continue 只控制最内层循环；
 *   想一次跳出多层，可以给循环加一个"标签"，再写 break/continue 标签名。
 *
 *   语法：
 *     标签名: for (...) {
 *         for (...) {
 *             break 标签名;     // 跳出外层循环
 *             continue 标签名;  // 跳过外层的当前一次
 *         }
 *     }
 *
 *   提醒：标签会让逻辑变复杂，能用方法+return 替代时，优先用方法。
 */
public class LabeledLoopDemo {
    public static void main(String[] args) {
        // 在二维数组里找第一个 0，找到立即跳出全部循环
        int[][] matrix = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };

        outer:
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    System.out.println("找到 0：位置 (" + i + ", " + j + ")");
                    break outer; // 一次跳出两层
                }
            }
        }
    }
}
