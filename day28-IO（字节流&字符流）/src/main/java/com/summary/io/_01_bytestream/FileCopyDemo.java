package com.summary.io._01_bytestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件拷贝示例：核心思想是 "边读边写"。
 *
 * 单字节拷贝 vs 字节数组拷贝：数据量越大，差距越明显。
 * 这里用一个临时生成的 1MB 文件做对比，避免依赖外部资源。
 */
public class FileCopyDemo {

    private static final String SRC = "data/src.bin";
    private static final String DEST1 = "data/copy_single.bin";
    private static final String DEST2 = "data/copy_buffer.bin";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException {
        prepareSource(1024 * 1024); // 1MB

        long t1 = System.currentTimeMillis();
        copySingle();
        long t2 = System.currentTimeMillis();
        copyWithBuffer();
        long t3 = System.currentTimeMillis();

        System.out.println("单字节拷贝耗时：" + (t2 - t1) + " ms");
        System.out.println("字节数组拷贝耗时：" + (t3 - t2) + " ms");
    }

    private static void prepareSource(int bytes) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(SRC)) {
            byte[] data = new byte[bytes];
            for (int i = 0; i < bytes; i++) {
                data[i] = (byte) (i % 128);
            }
            fos.write(data);
        }
    }

    /** 一次一字节，慢 */
    private static void copySingle() throws IOException {
        try (FileInputStream fis = new FileInputStream(SRC);
             FileOutputStream fos = new FileOutputStream(DEST1)) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
        }
    }

    /** 字节数组缓冲，快 */
    private static void copyWithBuffer() throws IOException {
        try (FileInputStream fis = new FileInputStream(SRC);
             FileOutputStream fos = new FileOutputStream(DEST2)) {
            byte[] buf = new byte[8192];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        }
    }
}
