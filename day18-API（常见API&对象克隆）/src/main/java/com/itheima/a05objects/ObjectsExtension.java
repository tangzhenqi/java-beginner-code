package com.itheima.a05objects;

import java.util.Objects;

/**
 * 知识点五：Objects —— 拓展 API
 * <p>
 *   Objects.requireNonNull(obj)              obj 为 null 时抛 NullPointerException
 *   Objects.requireNonNull(obj, "message")   抛出时带自定义信息（常用于参数校验）
 *   Objects.toString(obj)                    obj 为 null 时返回 "null"
 *   Objects.toString(obj, "default")         obj 为 null 时返回 "default"
 *   Objects.hash(values...)                  生成多字段的 hashCode，重写 hashCode 时常用
 *   Objects.hashCode(obj)                    null 安全的 hashCode（null 返回 0）
 */
public class ObjectsExtension {
    public static void main(String[] args) {
        // 1. requireNonNull：方法入口处的参数校验
        try {
            register(null);
        } catch (NullPointerException e) {
            System.out.println("捕获到: " + e.getMessage());
        }

        // 2. toString 的两种重载
        System.out.println(Objects.toString(null));               // "null"
        System.out.println(Objects.toString(null, "未知"));        // "未知"
        System.out.println(Objects.toString(123));                // "123"

        // 3. hash —— 重写 hashCode 标准写法：return Objects.hash(field1, field2, ...);
        System.out.println(Objects.hash("zhangsan", 23));

        // 4. hashCode null 安全
        System.out.println(Objects.hashCode(null));               // 0
    }

    private static void register(String name) {
        // 进入业务前先校验必填参数
        Objects.requireNonNull(name, "name 不能为 null");
        System.out.println("注册: " + name);
    }
}
