package com.itheima.regex.validate;

/**
 * 知识点 7（拓展）：密码强度校验
 * <p>
 * 演示正向先行断言 (?=...) 当作 “约束条件” 的常见用法 ——
 * 它本身不消耗字符，只是要求 “在当前位置往后看，存在某种模式”。
 * 多个 (?=...) 串联，等价于 “同时满足以下所有条件”。
 * <p>
 * 规则：8 ~ 16 位，且同时包含 1 个大写字母、1 个小写字母、1 个数字。
 */
public class B04_PasswordStrength {
    public static void main(String[] args) {

        // 拆解：
        //   ^                           从串首
        //   (?=.*[a-z])                 后面某处至少出现 1 个小写
        //   (?=.*[A-Z])                 后面某处至少出现 1 个大写
        //   (?=.*\\d)                   后面某处至少出现 1 个数字
        //   [a-zA-Z\\d]{8,16}           整体只能由字母数字组成，长度 8~16
        //   $
        String strong = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$";

        String[] cases = {
                "Abcdef12",     // true
                "abcdef12",     // false（无大写）
                "ABCDEF12",     // false（无小写）
                "Abcdefgh",     // false（无数字）
                "A1b2",         // false（太短）
                "AbcDef12345678901234" // false（太长）
        };
        for (String c : cases) {
            System.out.println(c + " : " + c.matches(strong));
        }

        // 拓展：再加一条 “必须包含 1 个特殊符号”
        String stronger = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[\\w!@#$%^&*]{8,16}$";
        System.out.println("Abcdef1!".matches(stronger)); // true
        System.out.println("Abcdef12".matches(stronger)); // false（无特殊符号）
    }
}
