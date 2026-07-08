package com.itheima._03_exception_throw;

import java.text.ParseException;

/**
 * 知识点 7：throws vs throw
 *
 *   throws：写在 "方法声明" 处，向调用者"声明"该方法可能抛出哪些异常。
 *           对编译时异常是强制的；对运行时异常可写可不写。
 *           多个异常用逗号分隔。
 *
 *   throw ：写在 "方法体内" 处，"实际"抛出一个异常对象，方法立即结束。
 *           常用于参数校验。
 *
 * 类比：throws 是"事先告知"，throw 是"当场动手"。
 */
public class C01_ThrowsVsThrow {
    public static void main(String[] args) {
        try {
            System.out.println(getMax(null));
        } catch (NullPointerException e) {
            System.out.println("捕获: " + e.getMessage());
        }

        try {
            System.out.println(getMax(new int[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获: " + e.getMessage());
        }

        System.out.println("max = " + getMax(new int[]{3, 1, 4, 1, 5, 9, 2, 6}));

        // ===== throws 的"必须"场景：编译时异常 =====
        // parseConfig 声明了 throws ParseException（编译时异常），
        // 调用者"必须"处理：要么 try-catch，要么自己也往上 throws，否则编译不过。
        try {
            System.out.println("端口 = " + parsePort("8080"));
            System.out.println("端口 = " + parsePort("abc")); // 触发抛出
        } catch (ParseException e) {
            System.out.println("捕获编译时异常: " + e.getMessage());
        }
    }

    
    /** 运行时异常可以不写 throws，但写了能让调用者更清晰。 */
    public static int getMax(int[] arr) {
        if (arr == null) {
            throw new NullPointerException("数组不能为 null");
        }
        if (arr.length == 0) {
            throw new ArrayIndexOutOfBoundsException("数组不能为空");
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    /**
     * 这里同时出现了 throws 和 throw：
     *   throws ParseException —— 写在方法声明上，"事先告知"调用者本方法可能抛出它。
     *   throw  new ParseException(...) —— 写在方法体内，"当场动手"真正抛出。
     * 因为 ParseException 是编译时异常，throws 是强制的（去掉就编译报错）。
     */
    public static int parsePort(String text) throws ParseException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new ParseException("非法端口: " + text, 0);
        }
    }
}
