package com.itheima.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注在字段上：指定该字段对应的列名与列类型。
 * <p>
 * type 有默认值，使用时可只写列名：@DBColumn("user_name")
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBColumn {
    String value();                       // 列名

    String type() default "varchar(255)"; // 列类型，默认 varchar(255)
}
