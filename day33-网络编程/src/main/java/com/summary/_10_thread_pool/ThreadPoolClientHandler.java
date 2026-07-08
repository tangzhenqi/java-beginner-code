package com.summary._10_thread_pool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ThreadPoolClientHandler implements Runnable {

    private final Socket socket;

    public ThreadPoolClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String name = UUID.randomUUID().toString().replace("-", "") + ".jpg";
        try (BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream bos = new BufferedOutputStream(
                     new FileOutputStream("resources/serverdir/" + name))) {

            byte[] buf = new byte[8192];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            bw.write("线程 " + Thread.currentThread().getName() + " 处理完成：" + name);
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }
}
