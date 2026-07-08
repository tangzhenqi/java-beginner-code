package com.summary.io.buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节缓冲流 BufferedInputStream / BufferedOutputStream
 *
 * 1. 构造方法：
 *      public BufferedInputStream(InputStream is)
 *      public BufferedOutputStream(OutputStream os)
 *
 * 2. 原理：内部维护一个长度为 8192 字节的数组（缓冲区），
 *    减少和硬盘的直接交互次数，从而提升效率。
 *
 * 3. 注意：源文件和目标文件不能是同一个，否则会把源文件清空。
 */
public class BufferedByteStreamDemo {
    public static void main(String[] args) throws IOException {
        // 利用字节缓冲流拷贝文件
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("resources/a.txt"));
             BufferedOutputStream bos = new BufferedOutputStream(
                     new FileOutputStream("resources/copy_byte.txt"))) {

            // 方式一：一次读写一个字节
            // int b;
            // while ((b = bis.read()) != -1) {
            //     bos.write(b);
            // }

            // 方式二：一次读写一个字节数组（更高效）
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }

            System.out.println("字节缓冲流拷贝完成");
        }
    }
}
