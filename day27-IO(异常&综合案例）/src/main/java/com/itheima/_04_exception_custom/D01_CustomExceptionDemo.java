package com.itheima._04_exception_custom;

import java.util.Scanner;

/**
 * 知识点 9：自定义异常 + 健壮的输入校验
 *
 * 需求：
 *   键盘录入姓名(3~10位) 与 年龄(18~40)，直到合法为止。
 *   出现 NumberFormatException(年龄不是数字) / NameFormatException / AgeOutOfBoundsException
 *   都要重新录入，绝不能 crash。
 *
 * 设计要点：
 *   - 数据校验放进 setter 而不是分散在调用处 → 错一处，处处遭殃 vs 错一处，处处都防得住。
 *   - 用自定义异常代替 boolean 返回值 → 错误信息更具体。
 */
public class D01_CustomExceptionDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GirlFriend gf = new GirlFriend();

        while (true) {
            try {
                System.out.println("请输入姓名 (3~10 字符):");
                gf.setName(sc.nextLine());

                System.out.println("请输入年龄 (18~40):");
                int age = Integer.parseInt(sc.nextLine()); // 可能 NumberFormatException
                gf.setAge(age);
                break; // 全部合法才跳出
            } catch (NumberFormatException e) {
                System.out.println("[重试] 年龄必须是整数: " + e.getMessage());
            } catch (NameFormatException | AgeOutOfBoundsException e) {
                System.out.println("[重试] " + e.getMessage());
            }
        }

        System.out.println("录入成功: " + gf);
    }
}
