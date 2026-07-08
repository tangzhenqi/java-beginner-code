package com.itheima._03_stream_intermediate;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 中间方法：{@code filter(Predicate)} —— 过滤
 * <p>
 * 通用要点（适用于所有中间方法）：
 * 1. 返回新的 Stream，原 Stream 不能再使用（会抛 IllegalStateException）。
 * 2. 不修改源集合 / 源数组。
 * 3. 是惰性求值：没有终结方法不会真正执行。
 */
public class StreamFilterDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰");

        // 留下张开头且长度为 3 的名字
        list.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::println);

        // 源集合不会被改变
        System.out.println("原集合仍为：" + list);
    }
}
