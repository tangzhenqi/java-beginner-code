package com.itheima.day14.finalkw;

/**
 * final 修饰方法 —— 该方法不可被子类重写。
 * 拓展：在模板方法模式中，常用 final 锁定模板骨架，防止破坏算法结构。
 */
public class FinalParent {

    public final void show() {
        System.out.println("FinalParent.show()  —— 这是一个 final 方法，子类不能 @Override");
    }

    public void hello() {
        System.out.println("FinalParent.hello() —— 普通方法，子类可以重写");
    }
}
