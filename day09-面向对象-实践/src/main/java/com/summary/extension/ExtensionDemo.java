package com.summary.extension;

import com.summary.crud.Student;
import com.summary.crud.StudentArrayUtil;

import java.util.Arrays;

/**
 * 拓展：JDK 已经提供了一些"数组工具"，没必要每次都手写循环。
 *
 *   1. System.arraycopy(src, srcPos, dest, destPos, length)
 *      —— 把 src 数组从 srcPos 开始的 length 个元素拷到 dest[destPos] 开始的位置。
 *
 *   2. Arrays.copyOf(arr, newLength)
 *      —— 返回一个长度为 newLength 的新数组，自动把老数据拷过来。
 *
 *   3. Arrays.toString(arr)
 *      —— 一行就能打印数组内容（前提是元素的 toString 重写过）。
 *
 *   4. "紧凑删除"：删除某个位置后，把后面的元素整体前移一格。
 */
public class ExtensionDemo {
    public static void main(String[] args) {
        Student[] arr = new Student[3];
        arr[0] = new Student(1, "zhangsan", 23);
        arr[1] = new Student(2, "lisi", 24);
        arr[2] = new Student(3, "wangwu", 25);

        // 1) Arrays.copyOf：一行扩容
        Student[] expanded = Arrays.copyOf(arr, arr.length + 1);
        expanded[3] = new Student(4, "zhaoliu", 26);
        System.out.println("--- 扩容后 ---");
        StudentArrayUtil.printArr(expanded);

        // 2) 紧凑删除：删 id=2 这一项
        shiftDelete(arr, 2);
        System.out.println("--- 紧凑删除 id=2 ---");
        StudentArrayUtil.printArr(arr);
    }

    /**
     * 紧凑删除：找到目标后，把后面所有元素整体左移一格，末尾置 null。
     */
    public static void shiftDelete(Student[] arr, int id) {
        int index = StudentArrayUtil.getIndex(arr, id);
        if (index < 0) {
            return;
        }
        // 把 [index+1, end) 拷到 [index, end-1)
        System.arraycopy(arr, index + 1, arr, index, arr.length - index - 1);
        arr[arr.length - 1] = null;
    }
}
