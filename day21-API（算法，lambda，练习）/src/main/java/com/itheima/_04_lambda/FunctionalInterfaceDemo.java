package com.itheima._04_lambda;

/**
 * 函数式接口 (Functional Interface)
 *
 * 定义：有且仅有一个抽象方法的接口。
 *
 * 特点：
 *   1. 可以使用 @FunctionalInterface 注解显式约束（编译期检查）；
 *   2. Lambda 表达式的目标类型必须是函数式接口；
 *   3. 接口里可以包含 default、static、private（JDK9+）方法，它们不计入"抽象方法"。
 */
public class FunctionalInterfaceDemo {

    @FunctionalInterface
    interface Greeting {
        void sayHi(String name);

        // default / static 方法不影响"只有一个抽象方法"的判定
        default void shout(String name) {
            sayHi(name.toUpperCase());
        }

        static Greeting english() {
            return n -> System.out.println("Hello, " + n + "!");
        }
    }

    public static void main(String[] args) {
        // 1) 传统匿名内部类
        Greeting g1 = new Greeting() {
            @Override
            public void sayHi(String name) {
                System.out.println("你好, " + name);
            }
        };
        g1.sayHi("世界");

        // 2) Lambda 表达式
        Greeting g2 = name -> System.out.println("Hi, " + name);
        g2.sayHi("Java");
        g2.shout("Java"); // 调用 default 方法

        // 3) 静态工厂返回的函数式接口实例
        Greeting.english().sayHi("Lambda");
    }
}
