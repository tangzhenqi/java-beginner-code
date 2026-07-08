package com.itheima._04_stream_terminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 终结方法：collect(Collector) —— 收集到 List / Set
 * <p>
 * - Collectors.toList()：收集到 ArrayList
 * - Collectors.toSet() ：收集到 HashSet（自动去重）
 * <p>
 * 注意：JDK16+ 还有更短的 Stream#toList()（不可变 List）。
 */
public class _03_StreamCollectListSetDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,
                "张无忌-男-15", "周芷若-女-14", "赵敏-女-13",
                "张强-男-20", "张三丰-男-100");

        // 收集所有男性
        List<String> males = list.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toList());
        System.out.println("男性：" + males);

        // 收集所有性别（去重）
        Set<String> genders = list.stream()
                .map(s -> s.split("-")[1])
                .collect(Collectors.toSet());
        System.out.println("性别集合：" + genders);
    }
}
