package com.summary;

/**
 * 知识点7：switch 与 if 的使用场景对比
 *
 *   if 的第三种格式：擅长"范围判断"，如成绩区间、分段函数。
 *   switch：擅长"有限枚举"，把若干固定值一一列出。
 *
 *   核心区别：
 *     1. switch 只能做"相等"判断，无法做范围、大小比较；
 *     2. if 可以表达任意 boolean 条件，几乎任何 switch 都能改写成 if。
 *
 *   性能小提示：当 case 数量较多且为连续/常量数据，switch 经常会被编译为
 *   tableswitch/lookupswitch 字节码，比一长串 if-else if 略快——但对初学者
 *   而言，应优先考虑可读性。
 */
public class SwitchVsIfDemo {
    public static void main(String[] args) {
        // 场景 A：分段范围 —— 应该用 if
        int score = 100;
        if (score >= 90 && score <= 100) {
            System.out.println("送自行车");
        } else if (score >= 80) {
            System.out.println("送笔记本");
        } else {
            System.out.println("没奖励");
        }

        // 场景 B：枚举固定取值 —— 应该用 switch
        int weekday = 5;
        switch (weekday) {
            case 1: System.out.println("星期一"); break;
            case 2: System.out.println("星期二"); break;
            case 3: System.out.println("星期三"); break;
            case 4: System.out.println("星期四"); break;
            case 5: System.out.println("星期五"); break;
            case 6: System.out.println("星期六"); break;
            case 7: System.out.println("星期日"); break;
            default: System.out.println("非法星期"); break;
        }
    }
}
