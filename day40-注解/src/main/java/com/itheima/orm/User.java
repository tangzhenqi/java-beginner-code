package com.itheima.orm;

/**
 * 用注解描述“类 -> 表、字段 -> 列”的映射关系。
 * 没有注解的字段（如 remark）不会出现在生成的建表语句里。
 */
@DBTable("t_user")
public class User {

    @DBColumn(value = "id", type = "int")
    private int id;

    @DBColumn("user_name")           // 省略 type，用默认 varchar(255)
    private String name;

    @DBColumn(value = "age", type = "int")
    private int age;

    // 没加 @DBColumn，不会映射成列
    private String remark;
}
