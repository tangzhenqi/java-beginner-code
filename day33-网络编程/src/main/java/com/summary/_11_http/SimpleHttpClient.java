package com.summary._11_http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 拓展 1：使用 URL + HttpURLConnection 调用 HTTP 接口
 *
 * HTTP 是应用层协议，基于 TCP。
 * Java 内置 URL/URLConnection 已经把"建立 TCP 连接 + 发送 HTTP 报文"封装好。
 * 真实项目中更常用 OkHttp / Apache HttpClient / Spring RestTemplate / WebClient。
 *
 * 此 Demo 演示发起 GET 请求并打印响应内容。
 */
public class SimpleHttpClient {

    public static void main(String[] args) throws Exception {
        String urlStr = "https://www.baidu.com";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (compatible; day33-summary/1.0)");

        int code = conn.getResponseCode();
        System.out.println("响应状态码：" + code);

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null && count++ < 10) {
                System.out.println(line);
            }
            System.out.println("... (只展示前 10 行)");
        }

        conn.disconnect();
    }
}
