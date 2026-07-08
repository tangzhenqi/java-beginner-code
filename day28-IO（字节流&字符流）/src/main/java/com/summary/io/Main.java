package com.summary.io;

import com.summary.io._07_advanced.PrintStreamDemo;
import com.summary.io._05_buffered.BufferedByteStreamDemo;
import com.summary.io._05_buffered.BufferedCharStreamDemo;
import com.summary.io._05_buffered.BufferedCompareDemo;
import com.summary.io._01_bytestream.FileCopyDemo;
import com.summary.io._01_bytestream.FileInputStreamDemo;
import com.summary.io._01_bytestream.FileOutputStreamDemo;
import com.summary.io._01_bytestream.TryWithResourcesDemo;
import com.summary.io._02_charset.CharsetDemo;
import com.summary.io._03_charstream.CharStreamCopyDemo;
import com.summary.io._03_charstream.FileReaderDemo;
import com.summary.io._03_charstream.FileWriterDemo;
import com.summary.io._04_convert.ConvertStreamDemo;
import com.summary.io._06_object.ObjectInputDemo;
import com.summary.io._06_object.ObjectListDemo;
import com.summary.io._06_object.ObjectOutputDemo;

import java.io.File;

/**
 * day28 IO 知识点总入口：按章节顺序依次运行所有 Demo。
 * 单独运行某个 Demo 也可以直接在 IDE 里点该类的 main。
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ensureDataDir();

        section("1. 字节输出流 FileOutputStream");
        FileOutputStreamDemo.run();

        section("2. 字节输入流 FileInputStream");
        FileInputStreamDemo.run();

        section("3. 文件拷贝 - 单字节 vs 字节数组");
        FileCopyDemo.run();

        section("4. try-with-resources（JDK7 / JDK9）");
        TryWithResourcesDemo.run();

        section("5. 字符集 编码 / 解码");
        CharsetDemo.run();

        section("6. 字符输入流 FileReader");
        FileReaderDemo.run();

        section("7. 字符输出流 FileWriter（flush vs close）");
        FileWriterDemo.run();

        section("8. 字符流拷贝（仅文本）");
        CharStreamCopyDemo.run();

        section("9. 转换流 + JDK11 替代方案");
        ConvertStreamDemo.run();

        section("10. 字节缓冲流 BufferedInput/OutputStream");
        BufferedByteStreamDemo.run();

        section("11. 字符缓冲流 BufferedReader/Writer");
        BufferedCharStreamDemo.run();

        section("12. 四种拷贝方式性能对比（拓展）");
        BufferedCompareDemo.run();

        section("13. 序列化 ObjectOutputStream");
        ObjectOutputDemo.run();

        section("14. 反序列化 ObjectInputStream");
        ObjectInputDemo.run();

        section("15. 序列化集合（拓展）");
        ObjectListDemo.run();

        section("16. PrintStream / System.out 重定向（拓展）");
        PrintStreamDemo.run();
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("==== " + title + " ====");
    }

    private static void ensureDataDir() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
