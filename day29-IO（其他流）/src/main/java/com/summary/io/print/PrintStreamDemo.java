package com.summary.io.print;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

/**
 * 字节打印流 PrintStream
 *
 * 构造方法：
 *      PrintStream(OutputStream/File/String)
 *      PrintStream(String fileName, Charset charset)
 *      PrintStream(OutputStream out, boolean autoFlush)
 *      PrintStream(OutputStream out, boolean autoFlush, String encoding)
 *
 * 特有方法：
 *      println(Xxx)        打印 + 换行 + （在 autoFlush=true 时）自动刷新
 *      print(Xxx)          打印不换行
 *      printf(format, ...) 格式化打印
 *      write(int b)        和普通字节输出流一样，按字节写
 *
 * 特点：
 *      1. 只能写出，不能读取；
 *      2. 异常时不会抛出 IOException，而是由 checkError() 上报；
 *      3. System.out 就是一个 PrintStream。
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws IOException {
        try (PrintStream ps = new PrintStream(
                new FileOutputStream("resources/print.txt"),
                true,
                Charset.forName("UTF-8"))) {

            ps.println(97);                       // 输出 97 + 换行
            ps.println("Hello World");
            ps.print(true);
            ps.println();
            ps.printf("%s爱上了%s%n", "阿珍", "阿强");
            ps.write(65);                         // 写出字节 65 -> 字符 'A'
        }

        // System.out 也是 PrintStream，可重定向到文件
        PrintStream origin = System.out;
        try (PrintStream toFile = new PrintStream("resources/redirect.txt")) {
            System.setOut(toFile);
            System.out.println("这一行去了文件");
        } finally {
            System.setOut(origin);
            System.out.println("控制台又恢复了");
        }
    }
}
