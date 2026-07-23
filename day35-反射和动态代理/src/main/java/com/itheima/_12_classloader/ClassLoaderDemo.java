package com.itheima._12_classloader;

import java.io.File;

/**
 * 演示：同名类被不同类加载器加载后，得到的是【互不相等】的 Class，
 * 且它们的实例互相不能强转（抛 ClassCastException）。
 *
 * 结论：JVM 判断"两个类是不是同一个类"，不只看全限定名，
 *       还要看【加载它的类加载器】。名字相同 + 加载器不同 = 两个类。
 */
public class ClassLoaderDemo {

    private static final String TARGET = "com.itheima._12_classloader.Sample";

    public static void main(String[] args) throws Exception {

        // 自动定位编译产物根目录（IDE / mvn 下通常是 target/classes）
        String classesDir = new File(
                ClassLoaderDemo.class.getProtectionDomain()
                        .getCodeSource().getLocation().toURI()).getPath();
        System.out.println("字节码目录: " + classesDir);
        System.out.println();

        // 两个独立的自定义加载器，指向同一个目录、加载同一个类
        DirClassLoader loaderA = new DirClassLoader(classesDir, TARGET);
        DirClassLoader loaderB = new DirClassLoader(classesDir, TARGET);

        Class<?> classA = loaderA.loadClass(TARGET);
        Class<?> classB = loaderB.loadClass(TARGET);

        // 作为对照：直接用应用加载器加载的那份（正常 import 得到的）
        Class<?> classApp = Sample.class;

        System.out.println("---- 三份 Class 的身份 ----");
        printClass("loaderA 加载", classA);
        printClass("loaderB 加载", classB);
        printClass("应用加载器  ", classApp);
        System.out.println();

        System.out.println("---- 名字都一样吗？ ----");
        System.out.println("classA.getName() = " + classA.getName());
        System.out.println("classB.getName() = " + classB.getName());
        System.out.println("三者 getName() 相同: "
                + (classA.getName().equals(classB.getName())
                && classB.getName().equals(classApp.getName())));
        System.out.println();

        System.out.println("---- 但它们是同一个 Class 吗？ ----");
        System.out.println("classA == classB    : " + (classA == classB));
        System.out.println("classA == classApp  : " + (classA == classApp));
        System.out.println("classA.equals(classB): " + classA.equals(classB));
        System.out.println();

        // 各自 new 一个实例
        Object objA = classA.getDeclaredConstructor().newInstance();
        Object objB = classB.getDeclaredConstructor().newInstance();

        System.out.println("---- 实例能互相强转吗？ ----");
        // objA 的真实类型是 loaderA 加载的 Sample，
        // 而这里的 Sample（应用加载器的那份）是另一个类 -> 强转失败
        try {
            Sample s = (Sample) objA;
            System.out.println("强转成功（不该发生）: " + s);
        } catch (ClassCastException e) {
            System.out.println("(Sample) objA 抛出 ClassCastException：");
            System.out.println("    " + e.getMessage());
        }

        // objA 与 objB 之间同理，用反射调用避免编译期类型绑定
        System.out.println();
        System.out.println("---- 用反射调用各自的 hello() ----");
        System.out.println(classA.getMethod("hello").invoke(objA));
        System.out.println(classB.getMethod("hello").invoke(objB));
    }

    private static void printClass(String tag, Class<?> c) {
        System.out.printf("%s: identityHashCode=%-10d classLoader=%s%n",
                tag, System.identityHashCode(c), c.getClassLoader());
    }
}
