package com.itheima._04_test;

import java.util.ArrayList;

/*
 * 综合练习：用通配符约束"可饲养的宠物范围"
 *
 *  动物体系：
 *                       Animal
 *                    ┌────────┐
 *                   Cat      Dog
 *                  ┌──┴──┐  ┌──┴──┐
 *              PersianCat LiHuaCat TeddyDog HuskyDog
 *
 *  通过同一个 keepPet 方法的"上界通配符"展示三种约束：
 *      keepPetAny  : ArrayList<? extends Animal>  —— 所有动物都行
 *      keepPetCat  : ArrayList<? extends Cat>     —— 只能养猫
 *      keepPetDog  : ArrayList<? extends Dog>     —— 只能养狗
 */
public class KeepPetTest {

    public static void main(String[] args) {
        ArrayList<PersianCat> persianCats = new ArrayList<>();
        persianCats.add(new PersianCat("加菲", 3));
        persianCats.add(new PersianCat("Tom", 2));

        ArrayList<LiHuaCat> liHuaCats = new ArrayList<>();
        liHuaCats.add(new LiHuaCat("阿狸", 4));

        ArrayList<TeddyDog> teddyDogs = new ArrayList<>();
        teddyDogs.add(new TeddyDog("豆豆", 1));

        ArrayList<HuskyDog> huskyDogs = new ArrayList<>();
        huskyDogs.add(new HuskyDog("二哈", 2));

        System.out.println("==== 任意动物都能养 ====");
        keepPetAny(persianCats);
        keepPetAny(liHuaCats);
        keepPetAny(teddyDogs);
        keepPetAny(huskyDogs);

        System.out.println("==== 只能养猫 ====");
        keepPetCat(persianCats);
        keepPetCat(liHuaCats);
        // keepPetCat(teddyDogs);   // 编译错误：TeddyDog 不在 Cat 体系下

        System.out.println("==== 只能养狗 ====");
        keepPetDog(teddyDogs);
        keepPetDog(huskyDogs);
        // keepPetDog(persianCats); // 编译错误
    }

    /** 要求 1：所有动物都能养，不允许其他类型。 */
    public static void keepPetAny(ArrayList<? extends Animal> list) {
        for (Animal a : list) a.eat();
    }

    /** 要求 2：只能养所有品种的猫。 */
    public static void keepPetCat(ArrayList<? extends Cat> list) {
        for (Cat c : list) c.eat();
    }

    /** 要求 3：只能养所有品种的狗。 */
    public static void keepPetDog(ArrayList<? extends Dog> list) {
        for (Dog d : list) d.eat();
    }
}
