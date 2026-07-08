package com.itheima._05_method_reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 方法引用 4：类名引用「成员方法」（最容易混淆的一种）
 * <p>
 * 格式：类名::成员方法名
 * <p>
 * 与「对象::成员方法」不同——这里没有具体对象。规则：
 *   - 抽象方法的「第一个参数」会被当作方法的调用者；
 *   - 抽象方法的「剩余参数」依次作为成员方法的参数；
 *   - 返回值一致。
 * <p>
 * 限制：因为「调用者」是流中的元素，所以只能引用流中元素所属类的成员方法。
 * 例如流是 {@code Stream<String>}，就只能 {@code String::xxx}。
 */
public class MRClassInstanceDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "aaa", "bbb", "ccc");

        // 等价于 .map(s -> s.toUpperCase())
        // 抽象方法 R apply(T t)
        //   T 是流元素 String，作为 toUpperCase 的调用者
        ArrayList<String> upper = list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(upper);
    }
}
