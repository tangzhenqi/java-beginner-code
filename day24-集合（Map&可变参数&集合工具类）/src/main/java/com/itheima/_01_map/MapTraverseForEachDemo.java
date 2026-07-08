package com.itheima._01_map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Map 的第三种遍历方式：lambda（forEach）。
 *
 * 底层就是用 entrySet 拿到键和值后调用 BiConsumer.accept(k, v)。
 *
 * 三种写法演示：匿名内部类 -> 完整 lambda -> 省略类型的 lambda。
 */
public class MapTraverseForEachDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("鲁迅",   "这句话是我说的");
        map.put("曹操",   "不可能绝对不可能");
        map.put("刘备",   "接着奏乐接着舞");
        map.put("柯镇恶", "看我眼色行事");

        // 写法一：匿名内部类
        System.out.println("------ 匿名内部类 ------");
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String key, String value) {
                System.out.println(key + " = " + value);
            }
        });

        // 写法二：lambda 写明参数类型
        System.out.println("------ lambda 完整 ------");
        map.forEach((String key, String value) -> {
            System.out.println(key + " = " + value);
        });

        // 写法三：lambda 简化（推荐）
        System.out.println("------ lambda 简化 ------");
        map.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
