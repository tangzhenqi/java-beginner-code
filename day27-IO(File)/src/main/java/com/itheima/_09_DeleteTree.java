package com.itheima;

import java.io.File;

/**
 * 知识点 19：递归删除整棵目录
 *
 *   思路："先儿子，再自己"：
 *     1) 递归删除子项
 *     2) 删除自己（这时已经空了）
 *
 *   ⚠ 安全提示：
 *     - 这是一个不可逆操作！不走回收站。
 *     - 演示限制在 target/playground/delete-demo，避免误删用户数据。
 */
public class _09_DeleteTree {
    public static void main(String[] args) {
        File root = new File("target/playground/delete-demo");
        prepareSamples(root);

        boolean ok = deleteTree(root);
        System.out.println("删除结果: " + ok + ", 还存在? " + root.exists());
    }

    public static boolean deleteTree(File src) {
        if (src == null || !src.exists()) return false;
        if (src.isDirectory()) {
            File[] children = src.listFiles();
            if (children != null) {
                for (File f : children) {
                    deleteTree(f);
                }
            }
        }
        return src.delete();
    }

    private static void prepareSamples(File root) {
        try {
            new File(root, "a/b/c").mkdirs();
            new File(root, "a/b/c/leaf.txt").createNewFile();
            new File(root, "top.txt").createNewFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
