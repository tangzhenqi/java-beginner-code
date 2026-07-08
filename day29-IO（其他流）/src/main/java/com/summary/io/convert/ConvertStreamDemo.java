package com.summary.io.convert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * 转换流 InputStreamReader / OutputStreamWriter
 *
 * 作用：
 *      1. 字节流 ←→ 字符流的桥梁。
 *      2. 可以指定字符集，按照特定字符编码读写文件。
 *
 * 构造方法：
 *      InputStreamReader(InputStream in, Charset cs)
 *      OutputStreamWriter(OutputStream out, Charset cs)
 *
 * 注意（JDK11 起）：
 *      指定字符编码时推荐直接使用 FileReader(File, Charset)
 *      或 FileWriter(File, Charset) 替代转换流。
 */
public class ConvertStreamDemo {
    public static void main(String[] args) throws IOException {
        // 用 GBK 写入，用 UTF-8 读取，演示乱码场景
        writeGbk("resources/gbk.txt", "你好，世界");
        readAs("resources/gbk.txt", StandardCharsets.UTF_8);   // 乱码
        readAs("resources/gbk.txt", java.nio.charset.Charset.forName("GBK")); // 正常
    }

    private static void writeGbk(String path, String text) throws IOException {
        try (OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream(path), java.nio.charset.Charset.forName("GBK"))) {
            osw.write(text);
        }
    }

    private static void readAs(String path, java.nio.charset.Charset charset) throws IOException {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(path), charset)) {
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = isr.read()) != -1) {
                sb.append((char) ch);
            }
            System.out.println("使用 " + charset + " 读取结果：" + sb);
        }
    }
}
