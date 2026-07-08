package com.summary;

/**
 * 知识点6：数组的两类常见异常
 *
 *   一、ArrayIndexOutOfBoundsException —— 索引越界异常
 *       原因：访问了不存在的索引（小于 0 或大于等于 length）。
 *       避免：循环条件用 i < arr.length；不要硬编码索引。
 *
 *   二、NullPointerException —— 空指针异常
 *       原因：数组变量为 null 时还想访问元素或 length。
 *       避免：使用前确保数组已经被 new 出来；必要时做 null 判断。
 *
 *   小贴士：异常打印的堆栈中，"at 类名.方法名(文件:行号)" 会直接定位到代码位置，
 *   先看第一行的异常类型，再看第一处自己代码的行号，绝大多数 bug 一眼可见。
 */
public class ArrayExceptionDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        // 1. 索引越界
        try {
            System.out.println(arr[10]); // 抛 ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获索引越界：" + e.getMessage());
        }

        // 2. 空指针
        int[] nullArr = null;
        try {
            System.out.println(nullArr.length); // 抛 NullPointerException
        } catch (NullPointerException e) {
            System.out.println("捕获空指针：数组没有指向任何对象");
        }
    }
}
