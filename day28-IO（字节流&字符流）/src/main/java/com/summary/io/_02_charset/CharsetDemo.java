package com.summary.io._02_charset;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 字符集 / 编码 / 解码。
 *
 * 重点：
 *   - UTF-8：英文 1 字节、中文常见汉字 3 字节
 *   - GBK：英文 1 字节、中文 2 字节
 *   - 编码：String -> byte[]   str.getBytes(charset)
 *   - 解码：byte[] -> String   new String(bytes, charset)
 *
 * "乱码" 的两大根因：
 *   1) 编码和解码用了 **不同** 的字符集
 *   2) 字节流读取中文时，只读了 "半个汉字" 就解码（缓冲区切分错位）
 */
public class CharsetDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        run();
    }

    public static void run() throws UnsupportedEncodingException {
        encodeDecode();
        wrongCharsetCauseMojibake();
        splitChineseCauseMojibake();
    }

    /** 1. 正常编码 / 解码 */
    private static void encodeDecode() throws UnsupportedEncodingException {
        String str = "ai你哟";

        byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
        byte[] gbk = str.getBytes("GBK");

        System.out.println("UTF-8 字节: " + Arrays.toString(utf8) + "  长度=" + utf8.length);
        System.out.println("GBK   字节: " + Arrays.toString(gbk) + "  长度=" + gbk.length);

        System.out.println("UTF-8 解码: " + new String(utf8, StandardCharsets.UTF_8));
        System.out.println("GBK   解码: " + new String(gbk, "GBK"));
    }

    /** 2. 错配字符集 → 乱码 */
    private static void wrongCharsetCauseMojibake() throws UnsupportedEncodingException {
        String str = "中国";
        byte[] gbk = str.getBytes("GBK");
        String wrong = new String(gbk, StandardCharsets.UTF_8); // GBK 编 → UTF-8 解
        System.out.println("错配解码结果: " + wrong);
    }

    /** 3. 字节流按 1 字节读中文 → "半个汉字" → 乱码 */
    private static void splitChineseCauseMojibake() {
        String str = "你好";
        byte[] utf8 = str.getBytes(StandardCharsets.UTF_8); // 6 字节
        // 模拟 "只读到 4 字节就解码"
        byte[] partial = Arrays.copyOf(utf8, 4);
        System.out.println("半个汉字解码: " + new String(partial, Charset.forName("UTF-8")));
    }
}
