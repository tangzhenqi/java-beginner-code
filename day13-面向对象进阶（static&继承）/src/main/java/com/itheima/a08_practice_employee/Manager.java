package com.itheima.a08_practice_employee;

public class Manager extends Employee {

    private double bonus;

    public Manager() {}

    public Manager(String id, String name, double salary, double bonus) {
        super(id, name, salary); // 父类字段由父类构造负责赋值
        this.bonus = bonus;
    }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    @Override
    public void work() {
        System.out.println(getName() + " 正在管理团队");
    }
}
