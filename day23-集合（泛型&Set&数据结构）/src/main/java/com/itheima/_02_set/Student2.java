package com.itheima._02_set;

/**
 * 多字段排序的演示对象，对应 Demo06。
 *
 * 排序规则：
 *      1) 总分降序
 *      2) 总分相同 → 语文降序
 *      3) 语文相同 → 数学降序
 *      4) 数学相同 → 英语降序
 *      5) 英语相同 → 年龄升序
 *      6) 年龄相同 → 姓名字典序
 *      7) 全相同   → 视为同一人，不存
 */
public class Student2 implements Comparable<Student2> {
    private String name;
    private int age;
    private int chinese;
    private int math;
    private int english;

    public Student2() {}

    public Student2(String name, int age, int chinese, int math, int english) {
        this.name = name;
        this.age = age;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }

    public int getSum() { return chinese + math + english; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getChinese() { return chinese; }
    public int getMath() { return math; }
    public int getEnglish() { return english; }

    @Override
    public String toString() {
        return "Student2{name=" + name + ", age=" + age
                + ", 语=" + chinese + ", 数=" + math + ", 英=" + english
                + ", 总分=" + getSum() + "}";
    }

    @Override
    public int compareTo(Student2 o) {
        // 1) 总分降序（注意是 o - this）
        int i = o.getSum() - this.getSum();
        // 2) 语文降序
        i = i == 0 ? o.chinese - this.chinese : i;
        // 3) 数学降序
        i = i == 0 ? o.math - this.math : i;
        // 4) 英语降序
        i = i == 0 ? o.english - this.english : i;
        // 5) 年龄升序
        i = i == 0 ? this.age - o.age : i;
        // 6) 姓名字典序
        i = i == 0 ? this.name.compareTo(o.name) : i;
        return i;
    }
}
