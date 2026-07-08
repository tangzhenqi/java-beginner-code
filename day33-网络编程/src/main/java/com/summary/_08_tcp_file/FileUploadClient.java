package com.summary._08_tcp_file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 知识点 8：TCP 文件上传（综合 IO 流 + TCP）
 *
 * 注意：
 *  - 用缓冲流提升效率
 *  - 文件读完后必须 shutdownOutput()，否则服务端 read 永远阻塞
 *  - 读取回写用 readLine() 时，服务端必须 newLine()/flush() 配合
 */
public class FileUploadClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10000);

        // 替换为本地任意文件路径
        String localFile = "resources/clientdir/a.jpg";

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(localFile))) {
            OutputStream os = socket.getOutputStream();
            byte[] buf = new byte[8192];
            int len;
            while ((len = bis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            socket.shutdownOutput(); // 关键：告诉服务端文件发完了
        }

        // 等待服务端回写
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        String resp = br.readLine();
        System.out.println("服务端反馈：" + resp);

        socket.close();
    }
}
