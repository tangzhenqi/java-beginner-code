package com.itheima._04_stream_terminal;

import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * 拓展：Collectors.joining —— 字符串拼接
 * <p>
 * 三个重载：
 *   - joining()                           直接拼接
 *   - joining(分隔符)                     用分隔符
 *   - joining(分隔符, 前缀, 后缀)         加前后缀
 */
public class _05_StreamCollectJoiningDemo {
    public static void main(String[] args) {
        String r1 = Stream.of("张三", "李四", "王五")
                .collect(Collectors.joining());
        System.out.println(r1);              // 张三李四王五

        String r2 = Stream.of("张三", "李四", "王五")
                .collect(Collectors.joining(", "));
        System.out.println(r2);              // 张三, 李四, 王五

        String r3 = Stream.of("张三", "李四", "王五")
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(r3);              // [张三, 李四, 王五]
    }
}
