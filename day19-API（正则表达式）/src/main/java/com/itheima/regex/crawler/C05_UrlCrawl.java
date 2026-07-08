package com.itheima.regex.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点 12：从网络读取文本，并用正则抽取信息
 * <p>
 * 演示思路：
 * <pre>
 *   URL → URLConnection.openConnection() → InputStream → BufferedReader 逐行读
 *   每读一行，就用 Pattern/Matcher 抽取目标子串
 * </pre>
 * 拓展：复用 Pattern（编译一次，循环里反复 matcher() 即可），避免在循环里 compile。
 */
public class C05_UrlCrawl {
    public static void main(String[] args) {
        // 通过命令行参数传入 URL，否则使用一段内置示例文本以便离线运行
        if (args.length >= 1) {
            crawlFromUrl(args[0]);
        } else {
            crawlFromText();
        }
    }

    private static void crawlFromUrl(String urlStr) {
        Pattern p = Pattern.compile("[1-9]\\d{17}"); // 简单 18 位身份证
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Matcher m = p.matcher(line);
                    while (m.find()) {
                        System.out.println(m.group());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("读取 URL 失败：" + e.getMessage());
        }
    }

    private static void crawlFromText() {
        String text = "示例文本里有这些身份证号："
                + "41080119930228457X、"
                + "510801197609022309，"
                + "另有错误数据 0123 与 abc，应被忽略。";
        Pattern p = Pattern.compile("[1-9]\\d{16}[\\dXx]");
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
