package com.summary.encapsulation;

/**
 * 知识点 3：封装（Encapsulation）
 *
 *   思想：把对象的"属性"藏起来（private），不让外面直接 . 来 . 去，
 *        而是通过 getter / setter "门"来访问。
 *
 *   好处：
 *     - 安全：可以在 setter 里加合法性校验（如年龄必须 18 ~ 50），脏数据进不来。
 *     - 解耦：内部字段名 / 类型怎么改，对外的方法签名不变，使用方不受影响。
 *
 *   规范：
 *     - 属性一律 private。
 *     - 给每个 private 字段配一对 public 的 getXxx / setXxx。
 *     - 布尔值的 get 习惯叫 isXxx。
 */
public class GirlFriend {
    private String name;
    private int age;
    private String gender;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * setter 里加合法性校验：年龄必须在 18 ~ 50 之间，否则给出提示并保持原值不变。
     */
    public void setAge(int age) {
        if (age >= 18 && age <= 50) {
            this.age = age;
        } else {
            System.out.println("非法年龄：" + age);
        }
    }

    public int getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void sleep() {
        System.out.println(name + " 在睡觉");
    }

    public void eat() {
        System.out.println(name + " 在吃饭");
    }
}
