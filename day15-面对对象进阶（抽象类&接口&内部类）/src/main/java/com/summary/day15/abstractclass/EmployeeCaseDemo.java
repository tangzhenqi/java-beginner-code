package com.summary.day15.abstractclass;

/**
 * 综合案例（拓展）：员工体系
 *  - Employee 作为抽象父类，封装公共属性 name / baseSalary，定义抽象方法 monthlyPay()
 *  - Manager 在底薪基础上加管理奖金
 *  - Developer 在底薪基础上加项目奖金
 *
 * 拓展点：
 *  - 体现"抽象类 + 多态"在业务建模上的好处：调用方只面向 Employee 编程，新增工种不影响已有代码（开闭原则）。
 */
public class EmployeeCaseDemo {

    public static void main(String[] args) {
        Employee[] team = {
                new Manager("Alice", 12000, 5000),
                new Developer("Bob", 15000, 3),
                new Developer("Carol", 16000, 5)
        };

        double total = 0;
        for (Employee e : team) {
            double pay = e.monthlyPay();
            total += pay;
            System.out.printf("%-8s %-10s 本月薪资：%.2f%n",
                    e.getClass().getSimpleName(), e.getName(), pay);
        }
        System.out.println("团队当月人力成本合计：" + total);
    }
}

abstract class Employee {
    private final String name;
    private final double baseSalary;

    protected Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() { return name; }
    public double getBaseSalary() { return baseSalary; }

    /** 不同工种月薪算法不同 → 抽象方法。 */
    public abstract double monthlyPay();
}

class Manager extends Employee {
    private final double managementBonus;

    public Manager(String name, double baseSalary, double managementBonus) {
        super(name, baseSalary);
        this.managementBonus = managementBonus;
    }

    @Override
    public double monthlyPay() {
        return getBaseSalary() + managementBonus;
    }
}

class Developer extends Employee {
    /** 完成的需求个数。 */
    private final int finishedTasks;

    public Developer(String name, double baseSalary, int finishedTasks) {
        super(name, baseSalary);
        this.finishedTasks = finishedTasks;
    }

    @Override
    public double monthlyPay() {
        return getBaseSalary() + finishedTasks * 800.0;
    }
}
