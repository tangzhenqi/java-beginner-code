package com.itheima;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 知识点 20：递归统计每种扩展名的文件个数
 *
 * 示例输出：
 *   {txt=3, jpg=2, log=1}
 *
 * 实现要点：
 *   - 用 HashMap<String, Integer> 累加
 *   - 没有扩展名的文件归入 "(none)"
 *   - 多点文件如 "abc.tar.gz" 只取最后一段 "gz"（与 day27 原题一致）
 */
public class _10_CountByExtension {
    public static void main(String[] args) {
        File root = new File("target/playground");
        Map<String, Integer> result = countByExt(root);
        System.out.println(result);
    }

    public static Map<String, Integer> countByExt(File src) {
        Map<String, Integer> result = new HashMap<>();
        collect(src, result);
        return result;
    }

    private static void collect(File src, Map<String, Integer> acc) {
        File[] children = src.listFiles();
        if (children == null) return;
        for (File f : children) {
            if (f.isFile()) {
                String name = f.getName();
                int dot = name.lastIndexOf('.');
                String ext = (dot > 0 && dot < name.length() - 1)
                        ? name.substring(dot + 1)
                        : "(none)";
                acc.merge(ext, 1, Integer::sum); // 比 if(containsKey) 写法简洁
            } else {
                collect(f, acc);
            }
        }
    }
}
