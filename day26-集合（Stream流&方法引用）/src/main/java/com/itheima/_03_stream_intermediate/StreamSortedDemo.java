package com.itheima._03_stream_intermediate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 拓展中间方法：sorted()
 * <p>
 * - 无参版：按元素的自然顺序（要求实现 Comparable）排序
 * - 有参版：sorted(Comparator)：自定义排序规则
 * <p>
 * 易错点：sorted 是「有状态」的中间操作，会缓存全部元素，无限流上使用会卡死。
 */
public class StreamSortedDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 3, 1, 4, 1, 5, 9, 2, 6);

        System.out.println("--- 自然排序 ---");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("--- 降序 ---");
        list.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        System.out.println("--- 按字符串长度排 ---");
        ArrayList<String> words = new ArrayList<>();
        Collections.addAll(words, "kiwi", "apple", "fig", "banana");
        words.stream()
                .sorted(Comparator.comparingInt(String::length))
                .forEach(System.out::println);
    }
}
