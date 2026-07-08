package com.summary._06_tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 知识点 6：TCP 基础通信
 *
 * 客户端：
 *   - new Socket(ip, port) 时即发起三次握手，连接失败直接抛异常
 *   - 通过 Socket.getOutputStream() 得到写出通道
 *
 * 服务端：
 *   - ServerSocket(port) 监听
 *   - accept() 是阻塞方法，等待客户端连接，返回 Socket
 *
 * 中文乱码避坑：
 *   - 字节流 read() 一次只读一个字节，中文 (UTF-8 通常 3 字节) 会被切散
 *   - 解决方案：使用字符流（InputStreamReader/OutputStreamWriter）
 *     或者使用 read(byte[]) 一次性读完再 new String(...)
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10000);

        OutputStream os = socket.getOutputStream();
        os.write("你好,TCP!".getBytes(StandardCharsets.UTF_8));
        os.write("现在我给你传几个字符".getBytes(StandardCharsets.UTF_8));

        socket.close();
    }
}
