package com.itheima.day14.codeblock;

/**
 * 代码块演示：
 *      - 第一次使用 Student 时，会触发"类加载" —— 静态代码块跑一次
 *      - 每次 new 都会先跑"构造代码块"，再跑"构造方法"
 */
public class CodeBlockDemo {
    public static void main(String[] args) {
        System.out.println(">>> 准备创建 s1");
        Student s1 = new Student();

        System.out.println(">>> 准备创建 s2");
        Student s2 = new Student("zhangsan");

        System.out.println(">>> 准备创建 s3");
        Student s3 = new Student("lisi", 20);

        // 局部代码块（写在方法里的 {}）：用于限制变量的作用域
        {
            int tmp = 100;
            System.out.println("局部代码块内：tmp = " + tmp);
        }
        // System.out.println(tmp);  // 编译失败：tmp 已超出作用域
    }
}
