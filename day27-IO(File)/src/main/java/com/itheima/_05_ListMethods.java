package com.itheima;

import java.io.File;
import java.util.Arrays;

/**
 * 知识点 15：File 的遍历方法（仅一层，不递归）
 *
 *   static File[] listRoots()                       系统所有盘符
 *   String[] list()                                 当前目录下所有名字（仅名字，无路径）
 *   String[] list(FilenameFilter filter)            按名字过滤
 *   File[]   listFiles()                            当前目录下所有 File（带完整路径，最常用）
 *   File[]   listFiles(FileFilter filter)           按 File 过滤
 *   File[]   listFiles(FilenameFilter filter)       按 (dir, name) 过滤
 *
 * 容易踩坑的"特殊返回值"：
 *   - 当 File 不存在 / 不是目录 / 无权限时，listFiles() 返回 null，而不是空数组。
 *     这意味着 "for (File f : src.listFiles())" 在边界条件下会 NPE，遍历前要判空。
 *   - 空目录返回长度为 0 的数组，这才是"目录里没文件"的情况。
 */
public class _05_ListMethods {
    public static void main(String[] args) {
        // 1) 列出盘符（Mac/Linux 通常只有 "/"，Windows 是 C:\ D:\ ...）
        System.out.println("roots = " + Arrays.toString(File.listRoots()));

        // 2) 准备测试目录
        File base = new File("target/playground");
        base.mkdirs();
        new File(base, "a.txt").getParentFile().mkdirs();

        // 3) 遍历（最常用：listFiles）
        File[] children = base.listFiles();
        if (children == null) {
            System.out.println("listFiles 返回 null —— base 不存在或不可读");
            return;
        }
        for (File c : children) {
            System.out.println(c + " | isFile=" + c.isFile());
        }
    }
}
