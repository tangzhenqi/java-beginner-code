package com.summary.creation;

/**
 * 拓展：字符串常量池（StringPool）
 *
 *   - 用"直接赋值"的方式创建的字符串，会先去"串池"里找有没有相同内容的，
 *     有就复用，没有就建一个新的放进池子。所以同样写 "abc" 多次，只会有一份。
 *
 *   - 用 new String("abc") 一定在堆里"另外"开一块内存放，所以和池子里的 "abc" 不是同一个对象。
 *
 *   - 调用 s.intern() 会把字符串"放进/复用"常量池，返回池中那一份的引用。
 *
 * 结论：以后比较字符串内容用 equals，比较是否同一对象（地址）才用 ==。
 */
public class StringPoolDemo {
    public static void main(String[] args) {
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        String d = c.intern();

        System.out.println(a == b);   // true  —— 都指向常量池里同一份 "abc"
        System.out.println(a == c);   // false —— c 在堆里另外开了一块
        System.out.println(a == d);   // true  —— d 是池中那一份的引用
        System.out.println(a.equals(c)); // true —— 比较内容
    }
}
