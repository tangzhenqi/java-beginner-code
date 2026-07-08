# day35 反射与动态代理 —— 总结与拓展

本工程是对 day35 知识点的归纳整理，并在原有基础上做了若干拓展。

## 知识地图

| 模块 | 主题 | 关键 API |
| --- | --- | --- |
| _01_getclass | 获取 Class 对象的三种方式 | `Class.forName / .class / getClass()` |
| _02_constructor | 反射操作构造方法 | `Constructor.newInstance` / `setAccessible` |
| _03_field | 反射操作成员变量 | `Field.get / set` |
| _04_method | 反射操作成员方法 | `Method.invoke` |
| _05_save_object | 实战：把任意对象的字段写到本地文件 | `Field[] getDeclaredFields` |
| _06_reflect_config | 反射 + 配置文件，动态创建对象并调用方法 | `Properties` + 反射 |
| _07_dynamic_proxy | JDK 动态代理基础（大明星例子） | `Proxy.newProxyInstance` |
| _08_proxy_advanced | **拓展**：用动态代理实现日志、参数校验、性能监控 | `InvocationHandler` lambda |
| _09_generic_reflect | **拓展**：反射获取字段/方法的泛型实参 | `ParameterizedType` |
| _10_annotation_reflect | **拓展**：反射读取自定义注解（测试框架雏形） | `@Retention(RUNTIME)` + `getAnnotation` |

## 运行方式

在每个示例的 `Demo`/`Test` 文件中都包含 `main` 方法，可直接运行。

工程结构遵循标准 Maven 布局：

```
day35-反射与动态代理-总结拓展/
├── pom.xml
└── src/main/
    ├── java/com/itheima/_01_getclass/...
    └── resources/prop.properties
```
