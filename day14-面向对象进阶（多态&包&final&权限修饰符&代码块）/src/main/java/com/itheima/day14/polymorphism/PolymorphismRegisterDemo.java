package com.itheima.day14.polymorphism;

/**
 * 多态的"价值"演示：
 *     一个 register(Person p) 方法 —— 既能接收 Student，
 *     也能接收 Teacher，还能接收 Administrator。
 *
 * 多态的三大前提：
 *     1. 有继承（或实现）关系
 *     2. 有方法重写
 *     3. 父类引用 指向 子类对象       Person p = new Student();
 */
public class PolymorphismRegisterDemo {
    public static void main(String[] args) {
        Person s = new Student("张三", 18);
        Person t = new Teacher("王建国", 30);
        Person admin = new Administrator("管理员", 35);

        register(s);
        register(t);
        register(admin);

        // 拓展：多态数组 —— 父类引用数组可以装下任意子类对象，统一处理
        Person[] people = { s, t, admin };
        System.out.println("---- 用多态数组遍历 ----");
        for (Person p : people) {
            p.show();
        }
    }

    /**
     * 形参写成父类，方法的"扩展性"就被打开了：
     * 以后再增加 Person 的任何子类，本方法都不用动。
     */
    public static void register(Person p) {
        p.show();
    }
}
