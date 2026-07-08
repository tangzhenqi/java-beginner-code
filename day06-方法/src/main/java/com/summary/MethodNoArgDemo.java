package com.summary;

/**
 * 知识点1：方法的最简单格式（无参数、无返回值）
 *
 *   格式：
 *     public static void 方法名() {
 *         方法体;
 *     }
 *
 *   调用：方法名();
 *
 *   为什么需要方法？
 *     1. 提高复用性：一段功能写一次，多处调用；
 *     2. 提高可读性：用方法名表达意图，main 方法不会变成"大杂烩"；
 *     3. 便于维护：修改逻辑只改一处。
 *
 *   命名规范：小驼峰命名法（printGFInfo、getSum），见名知意。
 */
public class MethodNoArgDemo {
    public static void main(String[] args) {
        // 调用方法
        printGFInfo();
        System.out.println("---");
        printGFInfo(); // 复用：再调一次完全没有重复代码的负担
    }

    public static void printGFInfo() {
        System.out.println("小惠惠");
        System.out.println("萌妹子");
        System.out.println("18 岁");
    }
}
