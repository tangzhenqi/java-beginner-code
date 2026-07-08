package com.summary.io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩流 ZipOutputStream
 *
 * 压缩原理：
 *      把要压缩的文件/文件夹按照层级关系写入 ZipOutputStream，
 *      每写出一个文件前要先 putNextEntry()，写完调用 closeEntry()。
 *
 * 关键 API：
 *      ZipOutputStream(OutputStream out)
 *      void putNextEntry(ZipEntry e)       开始一个新条目
 *      void closeEntry()                   关闭当前条目
 */
public class ZipDemo {
    public static void main(String[] args) throws IOException {
        File src = new File("resources");          // 要压缩的目录
        File destZip = new File("resources-archive.zip");
        zipDir(src, destZip);
    }

    /** 压缩一个目录到 zip 文件 */
    public static void zipDir(File srcDir, File destZip) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZip))) {
            zipRecursive(srcDir, zos, srcDir.getName());
        }
        System.out.println("压缩完成 -> " + destZip.getAbsolutePath());
    }

    /**
     * 递归压缩
     * @param file       当前正在处理的文件或目录
     * @param zos        压缩输出流
     * @param entryName  在压缩包中的相对路径
     */
    private static void zipRecursive(File file, ZipOutputStream zos, String entryName) throws IOException {
        if (file.isDirectory()) {
            // 空目录也需要保留
            zos.putNextEntry(new ZipEntry(entryName + "/"));
            zos.closeEntry();
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    zipRecursive(child, zos, entryName + "/" + child.getName());
                }
            }
        } else {
            zos.putNextEntry(new ZipEntry(entryName));
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buf = new byte[8192];
                int len;
                while ((len = fis.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
            }
            zos.closeEntry();
        }
    }
}
