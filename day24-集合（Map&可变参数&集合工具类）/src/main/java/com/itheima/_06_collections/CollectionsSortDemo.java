package com.itheima._06_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Collections.sort 排序。
 *
 *   sort(List<T> list)                       使用元素的 Comparable 默认规则
 *   sort(List<T> list, Comparator<T> c)      使用传入的比较器
 *
 *   - Integer / String 本身实现了 Comparable
 *   - 自定义对象要么实现 Comparable，要么传 Comparator
 */
public class CollectionsSortDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10, 1, 2, 4, 8, 5, 9, 6, 7, 3);

        // 1. 默认升序
        Collections.sort(list);
        System.out.println("默认升序：" + list);

        // 2. 传入 Comparator 实现降序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println("匿名内部类降序：" + list);

        // 3. lambda 等价写法
        Collections.sort(list, (o1, o2) -> o2 - o1);
        System.out.println("lambda 降序   ：" + list);
    }
}
