package com.itheima;

import java.io.File;
import java.util.Arrays;

/**
 * 知识点 16：用过滤器筛选文件
 *
 *   listFiles(FileFilter)     —— accept(File pathname)
 *   listFiles(FilenameFilter) —— accept(File dir, String name)
 *
 * 写法演进：
 *   1) 匿名内部类
 *   2) Lambda（接口是 SAM，可以 lambda 化）
 *   3) 方法引用
 */
public class _06_Filters {
    public static void main(String[] args) {
        File base = new File("target/playground");
        base.mkdirs();
        new File(base, "a.txt");

        // 1) 匿名内部类
        File[] txt1 = base.listFiles(new java.io.FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().endsWith(".txt");
            }
        });
        System.out.println("匿名内部类: " + Arrays.toString(txt1));

        // 2) Lambda（FileFilter 是函数式接口）
        File[] txt2 = base.listFiles(p -> p.isFile() && p.getName().endsWith(".txt"));
        System.out.println("Lambda     : " + Arrays.toString(txt2));

        // 3) FilenameFilter (dir, name)
        File[] txt3 = base.listFiles((dir, name) -> name.endsWith(".txt"));
        System.out.println("FilenameFilter: " + Arrays.toString(txt3));
    }
}
