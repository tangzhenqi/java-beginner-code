package com.itheima;

import java.io.File;
import java.io.IOException;

/**
 * 知识点 14：File 的创建与删除方法
 *
 *   public boolean createNewFile()   创建空文件
 *       - 父路径必须存在，否则抛 IOException
 *       - 已存在则返回 false（注意：返回值，不是异常）
 *       - 路径里不带后缀也会创建一个"无扩展名文件"
 *
 *   public boolean mkdir()           创建单级文件夹
 *   public boolean mkdirs()          创建多级文件夹（推荐，包含 mkdir 的能力）
 *       - 已存在返回 false
 *
 *   public boolean delete()          删除文件 / 空文件夹
 *       - 不走回收站！
 *       - 非空文件夹会直接 false，不会递归删
 *       - 删除有内容的目录需要自己递归（见 _09_DeleteTree）
 */
public class _04_CreateAndDelete {
    public static void main(String[] args) throws IOException {
        File base = new File("target/playground/h01");
        System.out.println("mkdirs : " + base.mkdirs());

        File f = new File(base, "demo.txt");
        System.out.println("createNewFile(首次) : " + f.createNewFile());
        System.out.println("createNewFile(重复) : " + f.createNewFile());

        File noExt = new File(base, "noExtension");
        System.out.println("无扩展名文件 createNewFile : " + noExt.createNewFile());

        // 演示 mkdirs 创建多级
        File deep = new File(base, "a/b/c/d");
        System.out.println("mkdirs(多级) : " + deep.mkdirs());

        // 删除
        System.out.println("delete file       : " + f.delete());
        System.out.println("delete 空目录     : " + deep.delete());
        File nonEmpty = new File(base, "a");
        System.out.println("delete 非空目录   : " + nonEmpty.delete() + "  (期望 false)");
    }
}
