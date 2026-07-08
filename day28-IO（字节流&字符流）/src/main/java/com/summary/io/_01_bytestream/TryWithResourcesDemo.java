package com.summary.io._01_bytestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 异常处理三种风格演示。
 *
 *   1) try-catch-finally：传统写法，资源需要在 finally 中 null 判 + close
 *   2) JDK7  try-with-resources：在 try() 里声明实现 AutoCloseable 的资源，自动 close
 *   3) JDK9  try-with-resources：可以在 try() 中引用 try 外部已声明的 final / effectively final 资源
 */
public class TryWithResourcesDemo {

    private static final String SRC = "data/src.bin";
    private static final String DEST = "data/copy_try.bin";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException {
        ensureSourceExists();

        traditionalTryCatchFinally();
        jdk7Style();
        jdk9Style();

        System.out.println("三种风格的拷贝均完成");
    }

    /** 风格 1：传统 try-catch-finally */
    private static void traditionalTryCatchFinally() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(SRC);
            fos = new FileOutputStream(DEST);
            byte[] buf = new byte[8192];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(fos);
            closeQuietly(fis);
        }
    }

    /** 风格 2：JDK7 try-with-resources */
    private static void jdk7Style() {
        try (FileInputStream fis = new FileInputStream(SRC);
             FileOutputStream fos = new FileOutputStream(DEST)) {
            byte[] buf = new byte[8192];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 风格 3：JDK9 可以在 try() 里引用外部的 effectively final 资源 */
    private static void jdk9Style() throws IOException {
        FileInputStream fis = new FileInputStream(SRC);
        FileOutputStream fos = new FileOutputStream(DEST);
        try (fis; fos) {
            byte[] buf = new byte[8192];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeQuietly(AutoCloseable c) {
        if (c != null) {
            try {
                c.close();
            } catch (Exception ignore) {
            }
        }
    }

    private static void ensureSourceExists() throws IOException {
        java.io.File src = new java.io.File(SRC);
        if (!src.exists()) {
            try (FileOutputStream fos = new FileOutputStream(SRC)) {
                fos.write("for try-with-resources demo".getBytes());
            }
        }
    }
}
