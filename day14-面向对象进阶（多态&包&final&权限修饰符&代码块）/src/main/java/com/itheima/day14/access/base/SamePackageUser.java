package com.itheima.day14.access.base;

/**
 * 同包"非子类"对 Animal 的访问能力：
 *      可以访问 default / protected / public，访问不了 private。
 */
public class SamePackageUser {

    public void use() {
        Animal a = new Animal();

        // a.secret              // 编译失败：private 不可访问
        System.out.println(a.packageHi);   // OK：同包默认可见
        System.out.println(a.family);      // OK：同包 protected 可见
        System.out.println(a.open);        // OK：public 总是可见

        // a.privateMethod();    // 编译失败
        a.packageMethod();
        a.protectedMethod();
        a.publicMethod();
    }
}
