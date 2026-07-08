package com.summary.stringbuilder;

/**
 * 案例：把一个 int[] 拼成 "[1, 2, 3]" 这样的字符串。
 *
 * 重点：循环拼接 -> 用 StringBuilder。
 * 控制"最后一个元素不带逗号" -> 用 i == arr.length - 1 判断。
 *
 * 拓展：JDK 自带 Arrays.toString(arr) 直接搞定，连分隔符都帮你处理好。
 */
public class ArrayToStringDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(arrToString(arr));   // [1, 2, 3, 4, 5]
        System.out.println(java.util.Arrays.toString(arr));  // 自带的实现

        System.out.println(arr);  // [I@xxxxxxxx
    }

    public static String arrToString(int[] arr) {
        if (arr == null) return "null";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}


/*
    * ============================================================
    * 问题：直接 System.out.println(arr) 会怎么样？
    * ============================================================
    *
    * 会打印数组的“默认 toString 结果”，而不是元素内容。
    * 输出形如：
    *     [I@1540e19d
    *
    * 格式说明：
    *   [   表示这是一个数组
    *   I   表示元素类型是 int
    *       （JVM 类型描述符：I=int, J=long, D=double,
    *         Z=boolean, [L...; = 对象数组）
    *   @   后面是该对象的 hashCode 的十六进制
    *
    * 原因：
    *   int[] 没有重写 Object.toString()，
    *   而 Object.toString() 的默认实现就是：
    *     getClass().getName() + "@" + Integer.toHexString(hashCode())
    *
    * 对比：
    *   System.out.println(arr)                       -> [I@1540e19d
    *   System.out.println(Arrays.toString(arr))      -> [1, 2, 3, 4, 5]
    *   System.out.println(arrToString(arr))          -> [1, 2, 3, 4, 5]
    *
    * 特例：
    *   char[] 是个例外 —— println(char[]) 有专门的重载，
    *   会直接打印字符序列（如 {'h','i'} 输出 hi），而不是地址。
    * ============================================================
    */