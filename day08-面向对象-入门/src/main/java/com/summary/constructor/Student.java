package com.summary.constructor;

/**
 * 知识点 5：构造方法（Constructor）
 *
 * 写法特征：
 *   - 方法名 = 类名
 *   - 没有返回值类型（连 void 都不能写）
 *   - new 对象时自动执行
 *
 * 默认构造：
 *   - 如果你"一个构造都没写"，编译器会偷偷给你加一个"public 空参构造"。
 *   - 一旦你"写了任何一个构造"（如下面的全参构造），编译器就不再加默认的空参了！
 *     这就是为什么很多教材让你"写了全参，最好也手动补一个空参"。
 *
 * 构造方法之间的复用：用 this(...) 调用本类其它构造，必须放在第一行。
 */
public class Student {
    private String name;
    private int age;

    // 手动写的空参构造：避免"写了全参就不能 new XXX()"的坑
    public Student() {
    }

    // 全参构造
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 只接收 name，age 给一个默认值 18
    // —— 演示 this(...) 复用其它构造方法
    public Student(String name) {
        this(name, 18);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
