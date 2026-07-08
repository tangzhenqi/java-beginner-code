package com.itheima._06_reflect_config;

public class Teacher {
    public Teacher() {
    }

    public void teach() {
        System.out.println("老师在教书！");
    }

    @Override
    public String toString() {
        return "Teacher{}";
    }
}
