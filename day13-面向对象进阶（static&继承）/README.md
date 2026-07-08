# day13 知识点归纳与拓展（static & 继承）

本模块是对 `day13-面向对象进阶（static&继承）` 的提炼与扩展，按知识点拆分为
不同的子包，每个子包都含一个 `Demo.java` 主程序，单独运行即可观察效果。

## 包结构与知识点

| 包 | 知识点 |
| --- | --- |
| `a01_static_basic` | static 成员变量：共享数据；类名访问 vs 对象访问 |
| `a02_static_util` | 工具类范式：私有化构造方法 + 静态方法 |
| `a03_static_features` | static 的访问特点；静态代码块 / 实例代码块（拓展） |
| `a04_extends_basic` | 继承基本语法、单继承、多层继承、子类只能访问父类非私有成员 |
| `a05_extends_members` | 成员变量访问的"就近原则"；this 与 super 的区别 |
| `a06_extends_constructor` | 子类构造默认 super()；显式 super(参数)；this(参数) |
| `a07_method_override` | 方法重写 `@Override`；super 调用父类方法 |
| `a08_practice_employee` | 综合练习：员工 / 管理者 / 厨师 |
| `a09_extends_advanced` | 拓展：`protected`、`final` 类、`Object` 作为根父类、`equals` 重写 |

## 拓展点（day13 之外的延伸）

1. **静态代码块** 与 **实例代码块** 的执行时机
2. **`protected` 修饰符**：父子类跨包访问场景
3. **`final` 类与 `final` 方法**：阻断继承 / 重写
4. **`Object` 是所有类的隐式父类**：`toString` / `equals` 重写
5. **构造方法执行顺序**：父类 → 子类，结合代码块整体顺序的总结
