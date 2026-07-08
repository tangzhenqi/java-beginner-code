package com.itheima._02_stream_create;

import java.util.HashMap;

/**
 * 获取 Stream 的方式 2：双列集合 Map
 * <p>
 * Map 没有 stream() 方法（因为它不是 Collection），需要先转单列：
 * - map.keySet().stream()       只关心键
 * - map.values().stream()       只关心值
 * - map.entrySet().stream()     需要键和值（最常用）
 */
public class StreamFromMapDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("aaa", 111);
        hm.put("bbb", 222);
        hm.put("ccc", 333);
        hm.put("ddd", 444);

        System.out.println("--- 1. keySet 流 ---");
        hm.keySet().stream().forEach(k -> System.out.println(k));

        System.out.println("--- 2. values 流 ---");
        hm.values().stream().forEach(v -> System.out.println(v));

        System.out.println("--- 3. entrySet 流（推荐）---");
        hm.entrySet().stream().forEach(e ->
                System.out.println(e.getKey() + "=" + e.getValue()));
    }
}
