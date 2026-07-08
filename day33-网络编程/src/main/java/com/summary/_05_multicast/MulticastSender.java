package com.summary._05_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

/**
 * 知识点 5：UDP 组播
 *
 * 组播地址范围：224.0.0.0 ~ 239.255.255.255
 *  - 224.0.0.0 ~ 224.0.0.255：路由协议等系统保留
 *  - 239.0.0.0 ~ 239.255.255.255：本地管理组播（推荐自定义使用）
 *
 * 只有加入到该组的接收端才会收到消息。
 */
public class MulticastSender {
    public static void main(String[] args) throws IOException {
        MulticastSocket ms = new MulticastSocket();

        byte[] bytes = "组播：vip 用户专属推送".getBytes(StandardCharsets.UTF_8);
        InetAddress group = InetAddress.getByName("224.0.0.1");
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, group, 10000);

        ms.send(dp);
        System.out.println("组播已发送");
        ms.close();
    }
}
