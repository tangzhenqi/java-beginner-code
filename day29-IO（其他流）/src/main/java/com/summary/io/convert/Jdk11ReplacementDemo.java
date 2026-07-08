package com.summary.io.convert;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * JDK11 起的替代方案：FileReader / FileWriter 直接支持指定字符集。
 *
 *      public FileReader(File file, Charset charset)
 *      public FileReader(String fileName, Charset charset)
 *      public FileWriter(File file, Charset charset)
 *      public FileWriter(String fileName, Charset charset)
 *
 * 相比转换流，代码更简洁，不需要先创建 FileInputStream/FileOutputStream。
 */
public class Jdk11ReplacementDemo {
    public static void main(String[] args) throws IOException {
        // 以 GBK 写
        try (FileWriter fw = new FileWriter("resources/gbk2.txt", Charset.forName("GBK"))) {
            fw.write("JDK11 之后推荐这种写法");
        }

        // 以 GBK 读
        try (FileReader fr = new FileReader("resources/gbk2.txt", Charset.forName("GBK"))) {
            int ch;
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println();
        }
    }
}
