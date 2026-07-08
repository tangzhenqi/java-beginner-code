package com.summary.extension;

import java.util.Objects;

/**
 * 拓展 2：toString 与 equals 的重写
 *
 *   System.out.println(对象) 默认会打印"地址值"（类似 Cat@1b6d3586），
 *   非常不友好。我们一般要重写 toString() 让它打印属性。
 *
 *   == 比的是地址；想让"两只 name 和 age 都一样的猫"被判等，要重写 equals()。
 *   重写 equals 时强烈建议同时重写 hashCode，否则用到 HashMap/HashSet 时会出错。
 *
 *   IDEA 快捷键：Alt + Insert -> equals() and hashCode() / toString()。
 */
class Cat {
    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{name='" + name + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;
        Cat cat = (Cat) o;
        return age == cat.age && Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

public class ToStringEqualsDemo {
    public static void main(String[] args) {
        Cat c1 = new Cat("咪咪", 2);
        Cat c2 = new Cat("咪咪", 2);
        Cat c3 = c1;

        System.out.println(c1);            // 重写后，打印属性而不是地址
        System.out.println(c1 == c2);      // false：不同对象
        System.out.println(c1.equals(c2)); // true ：内容相同
        System.out.println(c1 == c3);      // true ：同一个地址
    }
}
