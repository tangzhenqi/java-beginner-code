package com.itheima.a04object;

/**
 * 知识点四：Object —— toString
 * <p>
 * 来源：Object 是所有类的父类，所有类直接或间接继承自 Object。
 * <p>
 * toString 的默认实现：返回 "全类名@十六进制哈希码"，例如：
 *   com.itheima.a04object.Student@4eec7777
 * <p>
 * 调用时机：System.out.println(obj) 底层会调用 obj.toString() 把对象转字符串。
 *           字符串拼接 ("..." + obj) 同样会触发 toString。
 * <p>
 * 重写目的：让我们看到对象的属性值，便于调试与日志。
 */
public class ObjectToStringDemo {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o);            // java.lang.Object@xxxxxxxx —— 没有重写

        Student s = new Student("zhangsan", 23);
        System.out.println(s);            // Student{name=zhangsan, age=23} —— 重写后

        // 等价写法：
        System.out.println(s.toString());
        System.out.println("拼接：" + s);
    }
}
