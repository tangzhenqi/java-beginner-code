package com.summary.io.print;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 字符打印流 PrintWriter
 *
 * 构造方法：
 *      PrintWriter(Writer w, boolean autoFlush)
 *      PrintWriter(OutputStream out, boolean autoFlush)
 *      PrintWriter(String fileName, Charset charset)
 *
 * 特有方法和 PrintStream 类似（println/print/printf）。
 *
 * 与 PrintStream 的区别：
 *      PrintStream  —— 字节流（在最底层）
 *      PrintWriter  —— 字符流（适合写文本）
 *      均不会抛 IOException；调用 checkError() 检查是否出错。
 */
public class PrintWriterDemo {
    public static void main(String[] args) throws IOException {
        // 第二个参数 true 表示开启自动刷新（println/printf/format 调用后会自动 flush）
        try (PrintWriter pw = new PrintWriter(new FileWriter("resources/print_writer.txt"), true)) {
            pw.println("使用 PrintWriter 写文本");
            pw.printf("姓名：%s, 年龄：%d%n", "张三", 20);
            pw.print("最后一行不换行");

            // 没有抛 IOException，但是可以检查
            if (pw.checkError()) {
                System.err.println("PrintWriter 出错了");
            }
        }
    }
}
