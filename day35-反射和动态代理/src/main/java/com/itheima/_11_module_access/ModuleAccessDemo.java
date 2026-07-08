package com.itheima._11_module_access;

import java.lang.reflect.Field;

/**
 * 知识点：模块系统（JPMS，Java 9+）对反射的拦截。
 *
 * 结论先行：setAccessible(true) 并不是"无脑放行"。
 *   - 翻【自己的类】：你的代码在 classpath 上，属于"无名模块(unnamed module)"，
 *     无名模块默认对自己 opens，所以暴力反射成功。
 *   - 翻【别的模块的 private 成员】：目标包必须被 opens 出来，否则即使
 *     setAccessible(true) 也会抛 InaccessibleObjectException。
 *
 * 这里用 java.lang.Integer 演示：java.base 模块虽然 exports java.lang，
 * 但并没有 opens java.lang，所以对它的 private 字段做深度反射会被拦截。
 *
 * 运行环境：JDK 9+（本项目为 JDK 11）。
 */
public class ModuleAccessDemo {

    public static void main(String[] args) {

        System.out.println("==== ① 反射自己的类（无名模块，opens 给自己）====");
        try {
            Field secret = Account.class.getDeclaredField("balance");
            secret.setAccessible(true);            // 成功：翻自己的东西无所谓
            Account acc = new Account(100);
            System.out.println("暴力读取 private balance = " + secret.get(acc));
            secret.set(acc, 999999);
            System.out.println("暴力篡改后 balance      = " + secret.get(acc));
            System.out.println(">> setAccessible 成功，封装被突破\n");
        } catch (Exception e) {
            System.out.println(">> 意外失败: " + e + "\n");
        }

        System.out.println("==== ② 反射 java.base 里的 Integer（未 opens java.lang）====");
        try {
            // Integer 内部缓存了 -128~127 的实例，value 是 private final
            Field valueField = Integer.class.getDeclaredField("value");
            valueField.setAccessible(true);        // ← 就在这一行被模块系统拦下
            System.out.println(">> setAccessible 竟然成功了？（不该走到这）");
        } catch (java.lang.reflect.InaccessibleObjectException e) {
            System.out.println(">> 被模块系统拦截，抛出 InaccessibleObjectException：");
            System.out.println("   " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">> 其他异常: " + e);
        }
    }
}
