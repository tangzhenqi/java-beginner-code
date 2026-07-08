package com.summary._09_multi_thread;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MultiThreadClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10000);

        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("resources/clientdir/a.jpg"))) {
            OutputStream os = socket.getOutputStream();
            byte[] buf = new byte[8192];
            int len;
            while ((len = bis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            socket.shutdownOutput();
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        System.out.println(br.readLine());

        socket.close();
    }
}
