package com.summary.io.object;

import java.io.Serializable;

/**
 * 想要被序列化，类必须实现 Serializable 接口（标记接口，不包含任何方法）。
 *
 * serialVersionUID：
 *      序列化版本号。建议显式声明。如果不写，JVM 会根据类结构生成一个，
 *      只要类结构改变（增删字段、方法签名变化等），版本号就变化，
 *      反序列化已有数据时会抛 InvalidClassException。
 *
 * transient 关键字：
 *      被 transient 修饰的字段不会被序列化（例如密码、临时状态等）。
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String address;

    /** 不参与序列化 */
    private transient String password;

    public Student() {
    }

    public Student(String name, int age, String address, String password) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age
                + ", address='" + address + "', password='" + password + "'}";
    }
}
