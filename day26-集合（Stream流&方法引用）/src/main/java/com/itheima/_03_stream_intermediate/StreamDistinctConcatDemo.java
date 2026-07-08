package com.itheima._03_stream_intermediate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * 中间方法：distinct() / Stream.concat(a, b)
 * <p>
 * - distinct()：去重，依赖元素的 hashCode / equals
 *   ⇒ 对自定义对象去重必须重写这两个方法。
 * - Stream.concat(a, b)：合并两条流为一条；要求两条流的元素类型一致。
 */
public class StreamDistinctConcatDemo {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "张无忌", "张无忌", "张无忌", "张强", "张三丰");

        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "周芷若", "赵敏");

        System.out.println("--- distinct 去重 ---");
        list1.stream().distinct().forEach(System.out::println);

        System.out.println("--- concat 合并 ---");
        Stream.concat(list1.stream(), list2.stream())
                .distinct()                       // 合并后再去重
                .forEach(System.out::println);
    }
}
