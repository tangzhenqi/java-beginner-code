package com.itheima._02_set;

import java.util.Objects;

/**
 * Set 系列集合判断"元素是否重复"完全依赖 equals()，
 * 而 HashSet 在调用 equals 之前还会先用 hashCode 进行散列定位。
 *
 * 所以业务类要被放进 Set 时，必须同时重写 equals 和 hashCode：
 *      规则 1：equals 返回 true 的两个对象，hashCode 必须相等
 *      规则 2：hashCode 相等的对象，equals 不一定相等（哈希碰撞是允许的）
 *
 * 同时实现 Comparable 是为了能放进 TreeSet。
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
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{name=" + name + ", age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    /*
     * Comparable.compareTo：自然排序规则
     *      this：当前要添加的元素
     *      o   ：已经存在于红黑树中的元素
     *      负数 → 当前元素小，存左边
     *      正数 → 当前元素大，存右边
     *      0   → 视为相同，不存（TreeSet 去重的关键）
     */
    @Override
    public int compareTo(Student o) {
        // 主：按年龄升序
        int result = this.age - o.age;
        // 副：年龄相同时按姓名字典序（避免不同人被当成同一人丢失）
        return result == 0 ? this.name.compareTo(o.name) : result;
    }
}
