package com.summary;

/**
 * 知识点3：Java 中的三种注释
 *
 *   1. 单行注释      //  内容
 *   2. 多行注释      斜杠星  内容  星斜杠
 *   3. 文档注释      斜杠双星 内容 星斜杠   可由 javadoc 工具生成 API 文档
 *
 * 注意：
 *   - 注释不会被编译进 .class 字节码
 *   - 多行/文档注释不能嵌套
 *   - 文档注释常用于类、方法、字段的对外说明
 */
public class CommentDemo {
    public static void main(String[] args) {
        // 这是单行注释

        /*
         * 这是多行注释
         * 可以写多行说明
         */

        System.out.println("注释演示");
    }
}
