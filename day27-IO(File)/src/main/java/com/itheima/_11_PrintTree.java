package com.itheima;

import java.io.File;

/**
 * 知识点 21（拓展）：打印目录树
 *
 * 类似 Unix 的 tree 命令：
 *   playground
 *   ├── a.txt
 *   ├── sub
 *   │   └── c.txt
 *   └── b.log
 *
 * 实现要点：用一个深度参数控制缩进；用最后一项标志位画 "└──" / "├──"。
 */
public class _11_PrintTree {
    public static void main(String[] args) {
        File root = new File("target/playground");
        System.out.println(root.getName());
        printTree(root, "");
    }

    public static void printTree(File dir, String prefix) {
        File[] children = dir.listFiles();
        if (children == null) return;
        for (int i = 0; i < children.length; i++) {
            File f = children[i];
            boolean last = (i == children.length - 1);
            System.out.println(prefix + (last ? "└── " : "├── ") + f.getName());
            if (f.isDirectory()) {
                printTree(f, prefix + (last ? "    " : "│   "));
            }
        }
    }
}
