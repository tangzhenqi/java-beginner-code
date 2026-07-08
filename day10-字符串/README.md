# day10 字符串 - 知识归纳

## 知识点总览

day10 的核心是三个类：`String`、`StringBuilder`、`StringJoiner`。
重点是搞清楚它们各自的"使用场景"和"性能差别"。

| 类 | 是否可变 | 典型用途 |
|---|---|---|
| `String` | **不可变** | 任何场合都能用，但频繁拼接性能差 |
| `StringBuilder` | 可变 | 大量字符串拼接、反转 |
| `StringJoiner` | 可变 | 带分隔符 / 前后缀的拼接（如打印 `[a, b, c]`） |

## 包结构

- [com.summary.creation](src/main/java/com/summary/creation/) —— String 的 4 种创建方式 + 常量池原理。
- [com.summary.compare](src/main/java/com/summary/compare/) —— `==` vs `equals` vs `equalsIgnoreCase`。
- [com.summary.traversal](src/main/java/com/summary/traversal/) —— `length()`/`charAt()` 遍历，统计大小写/数字。
- [com.summary.extract](src/main/java/com/summary/extract/) —— `substring`、手机号脱敏、身份证解析。
- [com.summary.replace](src/main/java/com/summary/replace/) —— `replace` 与敏感词库。
- [com.summary.stringbuilder](src/main/java/com/summary/stringbuilder/) —— StringBuilder 性能、链式、反转。
- [com.summary.stringjoiner](src/main/java/com/summary/stringjoiner/) —— StringJoiner 用法。
- [com.summary.practice](src/main/java/com/summary/practice/) —— 综合案例：金额转中文大写、对称串。
- [com.summary.extension](src/main/java/com/summary/extension/) —— 拓展：split、join、trim、format、JDK11+ 的 strip/repeat。
