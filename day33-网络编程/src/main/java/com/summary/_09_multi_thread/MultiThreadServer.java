package com.summary._09_multi_thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 知识点 9：多线程 TCP 服务器
 *
 * 单线程问题：
 *   一次只能服务一个客户端，第二个连接必须等第一个处理完才能进入。
 *
 * 解决：每来一个连接，开一条线程去处理。
 *
 * 缺点（下一节解决）：
 *   - 高并发时疯狂创建线程，OOM
 *   - 线程创建/销毁开销大
 */
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        System.out.println("多线程服务端启动...");

        while (true) {
            Socket socket = ss.accept();
            System.out.println("新连接：" + socket.getInetAddress().getHostAddress());
            new Thread(new ClientHandler(socket)).start();
        }
    }
}
