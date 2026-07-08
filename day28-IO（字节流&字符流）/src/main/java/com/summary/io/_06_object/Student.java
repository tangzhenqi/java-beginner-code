package com.summary.io._06_object;

import java.io.Serializable;

/**
 * 序列化实体。
 *
 * 要点：
 *   1) 必须 implements Serializable（标记接口，无方法）
 *   2) 建议显式声明 serialVersionUID，避免字段微调后旧文件反序列化失败
 *   3) transient 修饰的字段 **不参与** 序列化，反序列化后是默认值
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private transient String password; // 不写入文件

    public Student() {
    }

    public Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "Student{name=" + name + ", age=" + age + ", password=" + password + "}";
    }
}
