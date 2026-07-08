package com.itheima._01_immutable;

import java.util.Set;

/**
 * 不可变 Set：Set.of(...)
 * <p>
 * 要点：
 * 1. 元素必须唯一，传入重复元素会立即抛 IllegalArgumentException。
 * 2. 同样不允许 null。
 * 3. 迭代顺序不保证（与 HashSet 类似的语义）。
 */
public class ImmutableSetDemo {
    public static void main(String[] args) {
        Set<String> set = Set.of("张三", "李四", "王五", "赵六");
        for (String s : set) {
            System.out.println(s);
        }

        // 重复元素：编译期不会报错，运行期抛 IllegalArgumentException
        try {
            Set.of("张三", "张三");
        } catch (IllegalArgumentException e) {
            System.out.println("Set.of 不允许重复元素：" + e.getMessage());
        }

        // 不可写
        try {
            set.remove("张三");
        } catch (UnsupportedOperationException e) {
            System.out.println("不可变 Set 不支持 remove");
        }
    }
}
