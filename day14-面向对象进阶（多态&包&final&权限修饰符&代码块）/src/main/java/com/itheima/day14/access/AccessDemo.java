package com.itheima.day14.access;

import com.itheima.day14.access.base.Animal;
import com.itheima.day14.access.base.SamePackageUser;
import com.itheima.day14.access.sub.Dog;

/**
 * 权限修饰符综合演示。
 *
 * 注意：本类与 Animal 不同包、也不是 Animal 的子类，
 *      所以只能访问 Animal 的 public 成员。
 */
public class AccessDemo {
    public static void main(String[] args) {
        Animal a = new Animal();

        // 跨包非子类：仅 public 可见
        System.out.println(a.open);
        a.publicMethod();
        // a.family;             // 编译失败：protected 不允许跨包非子类访问
        // a.packageHi;          // 编译失败：默认包私有
        // a.secret;             // 编译失败：private

        System.out.println("---- 在 Animal 内部访问所有成员 ----");
        a.showAll();

        System.out.println("---- 同包(非子类)访问 ----");
        new SamePackageUser().use();

        System.out.println("---- 跨包子类访问 ----");
        new Dog().show();
    }
}
