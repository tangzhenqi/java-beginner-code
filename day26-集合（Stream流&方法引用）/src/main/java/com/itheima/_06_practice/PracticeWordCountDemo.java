package com.itheima._06_practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 练习 2：单词计数 —— Stream + flatMap + groupingBy(counting)
 * <p>
 * 输入若干句子，输出每个单词出现的次数。
 * 经典「MapReduce」入门题。
 */
public class PracticeWordCountDemo {
    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
                "the quick brown fox",
                "jumps over the lazy dog",
                "the fox is quick"
        );

        Map<String, Long> wordCount = sentences.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        wordCount.forEach((k, v) -> System.out.println(k + " => " + v));
    }
}
