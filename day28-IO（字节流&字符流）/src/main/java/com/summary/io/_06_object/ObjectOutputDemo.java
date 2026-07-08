package com.summary.io._06_object;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 序列化：把 Java 对象写入文件。
 *
 *   构造：ObjectOutputStream(OutputStream)
 *   写出：writeObject(Object)
 *
 * 文件内容是二进制的，不可读。
 */
public class ObjectOutputDemo {

    public static final String FILE = "data/student.obj";

    public static void main(String[] args) throws IOException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException {
        Student stu = new Student("zhangsan", 23, "123456");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(stu);
        }
        System.out.println("已序列化 -> " + FILE);
    }
}
