package com.itheima._05_varargs;

import java.util.Arrays;
import java.util.List;

/**
 * 拓展：JDK 中常见的可变参数 API。
 *
 *   - Arrays.asList(T... a)      数组/枚举值 转 List（注意：固定长度，不能 add/remove）
 *   - Collections.addAll         批量添加
 *   - String.format / printf     格式化用的也是可变参数
 *   - JDK9+ 还有 List.of / Set.of / Map.of（本项目 JDK8 不演示）
 *
 * 它们都是站在使用者角度对可变参数的最佳实践。
 */
public class VarArgsAsListDemo {
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 2, 3, 4);
        System.out.println("Arrays.asList -> " + l1);

        // printf 也是可变参数
        System.out.printf("姓名=%s 年龄=%d 性别=%s%n", "张三", 18, "男");

        // 注意：Arrays.asList 返回的是固定长度，add 会抛异常
        try {
            l1.add(5);
        } catch (UnsupportedOperationException e) {
            System.out.println("Arrays.asList 不能 add: " + e.getClass().getSimpleName());
        }
    }
}
