package com.itheima._02_constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * 知识点：反射操作构造方法。
 *
 * Class 类中获取构造方法的 4 个 API：
 *   getConstructors()           只能拿 public 构造
 *   getDeclaredConstructors()   能拿所有构造（包括 private）
 *   getConstructor(参数类型...)        按参数列表拿 public 构造
 *   getDeclaredConstructor(参数类型...) 按参数列表拿任意构造
 *
 * Constructor 类常用方法：
 *   newInstance(实参...)        调用该构造方法创建对象
 *   setAccessible(true)         "暴力反射"：临时取消权限校验，让 private 构造也能调用
 */
public class ConstructorDemo {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("com.itheima._02_constructor.Person");

        // 1) 拿到所有构造（包含 private）
        System.out.println("==== 所有构造方法 ====");
        Constructor<?>[] cons = clazz.getDeclaredConstructors();
        for (Constructor<?> c : cons) {
            System.out.println(Modifier.toString(c.getModifiers()) + " " + c);
        }

        // 2) 拿指定的 private 构造，并通过暴力反射创建对象
        System.out.println("==== 用 private 构造创建对象 ====");
        Constructor<?> con = clazz.getDeclaredConstructor(String.class, int.class);
        con.setAccessible(true);   // 关键：取消权限检查
        Object p = con.newInstance("张三", 23);
        System.out.println(p);
    }
}
