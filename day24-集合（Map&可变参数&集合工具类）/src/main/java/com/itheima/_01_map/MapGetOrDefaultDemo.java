package com.itheima._01_map;

import java.util.HashMap;
import java.util.Map;

/**
 * 拓展：getOrDefault / putIfAbsent。
 *
 * getOrDefault(key, defaultValue)：键存在返回对应值，不存在返回默认值。
 *   适合做计数等场景，避免每次手写 containsKey 判断。
 *
 * putIfAbsent(key, value)：键不存在才放入。
 */
public class MapGetOrDefaultDemo {
    public static void main(String[] args) {
        // 用 getOrDefault 简化字符出现次数统计
        String s = "aababcabcdabcde";
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 不存在 -> 默认 0；存在 -> 取出已有次数，+1 后放回
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        System.out.println("字符次数：" + count);

        // putIfAbsent
        Map<String, String> map = new HashMap<>();
        map.put("a", "old");
        map.putIfAbsent("a", "new");   // 已存在 -> 不覆盖
        map.putIfAbsent("b", "new");   // 不存在 -> 添加
        System.out.println("putIfAbsent 后：" + map);
    }
}
