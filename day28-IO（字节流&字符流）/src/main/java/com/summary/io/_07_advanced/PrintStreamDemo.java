package com.summary.io._07_advanced;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 拓展：PrintStream。
 *
 * 特点：
 *   - 永远不会抛 IOException（内部吞掉，可用 checkError() 查询）
 *   - 自动 flush
 *   - println / printf 系列方法直接打印任意类型
 *
 * 用法：
 *   1) 标准输出就是 PrintStream（System.out）
 *   2) System.setOut(...) 可以重定向到任意位置 —— 比如日志文件
 */
public class PrintStreamDemo {

    public static void main(String[] args) throws FileNotFoundException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws FileNotFoundException {
        PrintStream original = System.out;
        try (PrintStream ps = new PrintStream(new FileOutputStream("data/print_log.txt"), true)) {
            System.setOut(ps);
            System.out.println("这行被重定向写到 data/print_log.txt");
            System.out.printf("格式化输出 pi=%.3f%n", Math.PI);
        } finally {
            System.setOut(original);
        }
        System.out.println("System.out 已恢复到控制台。可以查看 data/print_log.txt");
    }
}
