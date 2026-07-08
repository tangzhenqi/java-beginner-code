package com.itheima._01_map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Map 的第一种遍历方式：键找值（keySet）。
 *
 *   1. 调用 keySet() 把所有键放进一个 Set
 *   2. 遍历这个 Set
 *   3. 用键调用 map.get(key) 拿到值
 *
 * 三种写法：增强 for、迭代器、lambda forEach。
 */
public class MapTraverseKeySetDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("尹志平", "小龙女");
        map.put("郭靖",   "穆念慈");
        map.put("欧阳克", "黄蓉");

        Set<String> keys = map.keySet();

        // 写法一：增强 for
        System.out.println("------ 增强 for ------");
        for (String key : keys) {
            System.out.println(key + " = " + map.get(key));
        }

        // 写法二：迭代器
        System.out.println("------ 迭代器 ------");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key + " = " + map.get(key));
        }

        // 写法三：lambda
        System.out.println("------ lambda ------");
        keys.forEach(key -> System.out.println(key + " = " + map.get(key)));
    }
}
