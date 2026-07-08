package com.itheima.a08_practice_employee;

public class Demo {
    public static void main(String[] args) {
        Manager m = new Manager("heima001", "张三", 15000, 8000);
        System.out.println(m.getId() + ", " + m.getName() + ", "
                + m.getSalary() + ", 奖金=" + m.getBonus());
        m.work(); // 重写后的方法
        m.eat();  // 继承自父类

        System.out.println("--------");

        Cook c = new Cook("heima002", "李四", 8000);
        System.out.println(c.getId() + ", " + c.getName() + ", " + c.getSalary());
        c.work();
        c.eat();
    }
}
