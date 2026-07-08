package com.summary.io._04_convert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 转换流：在 字节流 ↔ 字符流 之间架桥，并可以指定字符集。
 *
 *   InputStreamReader (InputStream, Charset) ：字节输入流 → 字符输入流
 *   OutputStreamWriter(OutputStream, Charset)：字符输出流 → 字节输出流
 *
 * JDK11 提供更短的替代方案：
 *   new FileReader(path, Charset)
 *   new FileWriter(path, Charset)
 *
 * 典型用途：读写 GBK 文件、把 GBK 文件转 UTF-8。
 */
public class ConvertStreamDemo {

    private static final String GBK_FILE = "data/gbk.txt";
    private static final String UTF_FILE = "data/utf8.txt";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException {
        writeAsGbk();              // 先生成一个 GBK 文件
        readWithOldStyle();        // 老写法
        readWithJdk11Style();      // JDK11 替代
        convertGbkToUtf8();        // 转换字符集
    }

    private static void writeAsGbk() throws IOException {
        // 老写法
        try (OutputStreamWriter osw =
                     new OutputStreamWriter(new FileOutputStream(GBK_FILE), Charset.forName("GBK"))) {
            osw.write("你好转换流");
        }
    }

    private static void readWithOldStyle() throws IOException {
        try (InputStreamReader isr =
                     new InputStreamReader(new FileInputStream(GBK_FILE), Charset.forName("GBK"))) {
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = isr.read()) != -1) sb.append((char) ch);
            System.out.println("InputStreamReader 读 GBK: " + sb);
        }
    }

    private static void readWithJdk11Style() throws IOException {
        try (FileReader fr = new FileReader(GBK_FILE, Charset.forName("GBK"))) {
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = fr.read()) != -1) sb.append((char) ch);
            System.out.println("JDK11 FileReader(GBK):  " + sb);
        }
    }

    private static void convertGbkToUtf8() throws IOException {
        try (FileReader fr = new FileReader(GBK_FILE, Charset.forName("GBK"));
             FileWriter fw = new FileWriter(UTF_FILE, StandardCharsets.UTF_8)) {
            int ch;
            while ((ch = fr.read()) != -1) {
                fw.write(ch);
            }
        }
        System.out.println("GBK → UTF-8 转换完毕：" + UTF_FILE);
    }
}
