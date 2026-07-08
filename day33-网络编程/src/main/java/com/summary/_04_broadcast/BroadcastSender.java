package com.summary._04_broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * 知识点 4：UDP 广播
 *
 * 广播地址：255.255.255.255（本网段广播）
 * 同一网段内所有绑定该端口的接收端都会收到。
 *
 * 三种 UDP 通信模式：
 *   - 单播 unicast：一对一（默认）
 *   - 广播 broadcast：一对所有同网段
 *   - 组播 multicast：一对一组（224.0.0.0 ~ 239.255.255.255）
 */
public class BroadcastSender {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        byte[] bytes = "广播：今晚 8 点全员开会！".getBytes(StandardCharsets.UTF_8);
        InetAddress broadcast = InetAddress.getByName("255.255.255.255");

        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, broadcast, 9999);
        ds.send(dp);
        System.out.println("广播已发送");

        ds.close();
    }
}
