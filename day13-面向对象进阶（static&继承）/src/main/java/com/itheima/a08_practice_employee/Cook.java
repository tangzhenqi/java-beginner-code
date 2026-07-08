package com.itheima.a08_practice_employee;

public class Cook extends Employee {

    public Cook() {}

    public Cook(String id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public void work() {
        System.out.println(getName() + " 正在炒菜");
    }
}
