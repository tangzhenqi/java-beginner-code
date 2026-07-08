package com.summary.multi;

/**
 * 知识点 2：多对象互不干扰
 *
 *   每 new 一次，都会在堆里开辟一块"新的内存"用来存这个对象的属性。
 *   所以 gf1.name 和 gf2.name 是两份完全独立的数据，互不影响。
 *
 * 一句话：类是模板，对象才是"一个个具体的实物"。一个模板可以生产无数个实物。
 */
public class MultiObjectDemo {
    public static void main(String[] args) {
        GirlFriend gf1 = new GirlFriend();
        gf1.name = "小诗诗";
        gf1.age = 18;
        gf1.gender = "萌妹子";

        GirlFriend gf2 = new GirlFriend();
        gf2.name = "小丹丹";
        gf2.age = 19;
        gf2.gender = "萌妹子";

        gf1.eat();   // 小诗诗 在吃饭
        gf2.eat();   // 小丹丹 在吃饭

        // 改 gf1.age 不会影响 gf2.age
        gf1.age = 20;
        System.out.println(gf1.age + " / " + gf2.age);   // 20 / 19
    }
}
