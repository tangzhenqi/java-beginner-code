package com.itheima._01_immutable;

import java.util.List;

/**
 * 不可变 List：List.of(...)
 * <p>
 * 要点：
 * 1. JDK9 新增，返回的是 ImmutableCollections.ListN（包私有类）。
 * 2. 一旦创建，无法 add / remove / set，任何写操作抛 UnsupportedOperationException。
 * 3. 元素允许重复，但不允许 null。
 * 4. 适用场景：常量集合、方法返回值、防御性拷贝。
 */
public class ImmutableListDemo {
    public static void main(String[] args) {
        List<String> list = List.of("张三", "李四", "王五", "赵六");

        // 只能读
        System.out.println(list.get(0));
        System.out.println("size = " + list.size());
        for (String s : list) {
            System.out.println(s);
        }

        // 不能写：以下任何一句都会抛 UnsupportedOperationException
        try {
            list.add("钱七");
        } catch (UnsupportedOperationException e) {
            System.out.println("不可变集合不支持 add：" + e.getClass().getSimpleName());
        }

        // 不允许 null
        try {
            List.of("a", null, "b");
        } catch (NullPointerException e) {
            System.out.println("List.of 不允许 null 元素");
        }
    }
}
