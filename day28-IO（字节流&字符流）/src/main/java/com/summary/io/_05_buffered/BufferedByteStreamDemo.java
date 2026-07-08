package com.summary.io._05_buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节缓冲流。
 *
 *   BufferedInputStream(InputStream)
 *   BufferedOutputStream(OutputStream)
 *
 * 原理：内部维护一个默认 8KB 的字节数组缓冲区。
 * - 读：一次从底层读 8KB 到缓冲区，应用层 read() 直接从缓冲区取
 * - 写：先写到缓冲区，满了再 flush 到底层
 * → 大幅减少 read/write 的 native 系统调用次数。
 */
public class BufferedByteStreamDemo {

    private static final String SRC = "data/src.bin";
    private static final String DEST = "data/copy_buffered.bin";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        // 依赖 FileCopyDemo 生成的 src.bin，缺则补
        if (!new java.io.File(SRC).exists()) {
            com.summary.io._01_bytestream.FileCopyDemo.run();
        }
        run();
    }

    public static void run() throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(SRC));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(DEST))) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
        }
        System.out.println("字节缓冲流拷贝完成 -> " + DEST);
    }
}
