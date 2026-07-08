package com.summary._04_broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class BroadcastReceiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(9999);
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);

        while (true) {
            ds.receive(dp);
            String msg = new String(dp.getData(), 0, dp.getLength(), StandardCharsets.UTF_8);
            System.out.println("收到广播：" + msg);
        }
    }
}
