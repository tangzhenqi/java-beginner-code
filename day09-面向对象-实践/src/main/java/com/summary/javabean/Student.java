package com.summary.javabean;

/**
 * 知识点 1：JavaBean 标准类
 *
 * 一个"标准的 JavaBean"必须满足下面的几条：
 *   1. 类用 public 修饰
 *   2. 成员变量全部 private
 *   3. 提供 public 的空参构造方法
 *   4. 提供 public 的全参构造方法
 *   5. 为每个属性提供 getXxx / setXxx 方法
 *
 * IDEA 快捷生成：
 *   Alt + Insert -> Constructor / Getter and Setter
 *
 * 拓展：JDK 14+ 之后可以用 record 一行写完一个不可变 JavaBean：
 *   public record Student(int id, String name, int age) {}
 *   但在本课程使用的 JDK 8 中仍以传统写法为准。
 */
public class Student {
    private int id;
    private String name;
    private int age;

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
