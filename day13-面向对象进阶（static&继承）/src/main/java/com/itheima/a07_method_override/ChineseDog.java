package com.itheima.a07_method_override;

/**
 * 完全覆盖式重写：不需要父类的实现。
 */
public class ChineseDog extends Dog {

    @Override
    public void eat() {
        System.out.println("中华田园犬吃剩饭");
    }
}
