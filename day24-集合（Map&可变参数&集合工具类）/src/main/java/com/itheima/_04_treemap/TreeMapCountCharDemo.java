package com.itheima._04_treemap;

import java.util.StringJoiner;
import java.util.TreeMap;

/**
 * 字符统计 + 按字典顺序输出。
 *
 * 需求：字符串 "aababcabcdabcde"
 *   - 统计每个字符出现的次数
 *   - 按字符升序输出，形如 a(5)b(4)c(3)d(2)e(1)
 *
 * 选择：要求结果排序 -> 用 TreeMap；如果不排序则 HashMap 更快。
 */
public class TreeMapCountCharDemo {
    public static void main(String[] args) {
        String s = "aababcabcdabcde";

        TreeMap<Character, Integer> tm = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 简化写法：用 merge（_01_map.MapMergeDemo 中已演示）
            tm.merge(c, 1, Integer::sum);
        }
        System.out.println("统计结果：" + tm);

        // 按指定格式拼接：a(5)b(4)c(3)d(2)e(1)
        StringJoiner sj = new StringJoiner("");
        tm.forEach((k, v) -> sj.add(k + "(" + v + ")"));
        System.out.println("格式化  ：" + sj);
    }
}
