package com.summary.io._03_charstream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符输入流 FileReader。
 *
 * 字符流底层仍是字节流，但会一次读取 "一个完整字符" 的字节并解码：
 *   - 英文 UTF-8 → 1 字节
 *   - 中文 UTF-8 → 3 字节
 * read() 返回的是字符的十进制码点，强转 (char) 即可显示。
 */
public class FileReaderDemo {

    private static final String FILE = "data/reader_demo.txt";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException {
        prepare();
        System.out.println("-- 单字符读取 --");
        readByChar();
        System.out.println("-- 字符数组读取 --");
        readByCharArray();
    }

    private static void prepare() throws IOException {
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write("hello, 你好世界!");
        }
    }

    private static void readByChar() throws IOException {
        try (FileReader fr = new FileReader(FILE)) {
            int ch;
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println();
        }
    }

    private static void readByCharArray() throws IOException {
        try (FileReader fr = new FileReader(FILE)) {
            char[] buf = new char[4];
            int len;
            while ((len = fr.read(buf)) != -1) {
                System.out.print(new String(buf, 0, len));
            }
            System.out.println();
        }
    }
}
