package com.summary._07_tcp_echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 知识点 7：TCP 双向通信（echo）
 *
 * 关键点：socket.shutdownOutput()
 *   - TCP 流没有 EOF 结束标记，对端 read() 会一直阻塞
 *   - 调用 shutdownOutput() 关闭"输出"半端，对端读到 -1
 *   - 注意：close() 是关闭整个 socket，而 shutdownOutput 只关闭写
 */
public class TcpEchoClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10000);

        // 发送
        OutputStream os = socket.getOutputStream();
        os.write("见到你很高兴！".getBytes(StandardCharsets.UTF_8));
        socket.shutdownOutput(); // 告诉服务端：我说完了

        // 接收回写
        InputStreamReader isr = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
        int b;
        while ((b = isr.read()) != -1) {
            System.out.print((char) b);
        }
        System.out.println();

        socket.close();
    }
}
