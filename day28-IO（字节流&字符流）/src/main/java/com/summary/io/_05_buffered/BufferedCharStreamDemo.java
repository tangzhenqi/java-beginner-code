package com.summary.io._05_buffered;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符缓冲流。
 *
 *   BufferedReader(Reader)   - 特有：String readLine()        读一整行（不含换行符）
 *   BufferedWriter(Writer)   - 特有：void newLine()           跨平台换行
 *
 * 这两个特有方法是面试和工作中最常用的字符流方法。
 */
public class BufferedCharStreamDemo {

    private static final String FILE = "data/buffered_lines.txt";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException {
        writeLines();
        readLines();
    }

    private static void writeLines() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            bw.write("第一行");
            bw.newLine();
            bw.write("第二行 hello");
            bw.newLine();
            bw.write("第三行 12345");
            bw.newLine();
        }
    }

    private static void readLines() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int idx = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("[" + (idx++) + "] " + line);
            }
        }
    }
}
