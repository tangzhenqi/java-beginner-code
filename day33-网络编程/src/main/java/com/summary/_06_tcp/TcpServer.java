package com.summary._06_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        System.out.println("服务端启动，等待连接...");

        Socket socket = ss.accept();
        System.out.println("客户端 " + socket.getInetAddress().getHostAddress() + " 已连接");

        // 用字符流，避免中文乱码
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

        int b;
        while ((b = br.read()) != -1) {
            System.out.print((char) b);
        }
        System.out.println();

        socket.close();
        ss.close();
    }
}
