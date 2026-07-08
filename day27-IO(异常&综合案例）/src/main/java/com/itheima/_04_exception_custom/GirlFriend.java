package com.itheima._04_exception_custom;

/**
 * 用 setter 做参数校验，校验失败抛出自定义异常。
 * 这样调用者一旦写出非法数据，能立刻看到清晰的错误信息。
 */
public class GirlFriend {
    private String name;
    private int age;

    public GirlFriend() {}

    public GirlFriend(String name, int age) {
        setName(name);
        setAge(age);
    }

    public void setName(String name) {
        if (name == null) {
            throw new NameFormatException("姓名不能为 null");
        }
        int len = name.length();
        if (len < 3 || len > 10) {
            throw new NameFormatException("姓名长度应为 3~10，实际: " + len);
        }
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 18 || age > 40) {
            throw new AgeOutOfBoundsException("年龄应为 18~40，实际: " + age);
        }
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return "GirlFriend{name='" + name + "', age=" + age + "}";
    }
}
