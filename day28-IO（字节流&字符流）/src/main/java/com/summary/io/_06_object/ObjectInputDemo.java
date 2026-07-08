package com.summary.io._06_object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 反序列化：从文件读出 Java 对象。
 *
 *   构造：ObjectInputStream(InputStream)
 *   读取：Object readObject()  (注意：可能抛 ClassNotFoundException)
 *
 * 关注 transient 字段 password 反序列化后为 null。
 */
public class ObjectInputDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new java.io.File("data").mkdirs();
        // 依赖 ObjectOutputDemo 生成的 student.obj，缺则补
        if (!new java.io.File(ObjectOutputDemo.FILE).exists()) {
            ObjectOutputDemo.run();
        }
        run();
    }

    public static void run() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ObjectOutputDemo.FILE))) {
            Student stu = (Student) ois.readObject();
            System.out.println("反序列化结果: " + stu);
            System.out.println("transient 字段 password: " + stu.getPassword());
        }
    }
}
