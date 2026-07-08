package com.itheima.day14.codeblock;

public class Child extends Parent {

    static {
        System.out.println("Child  - 静态代码块");
    }

    {
        System.out.println("Child  - 构造代码块（实例初始化）");
    }

    public Child() {
        // super() 默认存在，会先去走 Parent 的实例初始化 + 构造方法
        System.out.println("Child  - 构造方法");
    }
}
