# Class.forName 拿的是 .class 文件，还是内存中的类？

## 一、简短回答

`Class.forName(className)` 返回的是 **JVM 方法区（元空间）里那个已经加载好的 `Class` 对象**——也就是"被加载到内存中的类"。

但为了得到这个内存中的 `Class` 对象，它可能需要**先去读 `.class` 文件**。

- `.class` 文件 = 原材料
- `Class` 对象 = 成品（`Class.forName` 交给你的永远是成品）

## 二、完整过程

`Class.forName("com.itheima.xxx.Student")` 内部大致做两件事：

```
                    ┌─ 类已经加载过? ─┐
Class.forName  ──►  │                │
                    是              否
                    │                │
             直接返回内存中     类加载器去 classpath
             的 Class 对象      找 Student.class 文件
                                     │
                                读字节 → 校验 → 解析
                                     │
                                在方法区生成 Class 对象
                                     │
                                返回这个 Class 对象
```

- **类之前没被加载过** → 类加载器现场去 classpath 读 `Student.class`（硬盘字节），加载进内存，生成 `Class` 对象再返回。
- **类已经被加载过**（每个类只加载一次） → 直接返回内存里现成的 `Class` 对象，**不再读文件**。

> 无论哪种情况，最终交到手里的永远是内存中的 `Class` 对象，而不是文件本身。

## 三、容易忽略的细节：会触发类初始化

`Class.forName(className)` 默认还会**初始化**这个类（执行静态代码块 `static {}` 和静态变量赋值）。

这就是老式 JDBC 写 `Class.forName("com.mysql.jdbc.Driver")` 的原因——目的不是拿返回值，而是**借加载过程触发 Driver 的静态代码块去注册驱动**。

对比 `.class` 字面量：

```java
Student.class                 // 只加载，不触发初始化（不执行 static 块）
Class.forName("...Student")   // 加载 + 初始化（执行 static 块）
```

如果不想触发初始化，用三参数版本：

```java
Class.forName(className, false, classLoader);  // 第二个参数 false = 不初始化
```

## 四、对应类加载的完整生命周期

```
加载(Loading) → 链接(验证/准备/解析) → 初始化(Initialization)
  ↑ 读 .class 文件                        ↑ 执行 static 块
```

- `.class` 文件在最开始的**加载**阶段被读取。
- `Class.forName` 返回的 `Class` 对象是走完加载 + 链接的产物，默认还帮你走到了初始化。

## 五、一句话总结

**`Class.forName` 拿到并返回的是"内存中的 `Class` 对象"；`.class` 文件只是它在类还没加载时，让类加载器去读取的原始字节来源。已加载过的类不会再读文件，直接返回内存里的那个对象。**
