package com.itheima._02_stream_create;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * 获取 Stream 的方式 1：单列集合 {@code Collection.stream()}
 * <p>
 * 所有实现 Collection 接口的类（List/Set/Queue ...）都有默认方法 stream()，
 * 返回一条「流水线」。Stream 是 JDK8 加在 Collection 上的 default 方法。
 */
public class StreamFromCollectionDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c", "d", "e");

        // 链式：拿流 -> 遍历
        list.stream().forEach(s -> System.out.println(s));

        // Set 同理
        HashSet<Integer> set = new HashSet<>();
        Collections.addAll(set, 1, 2, 3);
        Stream<Integer> stream = set.stream();
        stream.forEach(System.out::println);
    }
}
