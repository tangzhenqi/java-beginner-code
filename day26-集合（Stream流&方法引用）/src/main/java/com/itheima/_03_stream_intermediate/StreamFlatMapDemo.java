package com.itheima._03_stream_intermediate;

import java.util.Arrays;
import java.util.List;

/**
 * 拓展中间方法：flatMap —— 「扁平化」
 * <p>
 * map  : T -> R         （一对一）
 * flatMap : T -> Stream<R>（一对多，再合并成一条流）
 * <p>
 * 典型场景：把「集合的集合」摊平成单层集合。
 */
public class StreamFlatMapDemo {
    public static void main(String[] args) {
        List<List<String>> nested = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d", "e"),
                Arrays.asList("f")
        );

        // 错误用法：map 得到 Stream<Stream<String>>
        // nested.stream().map(List::stream).forEach(System.out::println);

        // 正确：flatMap 摊平
        nested.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);

        System.out.println("--- 把句子拆成单词 ---");
        List<String> sentences = Arrays.asList("hello world", "foo bar baz");
        sentences.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .forEach(System.out::println);
    }
}
