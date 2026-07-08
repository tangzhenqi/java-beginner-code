package com.summary.javabean;

/**
 * 知识点 6：标准 JavaBean
 *
 * 一个"标准 JavaBean"要满足：
 *   1. 类用 public 修饰。
 *   2. 所有成员变量 private。
 *   3. 提供 public 空参构造方法。
 *   4. 提供 public 全参构造方法。
 *   5. 每个属性提供一对 getXxx / setXxx。
 *
 * IDEA 帮你生成的快捷键：
 *   Alt + Insert (Mac: Cmd + N)  ->  Constructor / Getter and Setter
 *
 * 插件推荐：
 *   - PTG（Ptg / Auto generate java beans）：一键根据字段生成标准 JavaBean。
 *   - Lombok：用 @Data 注解一行替代所有 getter/setter（拓展见 extension 包）。
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String gender;
    private int age;

    public User() {
    }

    public User(String username, String password, String email, String gender, int age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
