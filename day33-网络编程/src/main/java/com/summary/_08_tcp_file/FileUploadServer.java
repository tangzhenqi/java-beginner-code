package com.summary._08_tcp_file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 服务端：保存到本地，并用 UUID 命名以避免同名覆盖
 *
 * 重名问题：
 *   - 多次上传同名文件会被覆盖
 *   - 用 UUID / 时间戳 / md5 生成唯一文件名即可
 */
public class FileUploadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        System.out.println("服务端启动，等待上传...");
        Socket socket = ss.accept();

        String name = UUID.randomUUID().toString().replace("-", "") + ".jpg";
        try (BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream bos = new BufferedOutputStream(
                     new FileOutputStream("resources/serverdir/" + name))) {
            byte[] buf = new byte[8192];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
        }
        System.out.println("文件已保存为：" + name);

        // 回写
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        bw.write("上传成功");
        bw.newLine(); // 配合客户端的 readLine()
        bw.flush();

        socket.close();
        ss.close();
    }
}
