package com.itheima._06_practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 练习 3：按年龄段分组 + 求各组平均年龄
 * <p>
 * 综合演示 groupingBy 嵌套 averagingInt 的用法。
 */
public class PracticeGroupActorDemo {
    public static void main(String[] args) {
        List<Actor> actors = Arrays.asList(
                new Actor("周润发", 67),
                new Actor("成龙", 70),
                new Actor("林青霞", 68),
                new Actor("张曼玉", 58),
                new Actor("甄子丹", 60),
                new Actor("李若彤", 47),
                new Actor("吴京", 49)
        );

        // 1. 按年龄段分组：50 以下 / 50~60 / 60 以上
        Map<String, List<Actor>> grouped = actors.stream()
                .collect(Collectors.groupingBy(a -> {
                    if (a.getAge() < 50) return "青壮";
                    if (a.getAge() < 60) return "中年";
                    return "资深";
                }));
        grouped.forEach((k, v) -> System.out.println(k + " => " + v));

        // 2. 各年龄段平均年龄
        Map<String, Double> avg = actors.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getAge() < 50 ? "青壮" : a.getAge() < 60 ? "中年" : "资深",
                        Collectors.averagingInt(Actor::getAge)));
        System.out.println("各组平均年龄：" + avg);
    }
}
