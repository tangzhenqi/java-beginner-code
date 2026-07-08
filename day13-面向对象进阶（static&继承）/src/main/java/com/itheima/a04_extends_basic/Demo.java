package com.itheima.a04_extends_basic;

/**
 * 继承的好处：
 *  - 复用：子类拿到父类中所有非 private 成员，不必重复书写。
 *  - 扩展：子类可以在父类基础上增加自己特有的成员。
 *
 * 何时使用继承：当 "子类 is-a 父类"（如 哈士奇 is-a 狗 is-a 动物）时才用，
 * 不要把没有 is-a 关系的类强行组成继承体系。
 */
public class Demo {
    public static void main(String[] args) {
        Husky h = new Husky();
        h.eat();        // 继承自 Animal
        h.drink();      // 继承自 Animal
        h.lookHome();   // 继承自 Dog
        h.breakHome();  // 自身扩展

        System.out.println("--------");

        Cat c = new Cat();
        c.eat();
        c.catchMouse();
    }
}
