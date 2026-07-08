package com.summary.javabean;

public class JavaBeanDemo {
    public static void main(String[] args) {
        // 空参 + setter
        User u1 = new User();
        u1.setUsername("zhangsan");
        u1.setPassword("123456");
        u1.setEmail("z@itcast.cn");
        u1.setGender("男");
        u1.setAge(20);

        // 全参构造
        User u2 = new User("lisi", "654321", "l@itcast.cn", "女", 22);

        print(u1);
        print(u2);
    }

    private static void print(User u) {
        System.out.println(u.getUsername() + ", " + u.getPassword() + ", " +
                u.getEmail() + ", " + u.getGender() + ", " + u.getAge());
    }
}
