package com.summary._07_tcp_echo;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpEchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        Socket socket = ss.accept();

        // 读
        InputStreamReader isr = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
        int b;
        while ((b = isr.read()) != -1) {
            System.out.print((char) b);
        }
        System.out.println();

        // 回写
        OutputStream os = socket.getOutputStream();
        os.write("到底有多开心？".getBytes(StandardCharsets.UTF_8));

        socket.close();
        ss.close();
    }
}
