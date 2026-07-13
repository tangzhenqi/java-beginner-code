package com.itheima.orm;

import java.lang.reflect.Field;
import java.util.StringJoiner;

/**
 * 实战案例二：注解 + 反射自动生成建表 SQL（简化版 ORM 思路）
 * <p>
 * MyBatis / JPA 这类框架就是靠“实体类上的注解 + 反射”把对象和数据库表关联起来的。
 * 这里演示最核心的一小步：读实体类上的 @DBTable / @DBColumn，拼出 CREATE TABLE 语句。
 */
public class CreateTableSqlGenerator {

    public static String generate(Class<?> entityClass) {
        // 1. 读类上的 @DBTable 拿表名
        if (!entityClass.isAnnotationPresent(DBTable.class)) {
            throw new IllegalArgumentException(entityClass.getName() + " 没有 @DBTable 注解");
        }
        String tableName = entityClass.getAnnotation(DBTable.class).value();

        // 2. 遍历字段，读 @DBColumn 拿列名和列类型
        StringJoiner columns = new StringJoiner(",\n  ");
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(DBColumn.class)) {
                DBColumn column = field.getAnnotation(DBColumn.class);
                columns.add(column.value() + " " + column.type());
            }
        }

        // 3. 拼出建表语句
        return "CREATE TABLE " + tableName + " (\n  " + columns + "\n);";
    }

    public static void main(String[] args) {
        String sql = generate(User.class);
        System.out.println("根据 User 类的注解自动生成的建表语句：\n");
        System.out.println(sql);
    }
}
