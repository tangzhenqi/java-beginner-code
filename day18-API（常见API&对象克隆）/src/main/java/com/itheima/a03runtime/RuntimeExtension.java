package com.itheima.a03runtime;

/**
 * 知识点三：Runtime —— 拓展
 * <p>
 * 1. addShutdownHook(Thread)
 *    JVM 关闭前会执行注册的钩子线程，常用于资源清理、日志落盘。
 *    无论是 System.exit、Runtime.exit、还是程序正常退出，都会触发；
 *    但 kill -9 / 断电 / JVM 崩溃不会触发。
 * <p>
 * 2. gc()
 *    向 JVM 建议执行垃圾回收（仅是建议，不保证立即执行）。
 * <p>
 * 3. Runtime.getRuntime().exec(cmd) 返回 Process 对象，
 *    可通过 Process 拿到子进程的输入/输出流，进行交互。
 */
public class RuntimeExtension {
    public static void main(String[] args) {
        // 1. 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                System.out.println("[shutdown hook] JVM 即将退出，正在清理资源…")
        ));

        // 2. 主动建议 GC
        System.out.println("调用 gc 前剩余内存: " + Runtime.getRuntime().freeMemory() / 1024 + " KB");
        Runtime.getRuntime().gc();
        System.out.println("调用 gc 后剩余内存: " + Runtime.getRuntime().freeMemory() / 1024 + " KB");

        System.out.println("main 结束（钩子会在此之后触发）");
    }
}
