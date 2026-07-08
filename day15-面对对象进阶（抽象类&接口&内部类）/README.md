# day15-summary：抽象类 & 接口 & 内部类

本模块是 day15 的知识点归纳与拓展，独立的 Maven 项目，编译版本 JDK 1.8（与仓库其它模块一致）。

## 目录结构

```
com.summary.day15
├── abstractclass/      抽象类
│   ├── AbstractBasicsDemo.java        基础语法（抽象类 / 抽象方法 / 抽象类不可实例化）
│   ├── AbstractRulesDemo.java         六大注意事项（构造方法、子类规则、抽象类继承抽象类等）
│   └── EmployeeCaseDemo.java          综合案例（员工 → 经理 / 程序员）
├── interfacekw/        接口
│   ├── InterfaceBasicsDemo.java       基础语法 + 接口多态
│   ├── InterfaceMembersDemo.java      成员变量（常量）、无构造、抽象方法默认修饰符
│   ├── InterfaceRelationshipDemo.java 类与接口（多实现）、接口与接口（多继承）
│   ├── DefaultMethodDemo.java         JDK 8 - 默认方法（default）
│   ├── StaticMethodDemo.java          JDK 8 - 静态方法
│   ├── PrivateMethodDemo.java         JDK 9 - 私有方法（拓展）
│   ├── AdapterPatternDemo.java        适配器模式
│   └── CoachAthleteCaseDemo.java      综合案例：教练 & 运动员 体系
└── innerclass/         内部类
    ├── MemberInnerClassDemo.java      成员内部类 + 创建方式
    ├── OuterThisDemo.java             Outer.this 解决重名问题
    ├── StaticInnerClassDemo.java      静态内部类
    ├── LocalInnerClassDemo.java       局部内部类
    └── AnonymousInnerClassDemo.java   匿名内部类（重点）
```

## 知识点总览

### 一、抽象类（abstract class）
1. 抽象方法只有方法签名没有方法体，用 `abstract` 修饰。
2. 含有抽象方法的类必须是抽象类；抽象类**不能直接实例化**。
3. 抽象类**可以**有构造方法（供子类 `super()` 调用，给成员变量赋值）。
4. 抽象类可以有成员变量、普通方法、静态方法、代码块等。
5. 子类继承抽象类后：要么重写所有抽象方法，要么自己也声明为 `abstract`。
6. 抽象类可以继承抽象类，此时不必重写父类的抽象方法。

### 二、接口（interface）
1. 用 `interface` 定义，用 `implements` 实现。
2. 成员变量：**只能是常量**，默认修饰符 `public static final`。
3. **没有构造方法**。
4. 成员方法：默认 `public abstract`（JDK 7 及以前）。
5. 类与接口：**单继承 + 多实现**。
6. 接口与接口：**多继承**（一个接口可以 `extends` 多个父接口）。
7. JDK 8 拓展：
   - **默认方法** `public default`：可被实现类直接使用，也可重写（重写时去掉 `default`）。
   - **静态方法** `public static`：只能用**接口名**调用，实现类不能继承。
8. JDK 9 拓展：**私有方法** `private` / `private static`，给默认方法 / 静态方法做内部复用。
9. **接口多态**与**适配器模式**：接口方法很多但只关心其中几个时，用抽象适配器类提供空实现，业务类只重写需要的方法。

### 三、内部类（inner class）
| 分类 | 位置 | 关键点 |
| --- | --- | --- |
| 成员内部类 | 类的成员位置 | 可被 `private/default/protected/public/static` 修饰；可访问外部类一切成员（含 private） |
| 静态内部类 | 类的成员位置，含 `static` | 只能直接访问外部类的静态成员；创建方式 `Outer.Inner i = new Outer.Inner();` |
| 局部内部类 | 方法内部 | 只能在所在方法内使用；可访问外部类成员和所在方法的局部变量 |
| 匿名内部类 | 任意能写表达式的位置 | 没有名字，本质是某个类/接口的子类对象；最常作为方法实参传入（接口多态） |

- 成员内部类访问外部同名成员：`外部类名.this.成员名`，如 `Outer.this.a`。
- 成员内部类的创建：`Outer.Inner oi = new Outer().new Inner();`
- 静态内部类的创建：`Outer.Inner oi = new Outer.Inner();`
- 匿名内部类的格式：
  ```java
  new 类名或接口名() {
      重写的方法;
  };
  ```

## 运行方式
```bash
# 在该模块目录下
mvn -q compile
# 运行某个 Demo（示例）
mvn -q exec:java -Dexec.mainClass="com.summary.day15.abstractclass.AbstractBasicsDemo"
```
或直接在 IDE 中右键各 `*Demo` 类的 `main` 方法运行。
