package com.itheima.day14.polymorphism;

public class KeepPetDemo {
    public static void main(String[] args) {
        Keeper p = new Keeper("老王", 30);
        Dog d = new Dog(2, "黑");
        Cat c = new Cat(3, "灰");

        p.keepPet(d, "骨头");
        p.keepPet(c, "鱼");
    }
}
