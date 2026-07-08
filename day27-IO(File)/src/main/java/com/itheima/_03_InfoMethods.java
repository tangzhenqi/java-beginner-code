package com.itheima;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 知识点 13：File 的信息获取方法
 *
 *   public long   length()           文件字节数 (仅文件有效，文件夹返回 0)
 *   public String getName()          文件/文件夹名（带后缀）
 *   public String getPath()          构造时传入的路径
 *   public String getAbsolutePath()  绝对路径（结合 user.dir 解析）
 *   public long   lastModified()     最后修改时间（毫秒值）
 *
 * 易错点：
 *   - length() 无法直接得到"文件夹大小"，需要递归累加。
 *   - getName() 对文件夹返回的就是文件夹名；对文件返回的是 "xx.yyy"，不剥离扩展名。
 */
public class _03_InfoMethods {
    public static void main(String[] args) throws IOException {
        File dir = new File("target/playground");
        dir.mkdirs();
        File f = new File(dir, "info.txt");
        if (!f.exists()) {
            f.createNewFile();
            // 仅为了 length() 有数据：用 RandomAccessFile 写 12 字节
            try (java.io.RandomAccessFile raf = new java.io.RandomAccessFile(f, "rw")) {
                raf.write("Hello, day27".getBytes());
            }
        }

        System.out.println("name             = " + f.getName());
        System.out.println("path             = " + f.getPath());
        System.out.println("absolutePath     = " + f.getAbsolutePath());
        System.out.println("length(bytes)    = " + f.length());

        long ts = f.lastModified();
        System.out.println("lastModified(ms) = " + ts);
        System.out.println("lastModified(fmt)= " +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ts)));
    }
}
