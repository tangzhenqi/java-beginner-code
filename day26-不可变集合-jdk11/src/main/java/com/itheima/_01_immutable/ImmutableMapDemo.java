package com.itheima._01_immutable;

import java.util.Map;

/**
 * 不可变 Map：Map.of(K, V, K, V, ...)
 * <p>
 * 要点：
 * 1. 参数必须是「键, 值」成对出现。
 * 2. 键不能重复，否则抛 IllegalArgumentException。
 * 3. 最多支持 10 对键值（20 个参数），超过要用 {@link ImmutableMapEntriesDemo}。
 * 4. 键、值都不允许 null。
 */
public class ImmutableMapDemo {
    public static void main(String[] args) {
        Map<String, String> map = Map.of(
                "张三", "南京",
                "李四", "北京",
                "王五", "上海"
        );

        // 两种遍历
        for (String key : map.keySet()) {
            System.out.println(key + " = " + map.get(key));
        }
        map.forEach((k, v) -> System.out.println(k + " => " + v));

        // 键重复
        try {
            Map.of("张三", "南京", "张三", "北京");
        } catch (IllegalArgumentException e) {
            System.out.println("Map.of 键重复：" + e.getMessage());
        }

        // 不可写
        try {
            map.put("赵六", "广州");
        } catch (UnsupportedOperationException e) {
            System.out.println("不可变 Map 不支持 put");
        }
    }
}
