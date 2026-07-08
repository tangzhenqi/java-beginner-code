package com.itheima._02_exception_handle;

/**
 * 知识点 3：try-catch 基本用法
 *
 *   try {
 *       可能抛出异常的代码;
 *   } catch (异常类型 e) {
 *       异常处理代码;
 *   }
 *
 * "灵魂四问"：
 *   Q1：try 中没有出现异常 → catch 不执行，try-catch 之后的代码正常执行。
 *   Q2：try 中出现多种异常 → 写多个 catch；父类 catch 必须放最后；JDK7+ 可用 | 合并捕获。
 *   Q3：try 中出现异常但没被任何 catch 匹配 → 等价于没 try，依然交给 JVM 处理。
 *   Q4：try 出现异常的那一行之后的代码 → 直接跳过，转入对应 catch；
 *                                       catch 执行完后，会继续执行 try-catch 之后的代码。
 */
public class B01_TryCatchBasic {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        try {
            System.out.println(arr[10]);          // 第一处异常出现，下面 try 内代码被跳过
            System.out.println("try 内: 此行不会输出");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到数组越界: " + e.getMessage());
        }

        System.out.println("try-catch 之后的代码正常执行");
    }
}
