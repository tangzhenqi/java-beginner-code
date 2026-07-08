package com.itheima._07_dynamic_proxy;

public class BigStar implements Star {
    private String name;

    public BigStar() {
    }

    public BigStar(String name) {
        this.name = name;
    }

    @Override
    public String sing(String songName) {
        System.out.println(name + " 正在唱《" + songName + "》");
        return "谢谢";
    }

    @Override
    public void dance() {
        System.out.println(name + " 正在跳舞");
    }
}
