package com.summary.io._03_charstream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流拷贝。
 *
 * 注意：字符流 **只能用于文本文件**，
 * 拷贝图片 / 音视频等二进制文件请使用字节流。
 */
public class CharStreamCopyDemo {

    private static final String SRC = "data/writer_demo.txt";
    private static final String DEST = "data/writer_demo_copy.txt";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        // 若 src 不存在，先用 FileWriterDemo 准备一份
        if (!new java.io.File(SRC).exists()) {
            FileWriterDemo.run();
        }
        run();
    }

    public static void run() throws IOException {
        try (FileReader fr = new FileReader(SRC);
             FileWriter fw = new FileWriter(DEST)) {
            char[] buf = new char[1024];
            int len;
            while ((len = fr.read(buf)) != -1) {
                fw.write(buf, 0, len);
            }
        }
        System.out.println("文本文件拷贝完成 -> " + DEST);
    }
}
