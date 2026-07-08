package com.itheima;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 知识点 17：递归遍历——按扩展名查找
 *
 * 思路 ("进入-遍历-判断-判断")：
 *   1) 进入 src 文件夹
 *   2) 遍历 src.listFiles()
 *   3) 如果是文件，按扩展名判断、加入结果
 *   4) 如果是目录，递归调用自身
 *
 * 健壮性：
 *   - listFiles() 可能返回 null (无权限/不是目录)，必须先判空。
 *   - 避免在巨型根目录（如 "/"）上裸跑，本案例默认只在 target/playground 下演示。
 */
public class _07_FindByExtension {
    public static void main(String[] args) {
        // 构造测试样本
        File root = new File("target/playground/find-demo");
        prepareSamples(root);

        List<File> results = new ArrayList<>();
        findByExt(root, ".txt", results);
        System.out.println("找到的 .txt 文件: ");
        results.forEach(System.out::println);
    }

    public static void findByExt(File src, String ext, List<File> out) {
        File[] children = src.listFiles();
        if (children == null) return;
        for (File f : children) {
            if (f.isFile()) {
                if (f.getName().toLowerCase().endsWith(ext.toLowerCase())) {
                    out.add(f);
                }
            } else {
                findByExt(f, ext, out);
            }
        }
    }

    /** 准备一些样本文件用来演示。 */
    private static void prepareSamples(File root) {
        root.mkdirs();
        try {
            new File(root, "a.txt").createNewFile();
            new File(root, "b.log").createNewFile();
            File sub = new File(root, "sub");
            sub.mkdirs();
            new File(sub, "c.txt").createNewFile();
            new File(sub, "d.jpg").createNewFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
