package com.summary.io.commonsio;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Apache Commons-IO 工具类（commons-io:commons-io:2.11.0）
 *
 * 常用方法：
 *      FileUtils
 *          copyFile(File src, File dest)
 *          copyDirectory(File srcDir, File destDir)
 *          copyDirectoryToDirectory(File srcDir, File destDir)
 *          deleteDirectory(File dir)
 *          cleanDirectory(File dir)
 *          readFileToString(File file, Charset cs)
 *          readLines(File file, Charset cs)
 *          writeStringToFile(File file, String data, Charset cs)
 *
 *      IOUtils
 *          copy(InputStream in, OutputStream out)        小文件 (int 返回字节数，最多 2G)
 *          copyLarge(InputStream in, OutputStream out)   大文件 (long 返回字节数)
 *          toString(InputStream in, Charset cs)
 *          readLines(Reader in)
 *
 * 价值：用一行替代 try-with-resources + 缓冲区循环。生产代码常用。
 */
public class CommonsIoDemo {
    public static void main(String[] args) throws IOException {
        File src = new File("resources/a.txt");
        File dest = new File("resources/a_copy_by_commons.txt");

        // 1. 一行复制文件
        FileUtils.copyFile(src, dest);

        // 2. 读文件为字符串
        String content = FileUtils.readFileToString(src, StandardCharsets.UTF_8);
        System.out.println("文件内容：\n" + content);

        // 3. 按行读
        List<String> lines = FileUtils.readLines(src, StandardCharsets.UTF_8);
        lines.forEach(System.out::println);

        // 4. 一行写入字符串
        FileUtils.writeStringToFile(
                new File("resources/written_by_commons.txt"),
                "由 Commons-IO 写入",
                StandardCharsets.UTF_8);

        // 5. IOUtils 流之间一次性拷贝
        try (FileInputStream in = new FileInputStream(src);
             FileOutputStream out = new FileOutputStream("resources/by_ioutils.txt")) {
            int bytes = IOUtils.copy(in, out);
            System.out.println("IOUtils.copy 复制了 " + bytes + " 字节");
        }
    }
}
