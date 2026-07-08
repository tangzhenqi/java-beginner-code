package com.itheima._04_stream_terminal;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 终结方法：forEach / count
 * <p>
 * - forEach(Consumer)：遍历，没有返回值
 * - count()：统计元素个数，返回 long
 * <p>
 * 调用任何终结方法后，流就「关闭」，再次使用会抛 IllegalStateException。
 */
public class _01_StreamForEachCountDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强");

        list.stream().forEach(s -> System.out.println(s));

        long count = list.stream().filter(s -> s.startsWith("张")).count();
        System.out.println("张开头的人数：" + count);
    }
}
