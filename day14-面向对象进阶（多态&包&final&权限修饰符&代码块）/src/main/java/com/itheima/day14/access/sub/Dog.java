package com.itheima.day14.access.sub;

import com.itheima.day14.access.base.Animal;

/**
 * 跨包子类对父类成员的访问能力：
 *      可以访问 protected 和 public，不能访问 private 和 default。
 */
public class Dog extends Animal {

    public void show() {
        // System.out.println(secret);     // 编译失败：private 不能跨类
        // System.out.println(packageHi);  // 编译失败：default 跨包不可见
        System.out.println(family);         // OK：protected 给子类用
        System.out.println(open);           // OK：public

        // 通过 this/super 调用方法同理
        protectedMethod();
        publicMethod();
    }
}
