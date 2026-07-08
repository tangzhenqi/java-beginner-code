package com.itheima.a02system;

/**
 * 知识点二：System 类 —— 拓展 API
 * <p>
 *   System.nanoTime()       高精度时间（纳秒），仅用于计算时间差，不代表当前时刻。
 *   System.lineSeparator()  跨平台换行符：Windows 是 \r\n，Linux/Mac 是 \n。
 *   System.getProperty(k)   读取 JVM 系统属性，例如 "java.version"、"user.dir"、"os.name"。
 *   System.getenv(k)        读取操作系统环境变量。
 *   System.getProperties()  所有系统属性。
 */
public class SystemExtension {
    public static void main(String[] args) {
        // 1. nanoTime：用于更精确的耗时计算
        long s = System.nanoTime();
        for (int i = 0; i < 100_000; i++) Math.sqrt(i);
        long e = System.nanoTime();
        System.out.println("耗时 " + (e - s) + " ns = " + (e - s) / 1_000_000.0 + " ms");

        // 2. 跨平台换行符
        System.out.print("第一行" + System.lineSeparator() + "第二行" + System.lineSeparator());

        // 3. 系统属性 —— 常用 key
        System.out.println("Java 版本: " + System.getProperty("java.version"));
        System.out.println("操作系统:  " + System.getProperty("os.name"));
        System.out.println("用户主目录: " + System.getProperty("user.home"));
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));
        System.out.println("文件分隔符: " + System.getProperty("file.separator"));

        // 4. 环境变量（取决于操作系统）
        System.out.println("PATH 长度: " + (System.getenv("PATH") == null ? 0 : System.getenv("PATH").length()));
    }
}
