package com.itheima._06_practice;

import java.util.ArrayList;

/**
 * 综合练习：通配符 + 集合 + 多态。
 *
 *  需求：定义一个 keepPet 方法，要求：
 *   ① 只能传"猫科"的集合       -> ArrayList<? extends Cat>
 *   ② 只能传"犬科"的集合       -> ArrayList<? extends Dog>
 *   ③ 能传任意动物的集合       -> ArrayList<? extends Animal>
 *
 *  这里同时演示三种"上界通配符"的区别，主方法默认调用最宽的那个版本。
 */
public class PetTest {

    public static void main(String[] args) {
        ArrayList<PersianCat> persians = new ArrayList<>();
        persians.add(new PersianCat("乐乐", 2));
        persians.add(new PersianCat("妞妞", 1));

        ArrayList<LiHuaCat> liHuas = new ArrayList<>();
        liHuas.add(new LiHuaCat("花花", 3));

        ArrayList<TeddyDog> teddys = new ArrayList<>();
        teddys.add(new TeddyDog("小棕", 2));

        ArrayList<HuskyDog> huskys = new ArrayList<>();
        huskys.add(new HuskyDog("二哈", 4));

        // —— 要求 ③：任意动物 —— 全部能传
        keepAnyPet(persians);
        keepAnyPet(liHuas);
        keepAnyPet(teddys);
        keepAnyPet(huskys);

        // —— 要求 ① 仅猫：keepCat(huskys) 编译期就报错（注释掉看）——
        keepCat(persians);
        keepCat(liHuas);
        // keepCat(teddys); // ❌

        // —— 要求 ② 仅狗 ——
        keepDog(teddys);
        keepDog(huskys);
        // keepDog(persians); // ❌
    }

    public static void keepCat(ArrayList<? extends Cat> list) {
        for (Cat c : list) c.eat();
    }

    public static void keepDog(ArrayList<? extends Dog> list) {
        for (Dog d : list) d.eat();
    }

    public static void keepAnyPet(ArrayList<? extends Animal> list) {
        for (Animal a : list) a.eat();
    }
}
