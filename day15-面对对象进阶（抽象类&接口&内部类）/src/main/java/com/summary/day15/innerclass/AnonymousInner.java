package com.summary.day15.innerclass;

/**
 * 匿名内部类（重点）：
 *  格式：
 *      new 类名或接口名() {
 *          重写的方法;
 *      };
 *
 *  含义：本质是某个父类/接口的一个"没有名字的"子类对象。
 *  常用场景：
 *      - 作为方法实参临时传递实现（接口多态）
 *      - 给字段/局部变量赋值
 *      - 在 lambda 出现之前，是事件回调最常见的写法
 *
 *  与 lambda 的关系：当父类型是"函数式接口"（只有一个抽象方法），可以用 lambda 简写。
 */
public class AnonymousInner {

    public static void main(String[] args) {

        /* ---------- 方式 1：基于接口创建匿名内部类对象 ---------- */
        Swimmer s1 = new Swimmer() {
            @Override
            public void swim() { System.out.println("匿名内部类：自由泳"); }
        };
        s1.swim();

        /* ---------- 方式 2：基于抽象类创建 ---------- */
        Mascot m = new Mascot("冰墩墩") {
            @Override
            public void perform() {
                System.out.println(getName() + " 正在表演");
            }
        };
        m.perform();

        /* ---------- 方式 3：作为方法实参（最常见） ---------- */
        feed(new Mascot("雪容融") {
            @Override
            public void perform() { System.out.println(getName() + " 吃萝卜"); }
        });

        /* ---------- 方式 4：直接调用方法，对象不接收（一次性） ---------- */
        new Swimmer() {
            @Override
            public void swim() { System.out.println("一次性匿名对象：蛙泳"); }
        }.swim();

        /* ---------- 方式 5：lambda 简化（仅函数式接口可用，作为对照） ---------- */
        Swimmer s2 = () -> System.out.println("lambda：仰泳");
        s2.swim();
    }

    /** 体现"接口多态"：参数声明为父接口，实际传任意匿名子类对象。 */
    private static void feed(Mascot mascot) {
        mascot.perform();
    }
}

interface Swimmer {
    void swim();
}

abstract class Mascot {
    private final String name;
    protected Mascot(String name) { this.name = name; }
    public String getName() { return name; }
    public abstract void perform();
}
