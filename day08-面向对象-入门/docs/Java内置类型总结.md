# Java 内置类型总结

## 一、类型体系总览

Java 的类型分为两大类：

- **基本类型（Primitive Types）**：8 种，直接存储值
- **引用类型（Reference Types）**：变量存储的是对对象的引用，分为 class、interface、array 三种

---

## 二、八种基本类型

### 整数类型

| 类型    | 字节数 | 位数 | 范围                              | 备注              |
| ------- | ------ | ---- | --------------------------------- | ----------------- |
| `byte`  | 1      | 8    | -128 ~ 127                        |                   |
| `short` | 2      | 16   | -32,768 ~ 32,767                  |                   |
| `int`   | 4      | 32   | 约 -21 亿 ~ 21 亿                 | 默认整数类型      |
| `long`  | 8      | 64   | 约 ±9.2 × 10¹⁸                    | 字面量加 `L` 后缀 |

### 浮点类型

| 类型     | 字节数 | 精度   | 备注                          |
| -------- | ------ | ------ | ----------------------------- |
| `float`  | 4      | 单精度 | 字面量加 `f` 后缀，如 `3.14f` |
| `double` | 8      | 双精度 | 浮点字面量默认类型            |

### 字符类型

| 类型   | 字节数 | 范围             | 备注                     |
| ------ | ------ | ---------------- | ------------------------ |
| `char` | 2      | 0 ~ 65,535（无符号） | 存储一个 UTF-16 编码单元 |

### 布尔类型

| 类型      | 取值              | 备注                       |
| --------- | ----------------- | -------------------------- |
| `boolean` | `true` / `false`  | JVM 规范未规定具体存储大小 |

### 默认值

作为类成员变量时，基本类型有默认值：

- 数值类型：`0`（`float`/`double` 为 `0.0`）
- `char`：`'\u0000'`
- `boolean`：`false`

> 局部变量没有默认值，必须显式初始化。

### 对应的包装类

每种基本类型都有对应的包装类（位于 `java.lang` 包）：

`Byte`、`Short`、`Integer`、`Long`、`Float`、`Double`、`Character`、`Boolean`

包装类是引用类型，可用于泛型和集合，支持自动装箱/拆箱。

---

## 三、引用类型的三种分类

JLS 把引用类型严格分为：**class、interface、array**。变量存储的是对对象的引用（类似指针），而非对象本身。

### 1. Class type（类类型）

由 `class` 关键字定义，变量指向类的实例。

```java
String s = "hello";
Object o = new Object();
Person p = new Person();
```

特殊形式：

- `enum` 是特殊的 class（编译后 `final class XXX extends Enum`）
- `record`（Java 14+）也是特殊的 class

### 2. Interface type（接口类型）

由 `interface` 关键字定义。接口本身不能 `new`，但接口类型的变量可以持有任何实现类的实例（多态基础）。

```java
List<Integer> list = new ArrayList<>();
Runnable r = () -> System.out.println("hi");
```

特殊形式：

- `@interface`（注解）是特殊的 interface（编译后 `interface XXX extends Annotation`）

### 3. Array type（数组类型）

任何以 `[]` 结尾的类型。**数组本身永远是引用类型**，即使元素是基本类型。

```java
int[] a = new int[5];           // 基本类型数组
String[] b = new String[5];     // 引用类型数组
int[][] c = new int[3][3];      // 多维数组（数组的数组）
```

数组类型由 JVM **隐式生成**，没有源代码定义。

```java
int[] arr = new int[3];
System.out.println(arr.getClass().getName()); // 输出 [I
```

> `[I` 是 JVM 内部命名：`[` 表示数组，`I` 是 `int` 的类型描述符。

### 三类对比

| 引用类型  | 来源                          | 能否 new              | 示例                  |
| --------- | ----------------------------- | --------------------- | --------------------- |
| class     | `class` / `enum` / `record`   | 可以（除非 abstract） | `String`、`ArrayList` |
| interface | `interface` / `@interface`    | 不可以                | `List`、`Runnable`    |
| array     | JVM 自动生成                  | 可以（`new T[n]`）    | `int[]`、`String[][]` |

---

## 四、关键概念辨析

### `int[]` 是什么类型？

`int[]` **不是基本类型，是引用类型**（具体为数组类型）。

判断依据：

- 变量存的是堆上数组对象的引用，不是值本身
- 可以赋值为 `null`：`int[] arr = null;` 合法
- 数组对象继承自 `Object`，可调用 `getClass()`、`clone()` 等方法
- 有 `length` 字段，`==` 比较的是引用地址

```java
int a = 5;           // a 直接存 5
int[] arr = {1, 2};  // arr 存指向 {1,2} 对象的引用
```

### 数组算"内置"吗？

- **语言层面**：是内置的。JLS 直接定义，有专门语法（`int[]`、`new int[10]`、`arr[i]`），无需 import
- **类型分类**：不是基本类型，属于引用类型下的 array type

### 内置引用类型

Java 没有"内置引用类型"的正式说法，但 `java.lang` 包下的类无需 import 即可使用，常被视为内置：

`String`、`Object`、`Math`、`System`、`Thread` 等。

其中 `String` 比较特殊——有字面量语法 `"hello"` 和 `+` 拼接运算符的语言级支持，使用起来像内置类型，但本质仍是普通类。

---

## 五、一句话总结

> Java 严格意义上的"内置类型"指 **8 种基本类型**；其他所有类型都是引用类型，包括类、接口和数组三种形式。
