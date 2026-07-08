package com.itheima._01_map;

import java.util.HashMap;
import java.util.Map;

/**
 * Map 集合的基本方法。
 *
 * Map 是双列集合的顶层接口，特点：
 *   - 键不可重复，值可重复
 *   - 每个键值对都是一个 Entry 对象
 *   - 键和值是一一对应的
 *
 * 常用方法：
 *   V put(K key, V value)              添加 / 覆盖元素，返回被覆盖的旧值（无则 null）
 *   V remove(Object key)               按键删除，返回被删除的值
 *   void clear()                       清空集合
 *   boolean containsKey(Object key)    是否包含指定键
 *   boolean containsValue(Object val)  是否包含指定值
 *   boolean isEmpty()                  是否为空
 *   int size()                         键值对个数
 *   V get(Object key)                  根据键取值，不存在返回 null
 */
public class MapBasicDemo {
    public static void main(String[] args) {
        Map<String, String> m = new HashMap<>();

        // put：键不存在 -> 添加，返回 null
        System.out.println("put 新键 返回：" + m.put("郭靖", "黄蓉"));
        m.put("韦小宝", "沐剑屏");
        m.put("尹志平", "小龙女");

        // put：键已存在 -> 覆盖，返回旧值
        System.out.println("put 覆盖 返回：" + m.put("韦小宝", "双儿"));

        // 其他常用方法
        System.out.println("containsKey(郭靖)   = " + m.containsKey("郭靖"));
        System.out.println("containsValue(双儿)= " + m.containsValue("双儿"));
        System.out.println("size               = " + m.size());
        System.out.println("isEmpty            = " + m.isEmpty());
        System.out.println("get(郭靖)           = " + m.get("郭靖"));

        // remove 返回被删除的值
        System.out.println("remove(郭靖)       返回：" + m.remove("郭靖"));

        System.out.println("最终集合：" + m);

        // clear 清空
        m.clear();
        System.out.println("clear 后 isEmpty = " + m.isEmpty());
    }
}
