package com.itheima.a06_extends_constructor;

/**
 * 子类构造方法的两条规则：
 *  1. 子类构造方法的第一行 默认 隐藏 super();（调用父类无参构造）。
 *     —— 如果父类没有无参构造，子类必须显式 super(参数) 调用某个父类构造。
 *  2. 在第一行可以显式写 super(参数) 或 this(参数)，二者互斥，且只能存在一行。
 *
 * 拓展点：this(...) 调用本类其他构造时，会接管参数填充工作，
 * 编译器就不再插入 super() 了（间接通过被调用的那个构造去调 super()）。
 */
public class Student extends Person {

    String school;

    public Student() {
        // super(); // 这一行写不写都一样，编译器会自动补上
        System.out.println("Student() 子类无参构造");
    }

    public Student(String name, int age) {
        super(name, age); // 显式调用父类带参构造
        System.out.println("Student(String,int) 子类带参构造");
    }

    public Student(String name, int age, String school) {
        this(name, age); // 调用本类的另一个构造（间接完成 super(name,age) ）
        this.school = school;
        System.out.println("Student(String,int,String) 子类三参构造");
    }
}
