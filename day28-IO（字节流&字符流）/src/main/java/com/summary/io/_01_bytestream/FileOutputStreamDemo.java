package com.summary.io._01_bytestream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 字节输出流 FileOutputStream。
 *
 * 三个 write 重载：
 *   void write(int b)                       一次写一个字节
 *   void write(byte[] b)                    一次写整个字节数组
 *   void write(byte[] b, int off, int len)  写字节数组的一部分
 *
 * 构造细节：
 *   - 父目录必须存在，文件不存在会自动创建
 *   - 默认覆盖；第二个参数 true 开启续写
 *
 * 跨平台换行：Windows \r\n，Linux \n，Mac \r
 */
public class FileOutputStreamDemo {

    private static final String FILE = "data/fos_demo.txt";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException {
        writeSingleByte();
        writeByteArray();
        writeWithAppendAndNewline();
        System.out.println("写出完毕，输出文件：" + FILE);
    }

    /** 1. 一次写一个字节（int 实际取低 8 位） */
    private static void writeSingleByte() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(FILE)) {
            fos.write(97);  // 'a'
            fos.write(98);  // 'b'
            fos.write(99);  // 'c'
        }
    }

    /** 2. 一次写整个字节数组 / 写一部分 */
    private static void writeByteArray() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(FILE)) {
            byte[] bytes = {97, 98, 99, 100, 101}; // a b c d e
            fos.write(bytes);              // 写全部
            fos.write(bytes, 1, 2);        // 只写 b c
        }
    }

    /** 3. 续写 + 换行 */
    private static void writeWithAppendAndNewline() throws IOException {
        // append = true：保留原内容，追加在末尾
        try (FileOutputStream fos = new FileOutputStream(FILE, true)) {
            fos.write("\r\n".getBytes(StandardCharsets.UTF_8));
            fos.write("第二行内容".getBytes(StandardCharsets.UTF_8));
            fos.write("\r\n".getBytes(StandardCharsets.UTF_8));
            fos.write("666".getBytes(StandardCharsets.UTF_8));
        }
    }
}
