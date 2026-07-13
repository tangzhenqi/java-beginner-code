package com.itheima.mytest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解：模拟 JUnit 的 @Test
 * <p>
 * 元注解说明：
 * - @Target(METHOD)     只能写在方法上
 * - @Retention(RUNTIME) 运行阶段仍然保留，这样反射才能读到它（关键！）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest {
}
