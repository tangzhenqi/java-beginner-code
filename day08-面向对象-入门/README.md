# day08 面向对象（入门） - 知识归纳

## 知识点总览

day08 是"面向对象"的入门，核心是从"写一堆 main 方法里的代码" 升级为 **"先定义一个类，
再用类生产对象，让对象去执行方法"**。

学完 day08 要能熟练背诵这六个关键词：

| 概念 | 一句话 |
|---|---|
| 类（class） | 描述"一类事物"的蓝图（有什么属性、能干什么） |
| 对象（object） | 按蓝图造出来的"具体一个东西" |
| 成员变量 / 属性 | 类里的"名词"（手机的品牌、价格） |
| 成员方法 / 行为 | 类里的"动词"（打电话、玩游戏） |
| 封装（private + getter/setter） | "把属性藏起来，对外只开方法这扇门" |
| 构造方法 | new 一个对象时自动跑的代码，负责给属性赋值 |

## 包结构（按知识点拆分）

- [com.summary.clazz](src/main/java/com/summary/clazz/) — 第一步：定义类、new 对象、操作属性与方法。
- [com.summary.multi](src/main/java/com/summary/multi/) — 多个对象之间是相互独立的。
- [com.summary.encapsulation](src/main/java/com/summary/encapsulation/) — 封装：private + getter/setter + 在 setter 里做合法性校验。
- [com.summary.thiskeyword](src/main/java/com/summary/thiskeyword/) — `this` 用来区分成员变量与同名局部变量。
- [com.summary.constructor](src/main/java/com/summary/constructor/) — 默认无参构造、自定义构造、"只写全参不写空参"的陷阱、`this()` 复用。
- [com.summary.javabean](src/main/java/com/summary/javabean/) — 标准 JavaBean 模板（也包含 IDEA 快捷键和插件提示）。
- [com.summary.extension](src/main/java/com/summary/extension/) — 拓展：内存图、toString/equals 重写、Lombok 简介。
