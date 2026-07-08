package com.itheima.a02system;

/**
 * 知识点二：System.arraycopy —— 细节与拓展
 * <p>
 * 细节：
 *   1. 数据源数组和目的地数组都是基本类型时，类型必须一致，否则抛 ArrayStoreException。
 *   2. 不能越界，否则抛 IndexOutOfBoundsException。
 *   3. 都是引用类型时，子类对象可以赋给父类数组（协变数组）。
 * <p>
 * 与其他拷贝方式对比：
 *   System.arraycopy     —— native，性能最快，是其他拷贝方法的底层实现。
 *   Arrays.copyOf        —— 内部调用 System.arraycopy，会创建一个新数组。
 *   Arrays.copyOfRange   —— 同上，按区间拷贝。
 *   for 循环手动拷贝       —— 最慢，但灵活。
 *   clone()              —— 数组对象自带 clone()，是浅拷贝。
 * <p>
 * 各方法参数说明：
 *   System.arraycopy(src, srcPos, dest, destPos, length)
 *     src      —— 源数组（数据来源）
 *     srcPos   —— 源数组的起始索引（从哪里开始拷）
 *     dest     —— 目标数组（拷到哪里去，必须已存在）
 *     destPos  —— 目标数组的起始索引（从哪里开始放）
 *     length   —— 拷贝的元素个数
 *     特点：不创建新数组，需要提前准备好目标数组。
 *
 *   Arrays.copyOf(original, newLength)
 *     original  —— 源数组
 *     newLength —— 新数组的长度
 *     特点：固定从索引 0 开始拷贝；newLength 小于原长度则截断，大于则用默认值
 *           （int 补 0，对象补 null）填充；返回一个新数组。
 *
 *   Arrays.copyOfRange(original, from, to)
 *     original —— 源数组
 *     from     —— 起始索引（包含）
 *     to       —— 结束索引（不包含）
 *     特点：拷贝区间 [from, to)，含头不含尾，长度为 to - from；返回一个新数组。
 */
public class ArrayCopyDemo {
    public static void main(String[] args) {
        // 演示子类数组拷贝到父类数组
        Student[] students = {new Student("zhangsan", 23), new Student("lisi", 24)};
        Person[] people = new Person[2];
        System.arraycopy(students, 0, people, 0, 2);
        for (Person p : people) {
            System.out.println(p);
        }

        // 同源拷贝：从数组自身的一部分拷贝到自身的另一部分（src/dst 区域可重叠）
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        System.arraycopy(arr, 0, arr, 2, 4);   // arr => 1 2 1 2 3 4 7 8
        StringBuilder sb = new StringBuilder();
        for (int v : arr) sb.append(v).append(' ');
        System.out.println(sb);

        // 类型不兼容会抛异常：
        // int[] a = {1, 2};
        // long[] b = new long[2];
        // System.arraycopy(a, 0, b, 0, 2);   // ArrayStoreException
    }
}

class Person {
    private String name;
    private int age;

    public Person() {}
    public Person(String name, int age) { this.name = name; this.age = age; }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() { return getClass().getSimpleName() + "{name=" + name + ", age=" + age + "}"; }
}

class Student extends Person {
    public Student() {}
    public Student(String name, int age) { super(name, age); }
}
