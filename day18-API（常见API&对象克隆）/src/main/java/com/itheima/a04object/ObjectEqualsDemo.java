package com.itheima.a04object;

/**
 * 知识点四：Object —— equals
 * <p>
 * Object 默认实现：return this == obj，即比较地址值。
 * 重写后：比较"内容"（属性值），更符合业务语义。
 * <p>
 * 重写 equals 时必须同时重写 hashCode，保证 hash 集合契约。
 * <p>
 * 易错点：调用者类型决定走哪个 equals。
 *   String 的 equals 先做类型判断，参数不是 String 直接 false；
 *   StringBuilder 没重写 equals，所以走的是 Object 中比较地址值的版本。
 */
public class ObjectEqualsDemo {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 23);
        Student s2 = new Student("zhangsan", 23);
        Student s3 = s1;

        System.out.println(s1 == s2);        // false  地址不同
        System.out.println(s1.equals(s2));   // true   重写后比较属性
        System.out.println(s1 == s3);        // true   同一个引用

        // 易错：String vs StringBuilder
        String s = "abc";
        StringBuilder sb = new StringBuilder("abc");
        System.out.println(s.equals(sb));    // false  String.equals 判类型不一致
        System.out.println(sb.equals(s));    // false  StringBuilder 走 Object 比地址

        // 等价的字符串字面量：
        System.out.println(s.equals(sb.toString()));   // true
    }
}
