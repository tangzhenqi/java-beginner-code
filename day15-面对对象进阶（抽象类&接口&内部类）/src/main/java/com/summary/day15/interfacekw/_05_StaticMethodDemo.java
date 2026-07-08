package com.summary.day15.interfacekw;

/**
 * 接口静态方法（JDK 8 引入）：
 *  格式：public static 返回值 方法名(...) { ... }
 *  注意：
 *      1. 静态方法只能通过"接口名"调用，不能通过实现类名 / 对象名调用。
 *      2. public 可省略，static 不能省略。
 *      3. 实现类中"同名同参的 static 方法"不是重写，只是实现类自身的静态方法，互不干扰。
 *  典型用途：
 *      作为该接口相关的工厂方法 / 工具方法，避免再额外建一个 XxxUtils 类。
 */
public class _05_StaticMethodDemo {

    public static void main(String[] args) {
        // 用接口名直接调用静态方法
        int sum = MathOps.add(3, 5);
        System.out.println("3 + 5 = " + sum);

        // 工厂方法：通过接口给出一个默认实现
        MathOps ops = MathOps.defaultOps();
        System.out.println("默认实现 * 2 = " + ops.times(2));

        // 实现类自己的同名 static 方法，与接口的 add 没有任何继承/重写关系
        MathOpsImpl.add(1, 1);
    }
}

interface MathOps {

    int times(int x);

    static int add(int a, int b) {
        return a + b;
    }

    static MathOps defaultOps() {
        return x -> x * 10;     // 用 lambda 顺便预告"函数式接口"的概念
    }
}

class MathOpsImpl implements MathOps {

    @Override
    public int times(int x) { return x * 3; }

    /** 注意：这不是重写，只是 MathOpsImpl 自己的静态方法。 */
    public static int add(int a, int b) {
        System.out.println("实现类自身的 static add 被调用");
        return a + b;
    }
}
