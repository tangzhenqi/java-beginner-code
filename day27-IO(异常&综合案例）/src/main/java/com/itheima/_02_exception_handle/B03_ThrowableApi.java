package com.itheima._02_exception_handle;

/**
 * 知识点 5：Throwable 的常用 API
 *
 *   public String getMessage()       仅返回异常信息（不含异常类型名）
 *   public String toString()         异常类型名 + ": " + getMessage()
 *   public void printStackTrace()    打印完整栈轨迹到 System.err (红色字)，不会终止程序
 *
 * 经验：日志中尽量打印 printStackTrace 或使用日志框架；仅打 getMessage 会丢失定位信息。
 */
public class B03_ThrowableApi {
    public static void main(String[] args) {
        try {
            int[] arr = new int[3];
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("getMessage : " + e.getMessage());
            System.out.println("toString   : " + e);
            System.out.println("printStackTrace ↓");
            e.printStackTrace();
        }

        System.out.println("程序继续运行：printStackTrace 不会中断流程");
    }
}
