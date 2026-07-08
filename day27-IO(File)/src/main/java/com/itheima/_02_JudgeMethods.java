package com.itheima;

import java.io.File;
import java.io.IOException;

/**
 * 知识点 12：File 的判断方法
 *
 *   public boolean exists()        路径所代表的文件/文件夹是否存在
 *   public boolean isFile()        是否是文件（存在 且 是文件）
 *   public boolean isDirectory()   是否是文件夹（存在 且 是文件夹）
 *
 * 重点：
 *   - 三个方法都隐含了"存在性"判断 —— 路径不存在时全部返回 false。
 *   - 切记不要"只判 exists 不判 isFile"就当作文件来读，否则碰到同名文件夹会出错。
 */
public class _02_JudgeMethods {
    public static void main(String[] args) throws IOException {
        File base = new File("target/playground");
        base.mkdirs();
        File aFile = new File(base, "a.txt");
        File aDir  = new File(base, "subdir");
        aFile.createNewFile();
        aDir.mkdirs();

        print(aFile);
        print(aDir);
        print(new File(base, "不存在"));
    }

    private static void print(File f) {
        System.out.printf("%-30s exists=%-5s isFile=%-5s isDir=%-5s%n",
                f.getPath(), f.exists(), f.isFile(), f.isDirectory());
    }
}
