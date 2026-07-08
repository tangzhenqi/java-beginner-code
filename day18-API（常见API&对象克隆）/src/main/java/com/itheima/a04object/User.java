package com.itheima.a04object;

import java.util.Arrays;

/**
 * 演示 clone 用的 JavaBean。
 * <p>
 * Cloneable 是"标记型接口"——里面没有抽象方法，
 *   实现它仅表示：当前类的对象允许被克隆。
 *   未实现却调用 super.clone() 会抛 CloneNotSupportedException。
 */
public class User implements Cloneable {
    private int id;
    private String username;
    private String password;
    private int[] data;

    public User() {}
    public User(int id, String username, String password, int[] data) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.data = data;
    }

    public int[] getData() { return data; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', password='" + password
                + "', data=" + Arrays.toString(data) + "}";
    }

    /**
     * 默认 super.clone() 是【浅克隆】：
     *   基本类型字段复制值；
     *   引用类型字段复制地址值，新旧对象共享同一个目标。
     * <p>
     * 想要【深克隆】：需要手动把引用类型字段也复制一份。
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        // 1. 先让父类完成浅克隆
        User u = (User) super.clone();
        // 2. 把数组也复制一份，切断对原数组的引用
        u.data = data.clone();        // 数组对象自带 clone，等价于 Arrays.copyOf
        return u;
    }
}
