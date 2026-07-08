package com.summary._12_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * 拓展 2：NIO 单线程 Reactor 回声服务器
 *
 * BIO 模型：
 *   - 一个连接一个线程
 *   - accept、read 都是阻塞操作
 *   - 连接数受线程数限制
 *
 * NIO 模型：
 *   - Channel（通道）+ Buffer（缓冲区）+ Selector（多路复用器）
 *   - 一个线程可以管理成千上万个连接
 *   - 内部由 OS 的 epoll/kqueue/IOCP 支撑
 *
 * 流程：
 *   1. 打开 Selector
 *   2. ServerSocketChannel 绑定端口、设为非阻塞、注册 OP_ACCEPT
 *   3. select() 阻塞等就绪事件
 *   4. 根据事件类型分别处理 accept / read / write
 *
 * 注意：这是教学版，未处理粘包/拆包、未做退出机制。
 */
public class NioEchoServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(20000));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("NIO 回声服务端启动，端口 20000");

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (true) {
            selector.select();
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();

                if (key.isAcceptable()) {
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("新连接：" + client.getRemoteAddress());

                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    buf.clear();
                    int n = client.read(buf);
                    if (n == -1) {
                        client.close();
                        continue;
                    }
                    buf.flip();
                    String msg = StandardCharsets.UTF_8.decode(buf).toString();
                    System.out.print("收到：" + msg);

                    ByteBuffer resp = ByteBuffer.wrap(
                            ("echo> " + msg).getBytes(StandardCharsets.UTF_8));
                    client.write(resp);
                }
            }
        }
    }
}
