package com.itheima;

import java.io.File;

/**
 * 知识点 18：递归统计文件夹大小
 *
 *   File.length() 对目录返回 0，需要递归累加所有子文件的 length。
 *   注意 long 累加，避免大目录溢出。
 */
public class _08_FolderSize {
    public static void main(String[] args) {
        File dir = new File("target/playground");
        long bytes = sizeOf(dir);
        System.out.println(dir + " 总大小 = " + bytes + " B ≈ " + human(bytes));
    }

    public static long sizeOf(File src) {
        if (src == null || !src.exists()) return 0L;
        if (src.isFile()) return src.length();
        File[] children = src.listFiles();
        if (children == null) return 0L;
        long sum = 0L;
        for (File f : children) {
            sum += sizeOf(f);
        }
        return sum;
    }

    private static String human(long bytes) {
        double v = bytes;
        String[] unit = {"B", "KB", "MB", "GB", "TB"};
        int i = 0;
        while (v >= 1024 && i < unit.length - 1) { v /= 1024; i++; }
        return String.format("%.2f %s", v, unit[i]);
    }
}
