package com.summary._05_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class MulticastReceiver {
    public static void main(String[] args) throws IOException {
        MulticastSocket ms = new MulticastSocket(10000);

        // 加入组
        InetAddress group = InetAddress.getByName("224.0.0.1");
        ms.joinGroup(group);

        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        ms.receive(dp);

        String msg = new String(dp.getData(), 0, dp.getLength(), StandardCharsets.UTF_8);
        System.out.println("收到组播：" + msg);

        ms.leaveGroup(group);
        ms.close();
    }
}
