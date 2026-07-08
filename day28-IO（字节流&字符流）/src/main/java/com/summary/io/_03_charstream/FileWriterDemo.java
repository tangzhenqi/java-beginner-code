package com.summary.io._03_charstream;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符输出流 FileWriter。
 *
 * 关键 API：
 *   void write(int c)
 *   void write(String str)
 *   void write(String str, int off, int len)
 *   void write(char[] cbuf)
 *   void write(char[] cbuf, int off, int len)
 *
 * flush vs close：
 *   - flush()：把缓冲区的数据真正刷到文件，刷完流仍可继续使用
 *   - close()：先 flush 再关闭流，关闭后再 write 会抛 IOException
 */
public class FileWriterDemo {

    private static final String FILE = "data/writer_demo.txt";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException {
        writeAllOverloads();
        flushVsClose();
    }

    private static void writeAllOverloads() throws IOException {
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write(25105);                       // 写一个字符的码点：'我'
            fw.write("\n你好世界\n");
            fw.write("abcdef", 1, 3);              // 写 "bcd"
            fw.write('\n');
            char[] chars = {'你', '我', '他'};
            fw.write(chars);
            fw.write('\n');
            fw.write(chars, 1, 2);                 // 写 "我他"
        }
    }

    private static void flushVsClose() throws IOException {
        FileWriter fw = new FileWriter(FILE, true); // append
        fw.write("\n[flush]再写一段");
        fw.flush();                                  // 刷完后仍可继续写
        fw.write("\n[flush 之后还能写]");
        fw.close();                                  // close 之后再 write 会抛异常
        try {
            fw.write("after-close");
            System.out.println("不应该走到这里");
        } catch (IOException e) {
            System.out.println("close 后再写：" + e.getClass().getSimpleName());
        }
    }
}
