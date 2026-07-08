package com.summary.extend;

/**
 * 拓展3：switch 新语法（JDK 12 引入，JDK 14 正式）
 *
 *   关键改进：
 *     1. 使用箭头 (->) 代替冒号 + break，避免穿透；
 *     2. 可直接作为"表达式"返回值，赋值给变量；
 *     3. 多值匹配可用逗号分隔，如 case 1, 2, 3 -> ...
 *     4. 需要多行时配合 yield 返回值。
 *
 *   注意：此特性需要 JDK 14+ 才能直接使用。本工程默认 JDK 1.8，
 *   下方语法仅以注释形式展示，避免编译报错。
 */
public class SwitchNewSyntaxDemo {
    public static void main(String[] args) {
        // 传统写法
        int number = 1;
        String result;
        switch (number) {
            case 1: result = "一"; break;
            case 2: result = "二"; break;
            case 3: result = "三"; break;
            default: result = "没有这种选项"; break;
        }
        System.out.println(result);

        /* JDK14+ 写法示例（在新版本 JDK 上可启用）：
        String r = switch (number) {
            case 1 -> "一";
            case 2, 3 -> "二或三";
            default -> {
                System.out.println("命中 default");
                yield "其他";
            }
        };
        System.out.println(r);
        */
    }
}
