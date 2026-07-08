package com.itheima._04_treemap;

/**
 * TreeMap 自定义键 JavaBean，实现 Comparable 指定默认排序规则。
 *
 * 规则：按年龄升序；年龄相同按姓名字母升序；同姓名年龄视为同一人。
 *
 * 返回值约定：
 *   - 负数：当前要添加的元素小，存左边
 *   - 正数：当前要添加的元素大，存右边
 *   - 0   ：视为重复，舍弃（值会被覆盖）
 */
public class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student() {}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public int    getAge()  { return age; }
    public void   setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{name=" + name + ", age=" + age + "}";
    }

    @Override
    public int compareTo(Student o) {
        int i = this.age - o.age;
        return i == 0 ? this.name.compareTo(o.name) : i;
    }
}
