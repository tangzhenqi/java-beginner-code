package com.summary;

/**
 * 知识点1：HelloWorld 与 main 方法
 *
 * 程序格式：
 *   public class 类名 {
 *       public static void main(String[] args) {
 *           // 程序入口
 *       }
 *   }
 *
 * 细节：
 *   - 源文件后缀必须是 .java
 *   - 文件名必须与 public 修饰的类名严格一致（区分大小写）
 *   - main 方法是程序唯一的主入口，由 JVM 调用
 *
 * 编译运行流程：
 *   javac HelloWorldDemo.java   // 生成 .class 字节码
 *   java HelloWorldDemo          // 运行字节码（不加 .class）
 */
public class HelloWorldDemo {
    public static void main(String[] args) {
        System.out.println("HelloWorld");
    }
}
