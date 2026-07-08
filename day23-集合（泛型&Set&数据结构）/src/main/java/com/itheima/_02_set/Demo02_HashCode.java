package com.itheima._02_set;

/*
 * 知识点 2：哈希值
 *
 *  概念：对象的整数表现形式，由 Object.hashCode() 计算。
 *
 *  规则：
 *      1) 不重写 hashCode：每个对象的哈希值基于内存地址，几乎不会相同。
 *      2) 重写 hashCode：可让"业务上等价"的两个对象拥有相同的哈希值。
 *      3) 哈希碰撞：不同内容/地址也可能产生相同的哈希值——这是允许的，
 *          但好的 hashCode 应当尽量分散，减少碰撞。
 *
 *  哈希值的用途：
 *      —— HashSet / HashMap 用它做"散列定位"：先算 hash 决定落在哪个桶里，
 *         再用 equals 在桶内比较精确相等。
 *
 *  课堂经典哈希碰撞：
 *      "Aa".hashCode() == "BB".hashCode()  // 都是 2112
 *      "abc".hashCode() == "acD".hashCode()
 */
public class Demo02_HashCode {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 23);
        Student s2 = new Student("zhangsan", 23);
        Student s3 = new Student("lisi",     24);

        // Student 已重写 hashCode：相同属性 → 相同哈希值
        System.out.println("s1.hashCode() = " + s1.hashCode());
        System.out.println("s2.hashCode() = " + s2.hashCode());
        System.out.println("s3.hashCode() = " + s3.hashCode());

        // 经典哈希碰撞演示
        System.out.println("---- 哈希碰撞 ----");
        System.out.println("\"Aa\".hashCode()  = " + "Aa".hashCode());
        System.out.println("\"BB\".hashCode()  = " + "BB".hashCode());
        System.out.println("\"abc\".hashCode() = " + "abc".hashCode());
        System.out.println("\"acD\".hashCode() = " + "acD".hashCode());
    }
}
