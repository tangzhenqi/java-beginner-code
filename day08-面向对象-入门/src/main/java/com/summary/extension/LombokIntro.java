package com.summary.extension;

/**
 * 拓展 3：Lombok 简介（写代码时再用）
 *
 *   Lombok 是一个注解处理器，能在"编译时"帮你生成 getter / setter / 构造 / toString / equals 等。
 *
 *   引入 Maven 依赖（仅示意，不需要现在加）：
 *
 *   <dependency>
 *       <groupId>org.projectlombok</groupId>
 *       <artifactId>lombok</artifactId>
 *       <version>1.18.30</version>
 *       <scope>provided</scope>
 *   </dependency>
 *
 *   常用注解：
 *     @Data                  一次性生成 getter/setter/toString/equals/hashCode + 必须字段构造
 *     @Getter / @Setter      只生成 getter / 只生成 setter
 *     @NoArgsConstructor     生成空参构造
 *     @AllArgsConstructor    生成全参构造
 *     @Builder               链式构造：User u = User.builder().name("x").age(1).build();
 *
 *   等价于：
 *
 *     @Data
 *     @NoArgsConstructor
 *     @AllArgsConstructor
 *     public class User {
 *         private String name;
 *         private int age;
 *     }
 *
 *   注意：使用 Lombok 需要 IDE 装 Lombok 插件，且开启 "Enable annotation processing"。
 *         本课程阶段先掌握"手写 JavaBean"，等熟练了再引入 Lombok 提效。
 */
public class LombokIntro {
}
