package com.summary.day15.interfacekw;

/**
 * 接口基础：
 *  1. 接口用 interface 定义，类实现接口用 implements。
 *  2. 接口体现"额外能力"。比如所有动物会吃 → 放父类；只有部分动物会游 → 抽成接口。
 *  3. 接口多态：父接口引用 = 实现类对象。
 */
public class _01_InterfaceBasicsDemo {

    public static void main(String[] args) {
        BFrog  f = new BFrog("小青");
        BDuck  d = new BDuck("唐老鸭");

        f.eat();
        f.swim();
        d.eat();
        d.swim();

        // 接口多态：调用方只关心"能游泳"，不在乎具体物种
        Swimmable s = f;
        s.swim();
    }
}

/** 共性属性 + 共性方法 放抽象父类。 */
abstract class BAnimal {
    private final String name;
    protected BAnimal(String name) { this.name = name; }
    public String getName() { return name; }
    public abstract void eat();
}

/** 额外能力（部分动物才有）放接口。 */
interface Swimmable {
    void swim();   // 默认就是 public abstract，关键字可省略
}

class BFrog extends BAnimal implements Swimmable {
    public BFrog(String name) { super(name); }
    @Override public void eat()  { System.out.println(getName() + " 吃虫子"); }
    @Override public void swim() { System.out.println(getName() + " 蛙泳"); }
}

class BDuck extends BAnimal implements Swimmable {
    public BDuck(String name) { super(name); }
    @Override public void eat()  { System.out.println(getName() + " 吃小鱼"); }
    @Override public void swim() { System.out.println(getName() + " 鸭子划水"); }
}
