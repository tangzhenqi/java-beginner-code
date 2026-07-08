package com.summary.interaction;

import java.util.Random;

/**
 * 知识点 3：对象与对象之间的交互
 *
 * 当一个对象的行为需要"作用到另一个对象"上时，
 * 把"另一个对象"作为方法参数传进来即可：
 *
 *     Role r1 = new Role(...);
 *     Role r2 = new Role(...);
 *     r1.attack(r2);   // 调用者 r1 攻击 参数 r2
 *
 * 在 attack 方法里：
 *     this  -> 调用者 r1
 *     role  -> 参数   r2
 *
 * Random 的用法：
 *     Random r = new Random();
 *     int x = r.nextInt(20) + 1;  // 1 ~ 20
 */
public class Role {
    private String name;
    private int blood;

    public Role() {
    }

    public Role(String name, int blood) {
        this.name = name;
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    /**
     * "this 攻击 role"。
     * 注意：要修改的是 role 的血量，所以调的是 role.setBlood(...)，而不是 this.setBlood(...)。
     */
    public void attack(Role role) {
        int hurt = new Random().nextInt(20) + 1;            // 1 ~ 20 的伤害
        int remain = Math.max(role.getBlood() - hurt, 0);   // 血量保护，不让它低于 0
        role.setBlood(remain);

        System.out.println(this.name + " 打了 " + role.name +
                " 一下，造成 " + hurt + " 点伤害，" + role.name +
                " 剩余血量 " + remain);
    }
}
