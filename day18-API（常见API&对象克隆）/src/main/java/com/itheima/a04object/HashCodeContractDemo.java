package com.itheima.a04object;

import java.util.HashSet;

/**
 * 知识点四：Object —— 拓展：equals 与 hashCode 的契约
 * <p>
 * 契约：
 *   1. 自反性：a.equals(a) 必须为 true
 *   2. 对称性：a.equals(b) ⇔ b.equals(a)
 *   3. 传递性：a.equals(b) 且 b.equals(c) ⇒ a.equals(c)
 *   4. 一致性：在对象未被修改时，多次调用结果不变
 *   5. 与 null 比较：a.equals(null) 必须为 false
 *   6. equals 相等的两个对象 hashCode 必须相同（反过来不要求）
 * <p>
 * 违反契约会导致：HashMap / HashSet 出现"明明存进去了却查不到"的诡异问题。
 */
public class HashCodeContractDemo {
    public static void main(String[] args) {
        // 演示：HashSet 依赖 hashCode 找桶，再用 equals 比较元素
        HashSet<Student> set = new HashSet<>();
        set.add(new Student("zhangsan", 23));

        Student probe = new Student("zhangsan", 23);
        System.out.println("set 中含等价对象？ " + set.contains(probe));   // true：因为重写了 equals/hashCode

        // 如果没重写 hashCode，set.contains 大概率返回 false，因为 hashCode 不同 → 找的桶不同。
        System.out.println("probe.hashCode = " + probe.hashCode());
    }
}
