package com.itheima._02_set;

import java.util.Comparator;
import java.util.TreeSet;

/*
 * 知识点 6：TreeSet 的比较器排序 + 多字段排序练习
 *
 *  Comparator.compare(o1, o2)：
 *      o1 : 当前要添加的元素
 *      o2 : 已存在于红黑树中的元素
 *      负 → o1 排前面 / 正 → o1 排后面 / 0 → 视为同一个元素，丢弃
 *
 *  典型场景：
 *      想用一种与 Comparable 不同的临时排序规则，
 *      或者元素类本身没有/无法实现 Comparable。
 *
 *  拓展：JDK8 的 Comparator 静态工厂 + 链式 thenComparing
 */
public class Demo06_TreeSetComparator {
    public static void main(String[] args) {
        // 1) 经典：按字符串长度排序，长度相同按字典序
        TreeSet<String> ts = new TreeSet<>((o1, o2) -> {
            int i = o1.length() - o2.length();
            return i == 0 ? o1.compareTo(o2) : i;
        });
        ts.add("c"); ts.add("ab"); ts.add("df"); ts.add("qwer");
        System.out.println(ts);

        // 2) 多字段排序（Student2 的自然排序已经处理好；这里用比较器做"反向"演示）
        TreeSet<Student2> reverse = new TreeSet<>(
                Comparator.comparingInt(Student2::getSum)              // 总分升序
                        .thenComparingInt(Student2::getChinese)        // 语文升序
                        .thenComparingInt(Student2::getMath)
                        .thenComparing(Student2::getName)
        );
        reverse.add(new Student2("zhangsan", 23, 90, 99, 50));
        reverse.add(new Student2("lisi",     24, 90, 98, 50));
        reverse.add(new Student2("wangwu",   25, 95, 100, 30));
        reverse.add(new Student2("zhaoliu",  26, 60, 99, 70));
        reverse.add(new Student2("qianqi",   26, 70, 80, 70));

        System.out.println("---- 比较器：总分升序 ----");
        reverse.forEach(System.out::println);

        // 3) Student2 自然排序：总分降序
        TreeSet<Student2> natural = new TreeSet<>();
        natural.add(new Student2("zhangsan", 23, 90, 99, 50));
        natural.add(new Student2("lisi",     24, 90, 98, 50));
        natural.add(new Student2("wangwu",   25, 95, 100, 30));
        natural.add(new Student2("zhaoliu",  26, 60, 99, 70));
        natural.add(new Student2("qianqi",   26, 70, 80, 70));

        System.out.println("---- 自然排序：总分降序 ----");
        natural.forEach(System.out::println);
    }
}
