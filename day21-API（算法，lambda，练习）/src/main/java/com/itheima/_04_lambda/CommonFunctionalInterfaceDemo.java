package com.itheima._04_lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 拓展：JDK8 内置的 4 大常用函数式接口（java.util.function 包）
 *
 *   Supplier<T>           T get()              "生产者"，无入参出 T
 *   Consumer<T>           void accept(T t)     "消费者"，吃 T 不吐
 *   Predicate<T>          boolean test(T t)    "判断"，吃 T 吐 boolean
 *   Function<T,R>         R apply(T t)         "转换"，吃 T 吐 R
 *
 * 此外还有 BiXxx 系列（吃两个参数）和针对基本类型的特化版本
 * （IntPredicate / ToIntFunction / IntUnaryOperator 等）避免装箱开销。
 */
public class CommonFunctionalInterfaceDemo {
    public static void main(String[] args) {
        // Supplier：生产一个新的字符串
        Supplier<String> supplier = () -> "Hello-" + System.currentTimeMillis();
        System.out.println(supplier.get());

        // Consumer：打印
        Consumer<String> consumer = System.out::println;
        consumer.accept("Consumer 消费啦");
        // andThen 链式消费
        consumer.andThen(s -> System.out.println(">> " + s)).accept("链式");

        // Predicate：判断字符串是否为空
        Predicate<String> isEmpty = s -> s == null || s.isEmpty();
        System.out.println(isEmpty.test(""));        // true
        System.out.println(isEmpty.negate().test("a")); // true

        // Function：String -> 长度
        Function<String, Integer> length = String::length;
        // andThen：再把长度转成 "长度=N"
        Function<String, String> describe = length.andThen(n -> "长度=" + n);
        System.out.println(describe.apply("Lambda"));

        // BiFunction：两个 int 相加
        BiFunction<Integer, Integer, Integer> add = Integer::sum;
        System.out.println("3 + 4 = " + add.apply(3, 4));
    }
}
