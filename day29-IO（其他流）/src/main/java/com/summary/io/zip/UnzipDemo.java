package com.summary.io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 解压缩流 ZipInputStream
 *
 * 解压原理：
 *      压缩包是一种特殊的"目录树"，通过 getNextEntry() 依次取出每一个文件/文件夹的描述对象 ZipEntry，
 *      按照 entry 的层级在目标目录下创建文件夹或写出文件即可。
 *
 * 关键 API：
 *      ZipInputStream(InputStream in)
 *      ZipEntry getNextEntry()             获取下一个条目
 *      boolean  ZipEntry.isDirectory()     当前条目是否为目录
 *      String   ZipEntry.toString()        条目相对路径
 *      void     closeEntry()               关闭当前条目
 */
public class UnzipDemo {
    public static void main(String[] args) throws IOException {
        File src = new File("resources/sample.zip");
        File dest = new File("resources/unzip-out");
        if (!src.exists()) {
            System.out.println("请先准备 " + src.getAbsolutePath());
            return;
        }
        if (!dest.exists()) {
            dest.mkdirs();
        }
        unzip(src, dest);
    }

    public static void unzip(File src, File dest) throws IOException {
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(src))) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                File current = new File(dest, entry.getName());
                if (entry.isDirectory()) {
                    current.mkdirs();
                } else {
                    // 兼容压缩包中只有文件没有显式目录条目的情况
                    File parent = current.getParentFile();
                    if (parent != null && !parent.exists()) {
                        parent.mkdirs();
                    }
                    try (FileOutputStream fos = new FileOutputStream(current)) {
                        byte[] buf = new byte[8192];
                        int len;
                        while ((len = zip.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }
                    }
                    zip.closeEntry();
                }
            }
        }
        System.out.println("解压完成 -> " + dest.getAbsolutePath());
    }
}
