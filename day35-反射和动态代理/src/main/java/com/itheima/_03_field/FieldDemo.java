package com.itheima._03_field;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 知识点：反射操作成员变量。
 *
 * Class 类中获取 Field 的 4 个 API：
 *   getFields()             只能拿 public 字段（含父类）
 *   getDeclaredFields()     本类所有字段（含 private），不含父类
 *   getField(name)          按名字拿 public 字段
 *   getDeclaredField(name)  按名字拿任意字段
 *
 * Field 类的核心方法：
 *   void   set(Object obj, Object value)   给 obj 对象的这个字段赋值
 *   Object get(Object obj)                 获取 obj 对象上该字段的值
 *   setAccessible(true)                    暴力反射，访问 private 字段
 */
public class FieldDemo {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("com.itheima._03_field.Person");

        System.out.println("==== 本类所有字段 ====");
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println(Modifier.toString(f.getModifiers())
                    + " " + f.getType().getSimpleName()
                    + " " + f.getName());
        }

        // 拿到 name 字段（private）
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);

        Person p = new Person("zhangsan", 23, "男");

        // 读
        String oldValue = (String) nameField.get(p);
        System.out.println("修改前: " + oldValue);

        // 写
        nameField.set(p, "lisi");
        System.out.println("修改后: " + p);
    }
}
