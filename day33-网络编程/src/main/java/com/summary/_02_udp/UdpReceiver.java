package com.summary._02_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * UDP 接收端
 *
 * 注意：
 *  - 接收端必须绑定具体端口，且要与发送端一致
 *  - receive 方法是阻塞方法，会一直等待数据到来
 */
public class UdpReceiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086);

        byte[] buf = new byte[1024 * 64]; // UDP 单包最大约 64KB
        DatagramPacket dp = new DatagramPacket(buf, buf.length);

        System.out.println("等待接收...");
        ds.receive(dp); // 阻塞

        String msg = new String(dp.getData(), 0, dp.getLength(), StandardCharsets.UTF_8);
        System.out.println("收到来自 " + dp.getAddress().getHostAddress()
                + ":" + dp.getPort() + " 的消息：" + msg);

        ds.close();
    }
}
