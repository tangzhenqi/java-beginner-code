package com.itheima.day14.codeblock;

/**
 * 演示"构造代码块"和"静态代码块"放在同一个类中的执行顺序：
 *
 *      静态代码块  static { ... }
 *          - 跟随类的加载执行
 *          - 整个 JVM 生命周期内只执行 1 次
 *          - 常用来初始化静态资源（如读取配置、注册驱动）
 *
 *      构造代码块（实例初始化块）  { ... }
 *          - 每次 new 对象时，先于构造方法执行
 *          - 把多个构造方法中重复的代码抽出来
 *          - 实际开发中已经不太常用（逐步被构造方法 / 工厂方法替代）
 *
 *      构造方法
 *          - 每次 new 对象时执行
 */
public class Student {
    private String name;
    private int age;

    static {
        System.out.println("[静态代码块] 类加载时执行，只执行一次");
    }

    {
        System.out.println("[构造代码块] 每次创建对象都执行，先于构造方法");
    }

    public Student() {
        System.out.println("[构造方法] 空参");
    }

    public Student(String name) {
        this.name = name;
        System.out.println("[构造方法] 一参：name=" + name);
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("[构造方法] 两参：name=" + name + ", age=" + age);
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}
