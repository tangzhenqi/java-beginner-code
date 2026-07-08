package com.itheima._03_stream_intermediate;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 中间方法：limit(n) / skip(n)
 * <p>
 * - limit(n)：截取前 n 个元素
 * - skip(n)：跳过前 n 个元素
 * 常用组合：skip(2).limit(3) 等价于「分页」第二页（每页 3 条）。
 */
public class StreamLimitSkipDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c", "d", "e", "f", "g");

        System.out.println("--- limit(3)：取前 3 ---");
        list.stream().limit(3).forEach(System.out::println);

        System.out.println("--- skip(2)：跳过前 2 ---");
        list.stream().skip(2).forEach(System.out::println);

        System.out.println("--- skip(2).limit(3)：第 3~5 个 ---");
        list.stream().skip(2).limit(3).forEach(System.out::println);
    }
}
