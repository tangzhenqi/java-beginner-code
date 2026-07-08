package com.summary.day15.abstractclass;

/**
 * 抽象类的 6 大注意事项（结合代码逐条验证）：
 *
 * 1. abstract 不能修饰构造方法、私有方法、静态方法、final 方法；
 *    因为以上方法都不能被重写，与 abstract 的设计目的冲突。
 * 2. 抽象类不能实例化，但可以有构造方法（给子类 super 用）。
 * 3. 抽象类的子类要么重写所有抽象方法，要么自己也是抽象类。
 * 4. 抽象类可以继承抽象类，此时父类的抽象方法可以不重写。
 * 5. 抽象类可以拥有普通方法、成员变量、静态成员、代码块等等。
 * 6. 抽象类同样可以用多态：父类引用 指向 子类对象。
 */
public class AbstractRulesDemo {

    public static void main(String[] args) {
        // Animal a = new Animal();   // 编译错误：抽象类不能 new
        // Cat   c = new Cat();       // 编译错误：Cat 仍是抽象类
        Animal a = new Kitten("小花", 2);   // 多态
        a.eat();
        a.sleep();
        System.out.println(a.getName() + " " + a.getAge());
    }
}

abstract class Animal {
    private String name;
    private int age;

    public Animal() { }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    /** 普通方法：所有动物通用。 */
    public void sleep() {
        System.out.println(name + " 在睡觉");
    }

    /** 抽象方法：吃什么由子类决定。 */
    public abstract void eat();
}

/** 规则 4：抽象类继承抽象类，无需重写父类抽象方法。 */
abstract class Cat extends Animal {

    public Cat() { }

    public Cat(String name, int age) {
        super(name, age);
    }

    /** 中间层补一个抽象方法，更加细化的子类去实现。 */
    public abstract void catchMouse();
}

/** 规则 3：具体子类必须重写所有未实现的抽象方法。 */
class Kitten extends Cat {

    public Kitten(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " 吃猫粮");
    }

    @Override
    public void catchMouse() {
        System.out.println(getName() + " 还在练习抓老鼠");
    }
}
