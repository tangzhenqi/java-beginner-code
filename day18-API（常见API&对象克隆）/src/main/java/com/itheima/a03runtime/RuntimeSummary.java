package com.itheima.a03runtime;

import java.io.IOException;

/**
 * 知识点三：Runtime 类
 * <p>
 * 归纳：
 *   Runtime 表示"当前虚拟机的运行环境"。
 *   每个 Java 应用只有一个 Runtime 实例（单例），通过 Runtime.getRuntime() 获取。
 * <p>
 * 常用方法：
 *   getRuntime()              获取当前运行环境对象（静态）
 *   exit(int)                 停止 JVM（实例方法，区别于 System.exit）
 *   availableProcessors()     CPU 线程数
 *   maxMemory()               JVM 能从系统中获取的最大内存（单位 byte）
 *   totalMemory()             JVM 已经向系统申请的总内存（单位 byte）
 *   freeMemory()              JVM 当前剩余内存（单位 byte）
 *   exec(String command)      启动一个操作系统进程执行命令
 * <p>
 * 注：底层其实就是 System.exit -> Runtime.getRuntime().exit。
 */
public class RuntimeSummary {
    public static void main(String[] args) throws IOException {
        Runtime r = Runtime.getRuntime();

        System.out.println("CPU 线程数: " + r.availableProcessors());
        System.out.println("最大可用内存: " + r.maxMemory()   / 1024 / 1024 + " MB");
        System.out.println("已申请内存:   " + r.totalMemory() / 1024 / 1024 + " MB");
        System.out.println("剩余内存:     " + r.freeMemory()  / 1024 / 1024 + " MB");

        // exec 演示（跨平台示例）：
        //   Windows: Runtime.getRuntime().exec("notepad");        打开记事本
        //   Mac:     Runtime.getRuntime().exec("open -a Calculator"); 打开计算器
        //   注意：危险命令（如 shutdown）会真的执行操作系统行为，谨慎使用！

        // exit 演示（注释掉避免直接停止）：
        // Runtime.getRuntime().exit(0);
    }
}
