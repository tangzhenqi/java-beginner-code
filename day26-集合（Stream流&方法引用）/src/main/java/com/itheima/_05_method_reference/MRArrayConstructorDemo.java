package com.itheima._05_method_reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 方法引用 5：引用数组的构造方法
 * <p>
 * 格式：类型[]::new
 * <p>
 * 用途：终结方法 toArray 需要传一个「根据长度创建数组的函数」
 * 等价于 size -> new Integer[size]
 * <p>
 * 注意：数组类型必须与流元素类型一致，否则 ArrayStoreException。
 */
public class MRArrayConstructorDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5);

        Integer[] arr = list.stream().toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr));

        // 组合：先用 Student::new 把字符串变对象，再用 Student[]::new 收集成数组
        ArrayList<String> raw = new ArrayList<>();
        Collections.addAll(raw, "张无忌,15", "周芷若,14");
        Student[] students = raw.stream()
                .map(Student::new)
                .toArray(Student[]::new);
        System.out.println(Arrays.toString(students));
    }
}
