package com.summary.io._01_bytestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节输入流 FileInputStream。
 *
 * 关键 API：
 *   int read()                  读一个字节，到末尾返回 -1
 *   int read(byte[] buffer)     读到缓冲数组，返回实际读取长度，末尾返回 -1
 *
 * 注意：
 *   - 文件不存在 → 直接 FileNotFoundException（与输出流不同！）
 *   - 处理 byte[] 时，务必使用 new String(buf, 0, len) 截取有效部分
 */
public class FileInputStreamDemo {

    private static final String FILE = "data/fis_demo.txt";

    public static void main(String[] args) throws IOException {
        /// new java.io.File("data") //此时只是在内存中创建了一个表示路径的对象,磁盘上还没有真正创建文件夹
        /// .mkdirs() //真正在磁盘上创建目录的方法,使用相对路径来创建.
        /// 相对路径的"基准点"是 JVM 启动时的当前工作目录(user.dir).可以通过 System.getProperty("user.dir") 查看。
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException {
        prepareFile();

        System.out.println("-- 逐字节读取 --");
        readByteByByte();

        System.out.println("-- 字节数组读取 --");
        readWithBuffer();
    }

    private static void prepareFile() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(FILE)) {
            // FileOutputStream 是字节流，write() 只接收 byte / byte[]，没有 write(String) 方法。
            // 所以要先用 getBytes() 把字符串“编码”成字节数组（默认平台字符集，涉及中文建议显式指定 UTF-8）。
            // 若想直接写字符串，应改用字符流 FileWriter（它的 write(String) 可直接接收字符串）。
            fos.write("HelloIO".getBytes());
        }
    }

    private static void readByteByByte() throws IOException {
        try (FileInputStream fis = new FileInputStream(FILE)) {
            int b;
            while ((b = fis.read()) != -1) {
                System.out.print((char) b);
            }
            System.out.println();
        }
    }

    private static void readWithBuffer() throws IOException {
        try (FileInputStream fis = new FileInputStream(FILE)) {
            byte[] buffer = new byte[3]; // 故意设小，演示多次读取
            int len;
            while ((len = fis.read(buffer)) != -1) {
                System.out.println("本次读到 " + len + " 字节：" + new String(buffer, 0, len));
            }
        }
    }
}
