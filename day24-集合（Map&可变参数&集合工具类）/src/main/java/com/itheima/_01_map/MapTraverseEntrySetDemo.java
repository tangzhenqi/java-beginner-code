package com.itheima._01_map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Map 的第二种遍历方式：键值对对象（entrySet）。
 *
 *   1. 调用 entrySet() 把所有 Entry 放进一个 Set
 *   2. 遍历 Set 拿到每一个 Map.Entry
 *   3. 通过 entry.getKey() / entry.getValue() 取键和值
 *
 * 相比 keySet：少一次 map.get 查找，效率更高，推荐写法。
 */
public class MapTraverseEntrySetDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("标枪选手", "马超");
        map.put("人物挂件", "明世隐");
        map.put("御龙骑士", "尹志平");

        Set<Map.Entry<String, String>> entries = map.entrySet();

        // 写法一：增强 for
        System.out.println("------ 增强 for ------");
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // 写法二：迭代器
        System.out.println("------ 迭代器 ------");
        Iterator<Map.Entry<String, String>> it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // 写法三：lambda
        System.out.println("------ lambda ------");
        entries.forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));
    }
}
