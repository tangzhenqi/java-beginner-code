# day09 面向对象综合训练 - 知识归纳

## 知识点总览

day09 的内容是把 day08 学过的"类与对象"的知识点综合运用起来。核心套路是：

1. **JavaBean 标准类**：私有属性 + 空参/全参构造 + getter/setter（外加可选业务方法）。
2. **对象与对象之间的交互**：`r1.attack(r2)` —— "方法的调用者"对"参数对象"做操作。
3. **对象数组**：用一个数组装一批同类型对象，再做遍历、求和、平均、条件筛选。
4. **对象数组的增删改查（CRUD）**：唯一性判断、扩容、按 id 查找索引、删除（置 null）、修改属性。
5. **Scanner 两套录入体系**：`nextInt/nextDouble/next` 与 `nextLine` 不能混用。

## 包结构（每个包对应一类知识点）

- [com.summary.javabean](src/main/java/com/summary/javabean/) —— JavaBean 标准写法。
- [com.summary.thiskeyword](src/main/java/com/summary/thiskeyword/) —— `this` 关键字的两种典型用法。
- [com.summary.interaction](src/main/java/com/summary/interaction/) —— 对象之间相互调用方法（回合制游戏）。
- [com.summary.scannerusage](src/main/java/com/summary/scannerusage/) —— Scanner 两套体系与混用陷阱。
- [com.summary.objectarray](src/main/java/com/summary/objectarray/) —— 对象数组的基础遍历、统计、平均、筛选。
- [com.summary.crud](src/main/java/com/summary/crud/) —— 对象数组的增、删、改、查、扩容。
- [com.summary.extension](src/main/java/com/summary/extension/) —— 拓展：printf 格式化、System.arraycopy 扩容、Arrays.copyOf 等。
