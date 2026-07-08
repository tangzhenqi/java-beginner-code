package com.itheima._04_stream_terminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 拓展：Collectors.groupingBy / partitioningBy
 * <p>
 * - groupingBy(keyMapper)：按某个字段分组，得到 Map<K, List<T>>
 * - groupingBy(keyMapper, downstream)：分组后再做二次汇聚，如 counting / averagingInt
 * - partitioningBy(predicate)：按布尔条件分两组（true/false）
 */
public class _06_StreamCollectGroupingByDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,
                "张无忌-男-15", "周芷若-女-14", "赵敏-女-13",
                "张强-男-20", "张三丰-男-100");

        // 1. 按性别分组
        Map<String, List<String>> grouped = list.stream()
                .collect(Collectors.groupingBy(s -> s.split("-")[1]));
        grouped.forEach((k, v) -> System.out.println(k + " => " + v));

        // 2. 分组 + 计数
        Map<String, Long> count = list.stream()
                .collect(Collectors.groupingBy(
                        s -> s.split("-")[1],
                        Collectors.counting()));
        System.out.println("每性别人数：" + count);

        // 3. 分组 + 平均年龄
        Map<String, Double> avg = list.stream()
                .collect(Collectors.groupingBy(
                        s -> s.split("-")[1],
                        Collectors.averagingInt(s -> Integer.parseInt(s.split("-")[2]))));
        System.out.println("每性别平均年龄：" + avg);

        // 4. 二分：是否成年
        Map<Boolean, List<String>> part = list.stream()
                .collect(Collectors.partitioningBy(
                        s -> Integer.parseInt(s.split("-")[2]) >= 18));
        System.out.println("成年/未成年：" + part);
    }
}
