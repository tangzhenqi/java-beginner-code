package com.itheima._10_annotation_reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 拓展：用"反射 + 注解"实现一个极简版的测试运行器（JUnit 雏形）。
 *
 * 思路：
 *   1) 拿到目标类的 Class
 *   2) 遍历所有方法，挑出 isAnnotationPresent(MyTest.class) 的
 *   3) 用 newInstance + invoke 依次执行；捕获异常并标记 FAIL/PASS
 */
public class TestRunner {

    public static void main(String[] args) throws Exception {
        run(CalculatorTests.class);
    }

    public static void run(Class<?> testClass) throws Exception {
        int pass = 0, fail = 0;

        Object instance = testClass.getDeclaredConstructor().newInstance();

        for (Method m : testClass.getDeclaredMethods()) {
            if (!m.isAnnotationPresent(MyTest.class)) {
                continue;
            }
            MyTest meta = m.getAnnotation(MyTest.class);
            String name = m.getName() + "（" + meta.value() + "）";
            try {
                m.setAccessible(true);
                m.invoke(instance);
                System.out.println("[PASS] " + name);
                pass++;
            } catch (InvocationTargetException e) {
                // 真实抛出的业务异常被反射封了一层，要剥开看真正原因
                Throwable cause = e.getCause();
                System.out.println("[FAIL] " + name + " -> "
                        + cause.getClass().getSimpleName() + ": " + cause.getMessage());
                fail++;
            }
        }

        System.out.println();
        System.out.println("总计: " + (pass + fail) + " 通过: " + pass + " 失败: " + fail);
    }
}
