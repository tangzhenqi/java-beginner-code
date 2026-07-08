package com.summary._02_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * 知识点 2：UDP 单次发送
 *
 * 三步走：
 *   1. 创建 DatagramSocket（快递公司）
 *   2. 创建 DatagramPacket（包裹：内容 + 长度 + 目的 IP + 目的端口）
 *   3. send 发送
 *   4. close 释放资源
 */
public class UdpSender {
    public static void main(String[] args) throws IOException {
        // 1. 创建发送端 Socket（端口随机）
        DatagramSocket ds = new DatagramSocket();

        // 2. 打包数据
        byte[] bytes = "Hello UDP！".getBytes(StandardCharsets.UTF_8);
        InetAddress target = InetAddress.getByName("127.0.0.1");
        int port = 10086;
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, target, port);

        // 3. 发送
        ds.send(dp);
        System.out.println("已发送：" + new String(bytes, StandardCharsets.UTF_8));

        // 4. 释放
        ds.close();
    }
}
