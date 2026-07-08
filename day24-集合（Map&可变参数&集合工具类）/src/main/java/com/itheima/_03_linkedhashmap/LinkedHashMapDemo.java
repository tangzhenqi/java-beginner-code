package com.itheima._03_linkedhashmap;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * LinkedHashMap：
 *   - 由键决定特点：有序、不重复、无索引
 *   - 有序：存取顺序一致（按插入顺序遍历）
 *   - 底层：在 HashMap 的基础上多维护了一个双向链表来记录插入顺序
 *
 * 适用场景：希望按插入顺序遍历的字典 / 缓存（也可改为访问顺序，做 LRU）。
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // HashMap：无序
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("c", 789);
        hm.put("b", 456);
        hm.put("a", 123);
        System.out.println("HashMap       -> " + hm);

        // LinkedHashMap：保持插入顺序
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("c", 789);
        lhm.put("b", 456);
        lhm.put("a", 123);
        // 同键覆盖，不会改变位置
        lhm.put("a", 111);
        System.out.println("LinkedHashMap -> " + lhm);

        // 拓展：accessOrder=true 时，按访问顺序排（被 get/put 的会移到末尾），可用于实现 LRU
        LinkedHashMap<String, Integer> lru = new LinkedHashMap<>(16, 0.75f, true);
        lru.put("a", 1);
        lru.put("b", 2);
        lru.put("c", 3);
        lru.get("a");                       // 访问 a -> a 移到末尾
        System.out.println("访问顺序 LRU   -> " + lru);
    }
}
