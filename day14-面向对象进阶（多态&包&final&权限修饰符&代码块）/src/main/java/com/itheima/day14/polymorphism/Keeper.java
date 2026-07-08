package com.itheima.day14.polymorphism;

/**
 * 饲养员 —— 演示用一个 keepPet(Animal, String) 方法接收所有动物。
 *
 * 用 Animal 作为形参的好处：
 *     新增 Bird / Fish 等子类时，无需修改 Keeper 的接口（开闭原则）。
 *
 * 缺点：
 *     如果想调用某子类的"特有方法"，需要向下转型 + instanceof 检查。
 */
public class Keeper {
    private String name;
    private int age;

    public Keeper() {}

    public Keeper(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    /**
     * 多态形参 + instanceof 模式匹配（JDK 16+ 起的写法）：
     *     if (a instanceof Dog d) { ... }
     * 等价于先 instanceof 判断、再强制类型转换。
     */
    public void keepPet(Animal a, String something) {
        if (a instanceof Dog d) {
            System.out.println("年龄为" + age + "岁的" + name + "养了一只" + a.getColor() + "颜色的" + a.getAge() + "岁的狗");
            d.eat(something);
            d.lookHome(); // 调用子类特有方法
        } else if (a instanceof Cat c) {
            System.out.println("年龄为" + age + "岁的" + name + "养了一只" + a.getColor() + "颜色的" + a.getAge() + "岁的猫");
            c.eat(something);
            c.catchMouse(); // 调用子类特有方法
        } else {
            System.out.println("没有这种动物");
        }
    }
}
