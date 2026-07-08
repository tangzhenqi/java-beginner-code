package com.summary.io.buffered;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 拓展：缓冲流到底快在哪？
 *
 * 1. FileInputStream 每调用一次 read()，都会触发一次系统调用，
 *    向操作系统申请从硬盘读取数据，频繁的系统调用非常耗时。
 *
 * 2. BufferedInputStream 内部维护 8192 字节的缓冲区。
 *    第一次调用 read() 时，会一次性从硬盘读取 8192 字节到缓冲区，
 *    之后的 read() 直接从内存中取数据，直到缓冲区被读完，
 *    再次触发一次硬盘读取。这样系统调用次数大幅降低。
 *
 * 3. 实测：拷贝大文件时缓冲流通常比裸流快 5-10 倍。
 */
public class BufferedPrincipleDemo {
    public static void main(String[] args) throws IOException {
        // 准备一份较大的测试数据
        prepareBigFile("resources/big.bin", 5 * 1024 * 1024); // 5MB

        long t1 = System.currentTimeMillis();
        copyByOneByteRaw();
        long t2 = System.currentTimeMillis();
        copyByOneByteBuffered();
        long t3 = System.currentTimeMillis();

        System.out.println("裸流逐字节耗时：" + (t2 - t1) + " ms");
        System.out.println("缓冲流逐字节耗时：" + (t3 - t2) + " ms");
    }

    private static void prepareBigFile(String path, int size) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            byte[] data = new byte[size];
            fos.write(data);
        }
    }

    private static void copyByOneByteRaw() throws IOException {
        try (FileInputStream fis = new FileInputStream("resources/big.bin");
             FileOutputStream fos = new FileOutputStream("resources/big_raw_copy.bin")) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
        }
    }

    private static void copyByOneByteBuffered() throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("resources/big.bin"));
             FileOutputStream fos = new FileOutputStream("resources/big_buf_copy.bin")) {
            int b;
            while ((b = bis.read()) != -1) {
                fos.write(b);
            }
        }
    }
}
