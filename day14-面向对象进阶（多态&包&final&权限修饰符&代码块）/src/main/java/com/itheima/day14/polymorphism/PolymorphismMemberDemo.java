package com.itheima.day14.polymorphism;

/**
 * 多态调用成员的规则（重点！）：
 *
 *     成员变量：  编译看左边，运行也看左边
 *     成员方法：  编译看左边，运行看右边
 *
 * 原因：
 *     成员变量不存在重写的概念，子类即便定义了同名变量，
 *     也只是把父类的变量"遮蔽"了，对象里两份都存在。
 *
 *     成员方法存在方法重写，JVM 的虚方法表会把父类方法覆盖掉，
 *     所以最终运行的是子类（即 new 出来的那个对象）的方法。
 */
public class PolymorphismMemberDemo {
    public static void main(String[] args) {
        AnimalP a = new DogP();

        // 编译看左边：左边 AnimalP 中有 name，可以编译通过
        // 运行也看左边：实际取到的是 AnimalP 的 name = "动物"
        System.out.println("a.name = " + a.name);

        // 编译看左边：左边 AnimalP 中有 show 方法，可以编译通过
        // 运行看右边：实际执行的是 DogP 中重写的 show
        a.show();
    }
}

class AnimalP {
    String name = "动物";

    public void show() {
        System.out.println("AnimalP --- show 方法");
    }
}

class DogP extends AnimalP {
    String name = "狗"; // 同名变量是"遮蔽"，不是重写

    @Override
    public void show() {
        System.out.println("DogP --- show 方法（子类）");
    }
}
