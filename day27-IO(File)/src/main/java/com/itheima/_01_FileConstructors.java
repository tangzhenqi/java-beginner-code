package com.itheima;

import java.io.File;

/**
 * 知识点 11：File 的三种构造
 *
 *   public File(String pathname)              单一字符串路径
 *   public File(String parent, String child)  父路径(字符串) + 子路径(字符串)
 *   public File(File parent, String child)    父路径(File)    + 子路径(字符串)
 *
 * 重要细节：
 *   1) File 仅是"路径名"的抽象，构造它本身不会做"文件是否存在"的检查。
 *   2) Windows 路径分隔符是 '\\'(转义)，Linux/Mac 是 '/'。跨平台优先用 File.separator 或 Paths。
 *   3) 相对路径相对的是 "user.dir"，IDE 与命令行的行为可能不同，需要心里有数。
 */
public class _01_FileConstructors {
    public static void main(String[] args) {
        String sep = File.separator;
        System.out.println("user.dir = " + System.getProperty("user.dir"));
        System.out.println("separator = " + sep);

        // 方式1：字符串拼好的完整路径
        File f1 = new File("data" + sep + "a.txt");
        System.out.println("f1 = " + f1);

        // 方式2：父路径字符串 + 子路径字符串
        File f2 = new File("data", "a.txt");
        System.out.println("f2 = " + f2);

        // 方式3：父路径 File + 子路径字符串（推荐：父级可复用）
        File parent = new File("data");
        File f3 = new File(parent, "a.txt");
        System.out.println("f3 = " + f3);

        // 构造不会检查是否存在
        File ghost = new File("不存在的目录" + sep + "幽灵.txt");
        System.out.println("ghost = " + ghost + ", exists = " + ghost.exists());
    }
}
