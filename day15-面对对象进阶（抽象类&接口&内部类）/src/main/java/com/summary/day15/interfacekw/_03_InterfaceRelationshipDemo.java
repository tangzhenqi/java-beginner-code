package com.summary.day15.interfacekw;

/**
 * 类与接口、接口与接口的关系：
 *  类与类：    单继承 (extends 一个父类)
 *  类与接口：  多实现 (implements 多个接口)，且可以"继承一个类 + 实现多个接口"
 *  接口与接口：多继承 (一个接口可以 extends 多个父接口)
 *
 * 细节：如果实现类实现了"最下面"的子接口，必须把链上所有抽象方法都重写。
 */
public class _03_InterfaceRelationshipDemo {

    public static void main(String[] args) {
        // 1. 多实现
        MultiImpl m = new MultiImpl();
        m.fly();
        m.swim();

        // 2. 实现"接口继承接口"链
        ChainImpl c = new ChainImpl();
        c.eat();
        c.fly();
        c.swim();
    }
}

/* ---------- 1. 多实现 ---------- */
interface Flyable { void fly(); }
interface Diveable { void swim(); }

class MultiImpl implements Flyable, Diveable {
    @Override public void fly()  { System.out.println("飞"); }
    @Override public void swim() { System.out.println("游"); }
}

/* ---------- 2. 接口的多继承 ---------- */
interface IEat { void eat(); }
interface IMove extends Flyable, Diveable {  // 接口可以多继承
    // 自己还可以再加抽象方法
}
interface IAllInOne extends IEat, IMove { }  // 链条最下层

/** 只要实现最下面那个，所有抽象方法必须全部重写。 */
class ChainImpl implements IAllInOne {
    @Override public void eat()  { System.out.println("吃"); }
    @Override public void fly()  { System.out.println("飞"); }
    @Override public void swim() { System.out.println("游"); }
}
