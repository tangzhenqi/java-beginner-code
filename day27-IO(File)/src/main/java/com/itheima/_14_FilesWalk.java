package com.itheima;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 知识点 24（拓展）：Files.walk —— 用 Stream API 递归遍历
 *
 * 用 Files.walk 重写 _10_case_recursive 里的几个案例：
 *   - 找扩展名:  filter(...).toList()
 *   - 统计大小: mapToLong(Files::size).sum()
 *   - 按扩展名计数: Collectors.groupingBy(..., counting())
 *
 * 注意：
 *   - Files.walk 返回的 Stream 持有目录句柄，必须 try-with-resources 关闭。
 *   - 中途的 IO 异常会被包装为 UncheckedIOException 抛出，按需 catch。
 */
public class _14_FilesWalk {
    public static void main(String[] args) throws IOException {
        Path root = Paths.get("target/playground");
        if (!Files.exists(root)) {
            System.out.println("先运行其它案例生成测试数据。");
            return;
        }

        // 1) 找出所有 .txt 文件
        try (Stream<Path> s = Files.walk(root)) {
            List<Path> txt = s.filter(Files::isRegularFile)
                              .filter(p -> p.toString().endsWith(".txt"))
                              .collect(Collectors.toList());
            System.out.println("txt 文件: ");
            txt.forEach(System.out::println);
        }

        // 2) 统计总大小
        try (Stream<Path> s = Files.walk(root)) {
            long bytes = s.filter(Files::isRegularFile)
                          .mapToLong(p -> {
                              try { return Files.size(p); }
                              catch (IOException e) { return 0L; }
                          })
                          .sum();
            System.out.println("总大小 = " + bytes + " B");
        }

        // 3) 按扩展名分组计数
        try (Stream<Path> s = Files.walk(root)) {
            Map<String, Long> grouped = s.filter(Files::isRegularFile)
                    .collect(Collectors.groupingBy(
                            p -> {
                                String n = p.getFileName().toString();
                                int dot = n.lastIndexOf('.');
                                return (dot > 0 && dot < n.length() - 1)
                                        ? n.substring(dot + 1)
                                        : "(none)";
                            },
                            Collectors.counting()));
            System.out.println("按扩展名计数: " + grouped);
        }
    }
}
