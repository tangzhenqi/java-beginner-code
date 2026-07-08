package com.summary;

/**
 * 知识点1：if 语句的三种格式
 *
 *   格式一：单分支
 *     if (关系表达式) {
 *         语句体;
 *     }
 *
 *   格式二：双分支
 *     if (关系表达式) {
 *         语句体1;
 *     } else {
 *         语句体2;
 *     }
 *
 *   格式三：多分支（用来处理多种条件 / 范围判断）
 *     if (关系表达式1) {
 *         语句体1;
 *     } else if (关系表达式2) {
 *         语句体2;
 *     } else {
 *         语句体n;
 *     }
 *
 *   小括号中表达式的结果必须是 boolean 类型。
 */
public class IfDemo {
    public static void main(String[] args) {
        // 格式一：女婿酒量大于 2 斤，老丈人给出回应
        int wine = 3;
        if (wine > 2) {
            System.out.println("小伙子，不错哟！！");
        }

        // 格式二：判断奇偶
        int num = 7;
        if (num % 2 == 0) {
            System.out.println(num + " 是偶数");
        } else {
            System.out.println(num + " 是奇数");
        }

        // 格式三：成绩等级（范围判断的典型用法）
        int score = 88;
        if (score >= 90 && score <= 100) {
            System.out.println("优秀");
        } else if (score >= 75) {
            System.out.println("良好");
        } else if (score >= 60) {
            System.out.println("及格");
        } else {
            System.out.println("不及格");
        }
    }
}
