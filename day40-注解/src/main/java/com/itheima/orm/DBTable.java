package com.itheima.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注在类上：指定该实体对应的数据库表名。
 * <p>
 * 只有一个属性且名为 value，使用时可省略属性名：@DBTable("t_user")
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    String value();
}
