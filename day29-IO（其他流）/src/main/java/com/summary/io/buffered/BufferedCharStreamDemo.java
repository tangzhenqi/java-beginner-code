package com.summary.io.buffered;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符缓冲流 BufferedReader / BufferedWriter
 *
 * 构造方法：
 *      public BufferedReader(Reader r)
 *      public BufferedWriter(Writer w)
 *
 * 特有方法：
 *      BufferedReader.readLine()      读取一整行（不包含换行符），读到末尾返回 null
 *      BufferedWriter.newLine()       跨平台换行（根据操作系统决定 \n 或 \r\n）
 *
 * 缓冲字符流的默认缓冲区也是 8192 个字符（约 16KB）。
 */
public class BufferedCharStreamDemo {
    public static void main(String[] args) throws IOException {
        readLines();
        writeLines();
    }

    /** 利用 readLine 按行读取 */
    private static void readLines() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("resources/a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /** 利用 newLine 跨平台换行写出 */
    private static void writeLines() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("resources/copy_char.txt"))) {
            bw.write("第一行");
            bw.newLine();
            bw.write("第二行");
            bw.newLine();
            bw.write("第三行");
        }
        System.out.println("字符缓冲流写出完成");
    }
}
