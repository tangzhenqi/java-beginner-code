package com.itheima.a08_practice_employee;

/**
 * 抽取父类的标准做法：
 *  1. 类名见名知意。
 *  2. 所有成员变量私有化（private），通过 get/set 暴露。
 *  3. 提供空参 + 全参 两个构造方法。
 *  4. 共性的行为（work / eat ...）提到父类中。
 */
public class Employee {

    private String id;
    private String name;
    private double salary;

    public Employee() {}

    public Employee(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public void work() {
        System.out.println("员工在工作");
    }

    public void eat() {
        System.out.println("员工在吃米饭");
    }
}
