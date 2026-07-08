package com.itheima._05_save_object;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 实战：把"任意对象"的所有字段名和值写到本地文件。
 *
 * 体现反射的核心威力：方法 saveObject(Object) 并不知道对象的具体类型，
 * 但仍然能够把它"打印"成一份字段列表。Jackson、Gson、ORM 框架的底层
 * 也都依赖这种"由 Object 出发拿 Class，再拿 Field"的能力。
 */
public class SaveObjectDemo {

    public static void main(String[] args) throws Exception {
        Student s = new Student("小A", 23, '女', 167.5, "睡觉");
        Teacher t = new Teacher("播妞", 10000);

        saveObject(s, "object_dump.txt");
        // 同一个方法可以处理完全不同的类型 —— 这就是反射的"通用性"
        saveObject(t, "object_dump.txt");

        System.out.println("已写出到 object_dump.txt（位于运行时工作目录）");
    }

    /**
     * 把对象的所有字段名=字段值，追加写到指定文件。
     */
    public static void saveObject(Object obj, String filePath) throws IOException, IllegalAccessException {
        Class<?> clazz = obj.getClass();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write("---- " + clazz.getSimpleName() + " ----");
            bw.newLine();
            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                bw.write(f.getName() + " = " + f.get(obj));
                bw.newLine();
            }
        }
    }
}
