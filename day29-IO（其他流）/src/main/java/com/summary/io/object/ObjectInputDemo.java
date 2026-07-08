package com.summary.io.object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 对象反序列化流 ObjectInputStream
 *
 * 构造方法：
 *      public ObjectInputStream(InputStream in)
 *
 * 成员方法：
 *      public Object readObject()       从文件中读取对象
 *
 * 反序列化注意点：
 *      1. 类必须实现 Serializable；
 *      2. serialVersionUID 必须一致，否则抛 InvalidClassException；
 *      3. transient 字段反序列化后为类型默认值（null/0/false）；
 *      4. 想读多个对象时，要么读到 EOFException 为止，
 *         要么用写出时已经包装好的集合一次性读出。
 */
public class ObjectInputDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 1. 读取单个对象
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("resources/student.obj"))) {
            Student s = (Student) ois.readObject();
            // password 因为是 transient，反序列化后为 null
            System.out.println("读出对象：" + s);
        }

        // 2. 读取整个集合
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("resources/students.obj"))) {
            @SuppressWarnings("unchecked")
            List<Student> list = (List<Student>) ois.readObject();
            list.forEach(System.out::println);
        }
    }
}
