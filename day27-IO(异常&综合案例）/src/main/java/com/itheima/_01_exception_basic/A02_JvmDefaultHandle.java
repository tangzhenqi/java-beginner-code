package com.itheima._01_exception_basic;

/**
 * 知识点 2：JVM 默认处理异常的方式
 *
 *   1. 将异常名称、原因、栈轨迹打印到控制台 (System.err 红色字)
 *   2. 终止当前线程：异常之后的代码不再执行
 *
 * 演示：把下面 main 中的 try-catch 去掉运行，对比观察"是否还会打印最后一行"。
 */
public class A02_JvmDefaultHandle {
    public static void main(String[] args) {
        System.out.println("step 1");
        try {
            System.out.println(10 / 0); // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("被捕获后程序可继续运行：" + e.getMessage());
        }
        System.out.println("step 2 —— 如果没有 try-catch,这一行不会被执行");
    }
}
