package com.itheima._01_exception_basic;

/**
 * 知识点 1：异常体系结构
 *
 *   Throwable
 *     ├─ Error          —— 严重错误，程序无法处理（如 OutOfMemoryError、StackOverflowError）
 *     └─ Exception
 *          ├─ RuntimeException  —— 运行时异常（编译期不强制处理）
 *          │     ├─ NullPointerException
 *          │     ├─ ArrayIndexOutOfBoundsException
 *          │     ├─ ClassCastException
 *          │     ├─ NumberFormatException
 *          │     └─ ArithmeticException ...
 *          └─ 其它 Exception   —— 编译时异常（编译期必须处理，否则报错）
 *                ├─ IOException
 *                ├─ ParseException
 *                ├─ SQLException ...
 *
 * 区分核心：
 *   - 运行时异常：通常由"程序员的逻辑错误"导致，应当通过修正代码避免，而不是被动 try-catch。
 *   - 编译时异常：通常是"系统/外部环境"可能出错的提醒，必须显式处理 (try-catch 或 throws)。
 */
public class A01_ExceptionHierarchy {
    public static void main(String[] args) {
        // 运行时异常示例：编译能通过，运行时才报错
        int[] arr = {1, 2, 3};
        try {
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到运行时异常: " + e.getClass().getSimpleName());
        }

        // 编译时异常示例（解开注释观察"红色波浪线"，必须处理才能编译）：
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Date d = sdf.parse("2026-05-26"); // ParseException 是编译时异常
    }
}
