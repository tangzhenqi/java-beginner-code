package com.summary.io._05_buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 拓展：四种拷贝方式的耗时对比，直观感受缓冲流 / 缓冲数组的威力。
 *
 *   方式 A：FileInput/OutputStream + 单字节
 *   方式 B：FileInput/OutputStream + 字节数组
 *   方式 C：Buffered + 单字节
 *   方式 D：Buffered + 字节数组（最快）
 */
public class BufferedCompareDemo {

    private static final String SRC = "data/src.bin";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        if (!new java.io.File(SRC).exists()) {
            com.summary.io._01_bytestream.FileCopyDemo.run();
        }
        run();
    }

    public static void run() throws IOException {
        long a = time(() -> copyA(SRC, "data/cmp_a.bin"));
        long b = time(() -> copyB(SRC, "data/cmp_b.bin"));
        long c = time(() -> copyC(SRC, "data/cmp_c.bin"));
        long d = time(() -> copyD(SRC, "data/cmp_d.bin"));

        System.out.println("A. 原始流 + 单字节   : " + a + " ms");
        System.out.println("B. 原始流 + 字节数组 : " + b + " ms");
        System.out.println("C. 缓冲流 + 单字节   : " + c + " ms");
        System.out.println("D. 缓冲流 + 字节数组 : " + d + " ms");
    }

    private static void copyA(String src, String dest) throws IOException {
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest)) {
            int b;
            while ((b = fis.read()) != -1) fos.write(b);
        }
    }

    private static void copyB(String src, String dest) throws IOException {
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest)) {
            byte[] buf = new byte[8192];
            int len;
            while ((len = fis.read(buf)) != -1) fos.write(buf, 0, len);
        }
    }

    private static void copyC(String src, String dest) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))) {
            int b;
            while ((b = bis.read()) != -1) bos.write(b);
        }
    }

    private static void copyD(String src, String dest) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))) {
            byte[] buf = new byte[8192];
            int len;
            while ((len = bis.read(buf)) != -1) bos.write(buf, 0, len);
        }
    }

    @FunctionalInterface
    private interface IoTask {
        void run() throws IOException;
    }

    private static long time(IoTask task) throws IOException {
        long t = System.currentTimeMillis();
        task.run();
        return System.currentTimeMillis() - t;
    }
}
