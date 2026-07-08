package com.itheima._04_method;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * 知识点：反射操作成员方法。
 *
 * Class 类中获取 Method 的 4 个 API：
 *   getMethods()                     所有 public 方法（含父类，比如 Object 的方法）
 *   getDeclaredMethods()             本类全部方法（含 private），不含父类
 *   getMethod(name, 参数类型...)        按签名拿 public 方法
 *   getDeclaredMethod(name, 参数类型...) 按签名拿任意方法
 *
 * Method 核心方法：
 *   Object invoke(Object obj, Object... args)
 *      参数一：用哪个对象去调；如果是静态方法，传 null 即可
 *      参数二：调用方法的实参
 *      返回值：方法本身的返回值；如果方法 void，则返回 null
 */
public class MethodDemo {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("com.itheima._04_method.Person");

        // 获取重载方法之一: eat(String)
        Method m = clazz.getDeclaredMethod("eat", String.class);

        System.out.println("修饰符: " + Modifier.toString(m.getModifiers()));
        System.out.println("方法名: " + m.getName());
        System.out.println("返回值类型: " + m.getReturnType().getSimpleName());

        System.out.println("---- 形参 ----");
        for (Parameter p : m.getParameters()) {
            System.out.println(p.getType().getSimpleName() + " " + p.getName());
        }

        System.out.println("---- 声明抛出的异常 ----");
        for (Class<?> ex : m.getExceptionTypes()) {
            System.out.println(ex.getName());
        }

        // 通过 invoke 调用 private 方法
        m.setAccessible(true);
        Person target = new Person();
        Object ret = m.invoke(target, "汉堡包");
        System.out.println("返回值: " + ret);
    }
}
