package com.summary.io.object;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 对象序列化流 ObjectOutputStream
 *
 * 构造方法：
 *      public ObjectOutputStream(OutputStream out)     把基本流变成高级流
 *
 * 成员方法：
 *      public final void writeObject(Object obj)        把对象序列化到文件
 *
 * 序列化的对象必须实现 Serializable 接口，否则抛 NotSerializableException。
 *
 * 想一次性写出多个对象时，建议把它们装进集合（ArrayList 也实现了 Serializable），
 * 然后整体写出，这样读取时也只读一次即可。
 */
public class ObjectOutputDemo {
    public static void main(String[] args) throws IOException {
        // 写出单个对象
        Student stu = new Student("zhangsan", 23, "南京", "secret123");

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("resources/student.obj"))) {
            oos.writeObject(stu);
        }
        System.out.println("单个对象写出完成");

        // 写出一组对象（推荐：用集合包装）
        List<Student> list = Arrays.asList(
                new Student("a", 18, "北京", "p1"),
                new Student("b", 19, "上海", "p2"),
                new Student("c", 20, "广州", "p3"));
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("resources/students.obj"))) {
            oos.writeObject(list);
        }
        System.out.println("对象集合写出完成");
    }
}
