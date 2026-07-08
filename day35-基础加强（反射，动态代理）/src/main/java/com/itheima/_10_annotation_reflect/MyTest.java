package com.itheima._10_annotation_reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义"测试方法"注解。
 *
 * @Retention(RUNTIME) —— 关键！只有 RUNTIME 保留的注解，反射才读得到。
 *                        如果是 SOURCE 或 CLASS，运行期会被丢弃。
 * @Target(METHOD)     —— 这个注解只能贴在方法上。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyTest {

    /** 给测试一个描述，便于运行日志输出。 */
    String value() default "";
}
