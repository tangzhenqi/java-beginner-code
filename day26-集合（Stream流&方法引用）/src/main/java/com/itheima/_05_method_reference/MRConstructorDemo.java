package com.itheima._05_method_reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方法引用 3：引用构造方法
 * <p>
 * 格式：类名::new
 * <p>
 * 流中元素 -> 作为构造方法实参 -> 得到新对象。
 * 要求：必须存在一个匹配形参的构造方法。
 * 这里 {@link Student#Student(String)} 接收 "张三,15" 这样的字符串。
 */
public class MRConstructorDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌,15", "周芷若,14", "赵敏,13", "张强,20");

        // 等价于 .map(s -> new Student(s))
        List<Student> students = list.stream()
                .map(Student::new)
                .collect(Collectors.toList());
        students.forEach(System.out::println);
    }
}
