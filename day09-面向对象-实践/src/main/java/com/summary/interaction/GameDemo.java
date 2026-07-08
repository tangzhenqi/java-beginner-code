package com.summary.interaction;

/**
 * 回合制游戏：两个 Role 互相攻击，直到一方血量 == 0。
 *
 * 这种"双方轮流执行 + 任意一方达到终止条件就退出"的模式，
 * 是 while(true) + break 的典型场景。
 */
public class GameDemo {
    public static void main(String[] args) {
        Role r1 = new Role("乔峰", 100);
        Role r2 = new Role("鸠摩智", 100);

        while (true) {
            r1.attack(r2);
            if (r2.getBlood() == 0) {
                System.out.println(r1.getName() + " K.O 了 " + r2.getName());
                break;
            }

            r2.attack(r1);
            if (r1.getBlood() == 0) {
                System.out.println(r2.getName() + " K.O 了 " + r1.getName());
                break;
            }
        }
    }
}
