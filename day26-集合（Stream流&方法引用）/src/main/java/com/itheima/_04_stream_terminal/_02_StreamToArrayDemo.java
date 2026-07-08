package com.itheima._04_stream_terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 终结方法：toArray
 * <p>
 * - 无参版：返回 Object[]
 * - 有参版 toArray(IntFunction)：传入「构造指定类型数组的函数」，返回该类型数组
 *   例如：toArray(String[]::new) 等价于 size -> new String[size]
 */
public class _02_StreamToArrayDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏");

        // 1. 默认 Object[]
        Object[] arr1 = list.stream().toArray();
        System.out.println(Arrays.toString(arr1));

        // 2. 指定具体类型：方法引用 String[]::new
        String[] arr2 = list.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(arr2));
    }
}
