package com.itheima._06_practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 练习 1：男女演员合并 -> 过滤 -> 收集
 * <p>
 * 题目：有男女两组演员，要求合并后保留年龄在 30~50 的演员，按年龄升序，
 *      最终结果收集到 List。
 */
public class PracticeFilterCollectDemo {
    public static void main(String[] args) {
        ArrayList<String> man = new ArrayList<>();
        man.addAll(Arrays.asList("周润发,55", "成龙,60", "李连杰,52", "甄子丹,45", "周星驰,50"));

        ArrayList<String> woman = new ArrayList<>();
        woman.addAll(Arrays.asList("林青霞,40", "张曼玉,45", "王祖贤,42", "关之琳,38", "李若彤,28"));

        List<Actor> result = Stream.concat(man.stream(), woman.stream())
                .map(s -> {
                    String[] arr = s.split(",");
                    return new Actor(arr[0], Integer.parseInt(arr[1]));
                })
                .filter(a -> a.getAge() >= 30 && a.getAge() <= 50)
                .sorted((a, b) -> a.getAge() - b.getAge())
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }
}
