package com.itheima._03_recursion;

import java.io.File;

/**
 * 拓展：用递归遍历文件夹（深度优先）
 *
 * 思路：
 *   - 如果遇到的是文件，处理它（这里只打印）；
 *   - 如果遇到的是文件夹，递归处理它里面的每一项；
 *   - 出口：null（不存在的路径）或 listFiles() 为空。
 *
 * 该模式可拓展为：统计文件大小、按名称搜索、批量改名等。
 */
public class RecursionFileSearchDemo {
    public static void main(String[] args) {
        // 用本工程的根目录做示例
        File root = new File(System.getProperty("user.dir"));
        System.out.println("开始遍历: " + root.getAbsolutePath());
        countAndPrint(root, ".java", 0, new int[1]);
    }

    /**
     * @param dir       目录或文件
     * @param suffix    只关心的文件后缀，如 ".java"
     * @param depth     当前递归深度，用于缩进展示
     * @param counter   通过数组传引用，记录匹配文件个数
     */
    public static void countAndPrint(File dir, String suffix, int depth, int[] counter) {
        if (dir == null || !dir.exists()) return;     // 出口 1
        if (dir.isFile()) {
            if (dir.getName().endsWith(suffix)) {
                counter[0]++;
                if (counter[0] <= 5) {                 // 只展示前 5 个，避免输出过长
                    System.out.println(indent(depth) + dir.getName());
                }
            }
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) return;                     // 出口 2：权限不足或目录为空
        for (File f : files) {
            countAndPrint(f, suffix, depth + 1, counter);
        }
        if (depth == 0) {
            System.out.println("共匹配到 " + suffix + " 文件: " + counter[0] + " 个");
        }
    }

    private static String indent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) sb.append("  ");
        return sb.toString();
    }
}
