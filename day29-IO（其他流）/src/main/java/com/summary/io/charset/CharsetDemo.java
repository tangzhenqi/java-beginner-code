package com.summary.io.charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 字符集（Charset）相关知识点
 *
 * 1. 常见字符集：
 *      ASCII       1 字节，仅英文及控制字符
 *      GBK         中文常用，1-2 字节
 *      UTF-8       Unicode 的可变长度编码，英文 1 字节，中文一般 3 字节（含 BOM 时多 3 字节）
 *      UTF-16      Unicode 的等长 / 可变长度编码，常见 2 字节
 *
 * 2. String 与字节的转换：
 *      byte[] getBytes()                        使用 JVM 默认字符集编码
 *      byte[] getBytes(Charset charset)         使用指定字符集编码
 *      new String(byte[] bytes, Charset cs)     用指定字符集解码字节
 *
 * 3. 乱码本质：编码和解码使用的字符集不一致。
 */
public class CharsetDemo {
    public static void main(String[] args) throws Exception {
        String text = "中国a";

        // 1. 用 UTF-8 编码
        byte[] utf8 = text.getBytes(StandardCharsets.UTF_8);
        System.out.println("UTF-8 编码后字节：" + Arrays.toString(utf8));

        // 2. 用 GBK 编码
        byte[] gbk = text.getBytes("GBK");
        System.out.println("GBK 编码后字节：" + Arrays.toString(gbk));

        // 3. 解码（使用相同字符集）
        String decoded = new String(utf8, StandardCharsets.UTF_8);
        System.out.println("UTF-8 正确解码：" + decoded);

        // 4. 解码（字符集不一致——出现乱码）
        String wrong = new String(utf8, Charset.forName("GBK"));
        System.out.println("用 GBK 解码 UTF-8 字节（乱码）：" + wrong);

        // 5. JVM 默认字符集
        System.out.println("JVM 默认字符集：" + Charset.defaultCharset());
    }
}
