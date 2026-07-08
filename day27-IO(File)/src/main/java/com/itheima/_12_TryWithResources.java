package com.itheima;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 知识点 22（拓展，JDK7+）：try-with-resources 自动关闭资源
 *
 *   try (资源声明) { ... } catch(...) { ... }
 *
 *   - 资源类型必须实现 AutoCloseable（包括 Closeable）。
 *   - 多资源用 ";" 分隔，"先声明后关闭，倒序关闭"。
 *   - JDK 9 起，资源变量可以是 try 之前声明的 effectively final 变量，
 *     无需再在 try 头里 re-assign。
 *
 * 对比传统写法：try-finally 中手动 close
 *   - 易遗漏 close
 *   - close 本身可能抛异常，与 try 内的异常"互相吞噬"，丢失栈轨迹
 *   - try-with-resources 由编译器生成"抑制异常 (Suppressed)"链，定位更友好
 */
public class _12_TryWithResources {
    public static void main(String[] args) throws IOException {
        String path = "target/playground/twr.txt";
        new java.io.File("target/playground").mkdirs();

        // 单资源
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("hello try-with-resources\n");
        } // ← 这里自动调用 fw.close()

        // 多资源：分号分隔；关闭顺序与声明顺序相反
        try (FileWriter a = new FileWriter(path, true);
             FileWriter b = new FileWriter(path, true)) {
            a.write("first append\n");
            b.write("second append\n");
        }

        System.out.println("写入完成，文件路径: " + path);
    }
}
