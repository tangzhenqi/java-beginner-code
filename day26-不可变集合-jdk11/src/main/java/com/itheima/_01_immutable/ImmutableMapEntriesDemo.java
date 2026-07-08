package com.itheima._01_immutable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 当键值对超过 10 个，使用 Map.ofEntries(Entry...)。
 * <p>
 * 配合 Map.entry(k, v) 构造 Entry，再把整个 entrySet 数组打散传入。
 * 实战中常见写法：先用一个普通 HashMap 收集数据，最后 ofEntries 包装成只读视图。
 */
public class ImmutableMapEntriesDemo {
    public static void main(String[] args) {
        // 1. 普通 Map 收集数据
        HashMap<String, String> hm = new HashMap<>();
        hm.put("张三", "南京");
        hm.put("李四", "北京");
        hm.put("王五", "上海");
        hm.put("赵六", "广州");
        hm.put("孙七", "深圳");
        hm.put("周八", "杭州");
        hm.put("吴九", "宁波");
        hm.put("郑十", "苏州");
        hm.put("刘一", "无锡");
        hm.put("陈二", "嘉兴");
        hm.put("aaa", "111");

        // 2. 把 entrySet 转成 Entry 数组，然后用 ofEntries 打包
        Set<Map.Entry<String, String>> entries = hm.entrySet();
        Map.Entry<String, String>[] arr = entries.toArray(new Map.Entry[0]);
        Map<String, String> immutable = Map.ofEntries(arr);

        immutable.forEach((k, v) -> System.out.println(k + " -> " + v));

        // 3. 链式更紧凑的写法：使用 Stream（拓展，详见 _04_stream_terminal）
        Map<String, String> immutable2 = Map.ofEntries(
                Map.entry("a", "1"),
                Map.entry("b", "2"),
                Map.entry("c", "3")
        );
        System.out.println(immutable2);
    }
}
