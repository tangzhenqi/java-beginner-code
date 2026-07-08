package com.summary.thiskeyword;

/**
 * 知识点 4：this 关键字
 *
 * this 表示"调用本方法的那个对象本身"。
 *
 * 最常见的用途：在 setter / 构造方法里，形参名和成员变量名"恰好一样"，
 * 这时编译器会按"就近原则"，把没加 this. 的 age 解释成形参。
 * 想要明确指代成员变量，就在前面加 this. 即可。
 *
 *   public void setAge(int age) {
 *       this.age = age;   // 左边的 this.age = 成员变量；右边的 age = 形参
 *   }
 *
 * 如果方法里没有同名局部变量，写不写 this. 都一样（隐式 this）。
 */
public class Person {
    private int age;

    public void method() {
        // 没有同名局部变量，两种写法等价：
        System.out.println("age      = " + age);
        System.out.println("this.age = " + this.age);
    }

    public void setAge(int age) {
        // 这里必须加 this，否则 age = age 是把形参赋给形参，成员变量永远是 0。
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
