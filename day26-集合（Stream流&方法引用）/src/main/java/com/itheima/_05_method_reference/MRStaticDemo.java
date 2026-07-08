package com.itheima._05_method_reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方法引用 1：引用静态方法
 * <p>
 * 格式： 类名::静态方法名
 * <p>
 * 使用前提（共 4 条，整个 _05 都适用）：
 * 1. 必须有「函数式接口」上下文（Function / Predicate / Consumer ...）
 * 2. 被引用的方法已经存在
 * 3. 被引用方法的形参 / 返回值需要与抽象方法保持一致
 * 4. 被引用方法的功能要满足当前需求
 */
public class MRStaticDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "1", "2", "3", "4", "5");

        // 等价于 .map(s -> Integer.parseInt(s))
        List<Integer> ints = list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(ints);

        // 也可以引用自己写的静态方法
        List<Integer> ints2 = list.stream()
                .map(StringOperation::parseInt)
                .collect(Collectors.toList());
        System.out.println(ints2);
    }
}
