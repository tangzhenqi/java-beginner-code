package com.summary.thiskeyword;

/**
 * 知识点 2：this 关键字
 *
 * this 表示"方法的调用者"。它的两种最典型场景：
 *   1. 在构造方法 / setter 中，用来区分"成员变量"和"同名的局部变量（形参）"。
 *      例： this.name = name;
 *   2. 在实例方法中表示"谁调用了我"。
 *      例： r1.attack(r2)   ——  在 attack 方法里 this 就是 r1。
 *
 * 拓展：this(...) 还可以在构造方法的第一行调用本类其它构造方法，做复用。
 */
public class ThisDemo {
    private String name;

    public ThisDemo() {
        // 复用全参构造：必须放在构造方法的第一行
        this("默认名");
    }

    public ThisDemo(String name) {
        this.name = name;
    }

    public void sayHi(ThisDemo other) {
        // this = 方法的调用者，other = 传入的参数
        System.out.println(this.name + " 向 " + other.name + " 打招呼");
    }

    public static void main(String[] args) {
        ThisDemo a = new ThisDemo("张三");
        ThisDemo b = new ThisDemo("李四");
        a.sayHi(b);   // this 是 a
        b.sayHi(a);   // this 是 b

        ThisDemo c = new ThisDemo();  // 调用空参 -> this("默认名")
        System.out.println(c.name);   // 默认名
    }
}
