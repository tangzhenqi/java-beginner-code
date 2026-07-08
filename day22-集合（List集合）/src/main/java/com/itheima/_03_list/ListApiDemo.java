package com.itheima._03_list;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点八：List 接口独有的"按索引"操作（Collection 不具备）。
 *
 *  void add(int index, E element)   在索引位置插入元素（原元素及之后整体右移）
 *  E    remove(int index)           删除索引位置的元素，返回被删元素
 *  E    set(int index, E element)   修改索引位置的元素，返回旧值
 *  E    get(int index)              读取索引位置的元素
 *
 * 因为 List 是"有序、可重复、有索引"的，所以这些 API 才有意义。
 */
public class ListApiDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        list.add(1, "QQQ");                 // [aaa, QQQ, bbb, ccc]
        System.out.println("插入后: " + list);

        String removed = list.remove(0);    // 删除并返回 aaa
        System.out.println("删除的元素: " + removed + ", 列表: " + list);

        String old = list.set(0, "ZZZ");    // 把 0 号位 QQQ 改成 ZZZ
        System.out.println("被替换的旧值: " + old + ", 列表: " + list);

        System.out.println("get(1): " + list.get(1));
    }
}
