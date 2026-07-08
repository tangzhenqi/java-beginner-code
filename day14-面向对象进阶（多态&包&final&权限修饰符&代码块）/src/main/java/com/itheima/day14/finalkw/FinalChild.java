package com.itheima.day14.finalkw;

public class FinalChild extends FinalParent {

    // @Override public void show() { ... }   // 编译失败：不能重写 final 方法

    @Override
    public void hello() {
        System.out.println("FinalChild.hello() —— 子类重写了非 final 方法");
    }
}
