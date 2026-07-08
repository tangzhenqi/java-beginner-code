package com.summary;

/**
 * 知识点2：变量
 *
 *   格式：  数据类型 变量名 = 数据值;
 *   作用：  在内存开辟空间存储数据，其值可在程序运行中改变
 *
 * 注意事项：
 *   1. 变量必须先声明再使用
 *   2. 必须先赋值才能参与运算或打印
 *   3. 同一作用域内不能定义同名变量
 *   4. long 字面量加 L，float 字面量加 F
 */
public class VariableDemo {
    public static void main(String[] args) {
        int age = 18;
        double money = 99.9;
        char gender = '男';
        boolean isStudent = true;
        String name = "张三";        // String 是引用类型

        // 变量值可变
        age = 19;
        money += 0.1;

        System.out.println(name + " " + age + " " + gender + " " + money + " " + isStudent);

        // long 与 float 字面量后缀
        long population = 1400000000L;
        float pi = 3.14F;
        System.out.println(population + " " + pi);
    }
}
