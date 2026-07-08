package com.itheima._06_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Collections.max / min。
 *
 *   max/min(Collection<T> coll)                默认自然顺序
 *   max/min(Collection<T> coll, Comparator c)  按比较器
 */
public class CollectionsMaxMinDemo {
    public static void main(String[] args) {
        // 1. 默认自然顺序
        ArrayList<Integer> nums = new ArrayList<>();
        Collections.addAll(nums, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("max = " + Collections.max(nums));
        System.out.println("min = " + Collections.min(nums));

        // 2. 指定规则：找最长的字符串
        ArrayList<String> words = new ArrayList<>();
        Collections.addAll(words, "a", "aa", "aaa", "aaaa");

        String longest = Collections.max(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println("最长字符串：" + longest);

        // lambda 写法
        String shortest = Collections.min(words, (a, b) -> a.length() - b.length());
        System.out.println("最短字符串：" + shortest);
    }
}
