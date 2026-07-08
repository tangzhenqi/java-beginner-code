package com.summary.io.commonsio;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Hutool 工具包（cn.hutool:hutool-all:5.8.10）
 *
 * Hutool 把 Java IO 进一步简化为静态方法，并且自动管理流的关闭，常用方法：
 *      FileUtil.copy(src, dest, isOverride)
 *      FileUtil.readUtf8String(File)
 *      FileUtil.readUtf8Lines(File)
 *      FileUtil.writeUtf8String(content, File)
 *      FileUtil.touch(File)
 *      FileUtil.del(File)
 *      IoUtil.read(InputStream, Charset)
 *      IoUtil.readUtf8(InputStream)
 *
 * 与 Commons-IO 的关系：功能基本重合，国内项目更常见 Hutool。
 * 选择哪一个看团队习惯即可。
 */
public class HutoolDemo {
    public static void main(String[] args) throws IOException {
        // 1. 一句话复制
        FileUtil.copy("resources/a.txt", "resources/a_copy_by_hutool.txt", true);

        // 2. 读为字符串
        String content = FileUtil.readUtf8String("resources/a.txt");
        System.out.println("内容：\n" + content);

        // 3. 按行读
        List<String> lines = FileUtil.readUtf8Lines("resources/a.txt");
        lines.forEach(System.out::println);

        // 4. 一句话写
        FileUtil.writeUtf8String("由 Hutool 写入", "resources/written_by_hutool.txt");

        // 5. IoUtil 配合流使用
        try (FileInputStream fis = new FileInputStream("resources/a.txt")) {
            String text = IoUtil.read(fis, CharsetUtil.CHARSET_UTF_8);
            System.out.println("IoUtil.read：" + text);
        }
    }
}
