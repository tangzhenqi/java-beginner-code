package com.itheima._04_lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Lambda 在排序中的应用 —— 与 day21 的算法主题呼应。
 *
 * Comparator 是函数式接口（compare(T, T) 是唯一抽象方法），可以直接用 Lambda 简写。
 *
 * 重点：自定义比较规则三步走：
 *   o1 - o2  → 升序
 *   o2 - o1  → 降序
 *   多关键字 → 先比关键字 A，再比关键字 B（链式 thenComparing 也可以）
 */
public class LambdaSortDemo {
    public static void main(String[] args) {
        Integer[] nums = {5, 2, 8, 1, 9, 3};

        // 升序
        Arrays.sort(nums, (o1, o2) -> o1 - o2);
        System.out.println("升序: " + Arrays.toString(nums));

        // 降序
        Arrays.sort(nums, (o1, o2) -> o2 - o1);
        System.out.println("降序: " + Arrays.toString(nums));

        // 字符串数组：先按长度升序，长度相同按字典序升序
        String[] words = {"banana", "fig", "apple", "kiwi", "pear", "ant"};
        Arrays.sort(words, (a, b) -> {
            int diff = a.length() - b.length();
            return diff != 0 ? diff : a.compareTo(b);
        });
        System.out.println("字符串混合排序: " + Arrays.toString(words));

        // 链式：thenComparing
        Arrays.sort(words, Comparator
                .comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder()));
        System.out.println("链式比较器: " + Arrays.toString(words));
    }
}
