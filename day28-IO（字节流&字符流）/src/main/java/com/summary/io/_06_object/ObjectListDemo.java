package com.summary.io._06_object;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 拓展：序列化一个集合（注意 ArrayList 自身实现了 Serializable）。
 *
 * 这是工作中最常见的做法 —— 把整个 List 一次写入 / 读取，
 * 比 "在文件里循环 writeObject 多次再循环 readObject" 更简洁稳健。
 */
public class ObjectListDemo {

    private static final String FILE = "data/student_list.obj";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new java.io.File("data").mkdirs();
        run();
    }

    public static void run() throws IOException, ClassNotFoundException {
        List<Student> list = new ArrayList<>(Arrays.asList(
                new Student("Alice", 20, "p1"),
                new Student("Bob", 22, "p2"),
                new Student("Carol", 25, "p3")
        ));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(list);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            @SuppressWarnings("unchecked")
            List<Student> back = (List<Student>) ois.readObject();
            back.forEach(System.out::println);
        }
    }
}
