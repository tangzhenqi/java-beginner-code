package com.itheima.day14;

import com.itheima.day14.access.AccessDemo;
import com.itheima.day14.codeblock.CodeBlockDemo;
import com.itheima.day14.codeblock.InheritanceOrderDemo;
import com.itheima.day14.finalkw.FinalBasicsDemo;
import com.itheima.day14.finalkw.FinalReferenceDemo;
import com.itheima.day14.pkg.PackageDemo;
import com.itheima.day14.polymorphism.PolymorphismMemberDemo;
import com.itheima.day14.polymorphism.PolymorphismRegisterDemo;
import com.itheima.day14.polymorphism.PolymorphismCastDemo;
import com.itheima.day14.polymorphism.KeepPetDemo;

/**
 * day14 知识点总入口：依次运行各个知识点的演示。
 * 也可以在 IDE 中单独运行各个 Demo 的 main 方法。
 */
public class App {
    public static void main(String[] args) {
        section("1. 多态 - 同名方法的多种形态（register 案例）");
        PolymorphismRegisterDemo.main(args);

        section("2. 多态 - 成员变量与成员方法的访问规则");
        PolymorphismMemberDemo.main(args);

        section("3. 多态 - 向下转型与 instanceof 模式匹配");
        PolymorphismCastDemo.main(args);

        section("4. 多态 - 综合：饲养员喂养不同动物");
        KeepPetDemo.main(args);

        section("5. 包 - 同名类通过全限定名区分");
        PackageDemo.main(args);

        section("6. final - 基础：修饰变量、方法、类");
        FinalBasicsDemo.main(args);

        section("7. final - 修饰引用类型/数组");
        FinalReferenceDemo.main(args);

        section("8. 权限修饰符 - 同包/不同包的可见性");
        AccessDemo.main(args);

        section("9. 代码块 - 构造代码块与静态代码块");
        CodeBlockDemo.main(args);

        section("10. 代码块拓展 - 继承体系下的执行顺序");
        InheritanceOrderDemo.main(args);
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("==================== " + title + " ====================");
    }
}
