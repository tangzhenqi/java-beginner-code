package com.summary.compare;

import java.util.Scanner;

/**
 * 典型案例：用户登录，最多 3 次机会，否则锁定账号。
 *
 *   - 用一个 for 循环控制 3 次机会。
 *   - 用户名密码都对：登录成功，break。
 *   - 错了：i == 2 时锁定；否则提示剩余次数 (2-i)。
 */
public class LoginDemo {
    public static void main(String[] args) {
        String rightUsername = "zhangsan";
        String rightPassword = "123456";

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名：");
            String username = sc.next();
            System.out.println("请输入密码：");
            String password = sc.next();

            if (username.equals(rightUsername) && password.equals(rightPassword)) {
                System.out.println("用户登录成功");
                break;
            }

            if (i == 2) {
                System.out.println("账号 " + username + " 被锁定，请联系客服");
            } else {
                System.out.println("用户名或密码有误，您还剩 " + (2 - i) + " 次机会");
            }
        }
    }
}
