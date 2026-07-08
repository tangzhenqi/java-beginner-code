package com.itheima._01_map;

import java.util.HashMap;
import java.util.Map;

/**
 * 拓展：JDK8 的 merge 方法。
 *
 * merge(key, value, remappingFunction)：
 *   - 如果键不存在 或 原值为 null，直接放入 value
 *   - 否则把 "原值" 和 "传入 value" 交给 remappingFunction 计算后再放入
 *
 * 这是写计数 / 累加器最优雅的写法。
 */
public class MapMergeDemo {
    public static void main(String[] args) {
        String s = "aababcabcdabcde";
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 不存在 -> 放 1；存在 -> oldValue + 1
            count.merge(c, 1, Integer::sum);
        }
        System.out.println("merge 计数结果：" + count);

        // 用 merge 把多个 List<购物车> 的金额累加（小演示）
        Map<String, Double> bill = new HashMap<>();
        bill.merge("苹果", 3.5, Double::sum);
        bill.merge("苹果", 2.5, Double::sum);
        bill.merge("牛奶", 6.0, Double::sum);
        System.out.println("merge 累加金额：" + bill);
    }
}
