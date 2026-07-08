package com.summary._03_udp_chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 知识点 3：UDP 多次发送（聊天）
 *
 * 输入 886 退出。接收端使用死循环不断接收。
 */
public class UdpChatSender {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        InetAddress target = InetAddress.getByName("127.0.0.1");

        while (true) {
            System.out.print("请输入要说的话（886 退出）：");
            String line = sc.nextLine();
            if ("886".equals(line)) {
                break;
            }
            byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, target, 10086);
            ds.send(dp);
        }

        ds.close();
    }
}
