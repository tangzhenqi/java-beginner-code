package com.itheima._03_stream_intermediate;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 中间方法：{@code map(Function<T, R>)} —— 类型 / 内容转换
 * <p>
 * map 把流中每个元素「一对一」地变成另一个对象，常见用法：
 * - 字符串切割取字段
 * - 对象 -> 属性值
 * - 字符串 -> 整型
 */
public class StreamMapDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-15", "周芷若-14", "赵敏-13", "张强-20");

        // String -> 年龄(int) 然后输出
        list.stream()
                .map(s -> s.split("-")[1])      // String -> String
                .map(Integer::parseInt)         // String -> Integer（方法引用，详见 _05）
                .forEach(age -> System.out.println("年龄 = " + age));
    }
}
