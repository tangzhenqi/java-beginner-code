package com.itheima.day14.access.base;

/**
 * 权限修饰符（access modifier）一览：
 *
 *   修饰符          本类     同包     不同包子类     不同包其它类
 *   private          √        ×           ×              ×
 *   （默认）         √        √           ×              ×
 *   protected        √        √           √              ×
 *   public           √        √           √              √
 *
 *  使用建议：
 *      - 成员变量优先 private（封装），通过 getter/setter 暴露
 *      - 方法默认 public，只在内部辅助时降到 private
 *      - protected 主要给"子类专用的扩展点"
 */
public class Animal {

    private   String  secret    = "private 字段：只能在本类访问";
              String  packageHi = "默认（包私有）字段：同包可访问";
    protected String  family    = "protected 字段：同包 + 子类（含跨包）可访问";
    public    String  open      = "public 字段：所有地方都可访问";

    private   void privateMethod()  { System.out.println(secret); }
              void packageMethod()  { System.out.println(packageHi); }
    protected void protectedMethod(){ System.out.println(family); }
    public    void publicMethod()   { System.out.println(open); }

    /** 本类内部可以调用所有方法 —— 包括自己的 private。 */
    public void showAll() {
        privateMethod();
        packageMethod();
        protectedMethod();
        publicMethod();
    }
}
