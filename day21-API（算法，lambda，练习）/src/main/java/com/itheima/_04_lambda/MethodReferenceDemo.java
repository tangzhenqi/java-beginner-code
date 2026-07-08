package com.itheima._04_lambda;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 拓展：方法引用 (Method Reference)
 *
 * 当 Lambda 表达式只是"调用某个已有方法"时，可以用 :: 进一步简写。
 *
 * 四种形式：
 *   1. 类名::静态方法     Integer::parseInt
 *   2. 对象::实例方法     System.out::println
 *   3. 类名::实例方法     String::length     （第一个参数作为方法的调用者）
 *   4. 类名::new          ArrayList::new     （构造方法引用）
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        // 1. 类名 :: 静态方法
        Function<String, Integer> parser = Integer::parseInt;
        System.out.println(parser.apply("123") + 1);

        // 2. 对象 :: 实例方法
        Arrays.asList("a", "b", "c").forEach(System.out::println);

        // 3. 类名 :: 实例方法  → 编译器把第一个参数当成调用者
        Function<String, Integer> len = String::length;
        System.out.println("hello".length() + " == " + len.apply("hello"));

        // 4. 构造方法引用
        Supplier<StringBuilder> builderFactory = StringBuilder::new;
        StringBuilder sb = builderFactory.get();
        sb.append("Hello").append(", ").append("MR");
        System.out.println(sb);
    }
}
