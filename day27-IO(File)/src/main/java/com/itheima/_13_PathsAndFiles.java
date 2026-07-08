package com.itheima;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 知识点 23（拓展，JDK7+/JDK11+）：Path + Files —— File 的现代替代
 *
 * 对比 java.io.File：
 *   - Path 跨平台路径抽象，链式 API (resolve / relativize / normalize) 更强
 *   - Files 提供大量静态方法：exists / createDirectories / readString / writeString / walk ...
 *   - 异常更明确：抛出 IOException 或子类 NoSuchFileException 等，而不是返回 boolean
 *   - 性能/可组合性都优于 File，新代码推荐使用 Path/Files
 *
 * JDK11 新增便利方法：
 *   Files.readString(Path) / Files.writeString(Path, CharSequence) —— 一行搞定整个文件
 */
public class _13_PathsAndFiles {
    public static void main(String[] args) throws IOException {
        Path base = Paths.get("target", "playground", "nio");
        Files.createDirectories(base); // 等价于 mkdirs()，已存在不抛异常

        Path file = base.resolve("hello.txt"); // 路径拼接
        Files.writeString(file, "Hello, NIO.2!\n第二行。"); // JDK11

        // 读
        String text = Files.readString(file);
        System.out.println("readString -> \n" + text);

        // 一些查询：
        System.out.println("exists       : " + Files.exists(file));
        System.out.println("isRegular    : " + Files.isRegularFile(file));
        System.out.println("size(bytes)  : " + Files.size(file));
        System.out.println("absolutePath : " + file.toAbsolutePath());

        // 路径运算
        Path other = Paths.get("target/playground/nio/sub/abc.txt");
        Files.createDirectories(other.getParent());
        Files.writeString(other, "child");
        System.out.println("relativize   : " + base.relativize(other));
    }
}
