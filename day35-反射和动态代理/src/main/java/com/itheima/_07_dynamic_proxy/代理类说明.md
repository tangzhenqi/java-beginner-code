# 代理类（Proxy）原理与常见用法

## 一、什么是代理

**代理模式（Proxy Pattern）** 是一种结构型设计模式：为某个对象提供一个"替身/中介"，外界通过替身来间接访问真正的对象，从而在**不修改原对象代码**的前提下，控制访问、增强功能。

核心角色有三个：

| 角色 | 说明 |
| --- | --- |
| **抽象主题（Subject）** | 代理类和真实类共同的接口/父类，约定对外提供哪些方法 |
| **真实主题（RealSubject）** | 真正干活、实现核心业务的对象 |
| **代理（Proxy）** | 持有真实对象的引用，对外表现得和真实对象一样，在转发调用的前后插入额外逻辑 |

调用方拿到的是代理，看起来和真实对象没区别，但每次调用都会先经过代理这一层。

```
调用方 ──▶ 代理(Proxy) ──▶ 真实对象(RealSubject)
                │
                └── 前置/后置增强：日志、权限、事务、缓存……
```

---

## 二、为什么要用代理

代理解决的核心问题是：**把和业务无关的通用逻辑（横切关注点）从业务代码里抽离出来，统一管理。**

常见增强点：

| 用途 | 代理做的事 |
| --- | --- |
| 日志记录 | 方法调用前后自动打印入参、返回值、耗时 |
| 权限校验 | 执行业务前先检查登录状态、角色权限 |
| 事务管理 | 方法前开启事务，正常提交、异常回滚 |
| 性能监控 | 统计方法执行时间 |
| 缓存 | 有缓存直接返回，无缓存再调真实方法并缓存结果 |
| 远程调用 | 本地代理把调用透明地转发到远程服务（RPC） |
| 延迟加载 | 真正用到时才创建 / 加载重量级对象 |

如果把这些逻辑写进每个业务方法里，会**大量重复且难以维护**；用代理统一处理，业务类只关注业务本身。这正是 **Spring AOP、声明式事务、MyBatis Mapper** 等框架的底层基础。

---

## 三、代理的两种实现方式

### 1. 静态代理

程序员**手写**一个代理类，编译期就已经存在。

**特点：**
- 代理类和真实类实现同一个接口。
- 代理类内部持有真实对象，在方法里手动调用真实对象并加增强。

**示意（通用写法，非具体业务）：**

```java
// 抽象主题
interface Service {
    void doWork();
}

// 真实主题
class RealService implements Service {
    public void doWork() {
        System.out.println("执行核心业务");
    }
}

// 静态代理：手写，一个方法一个方法地包
class ServiceProxy implements Service {
    private final Service target;          // 持有真实对象
    public ServiceProxy(Service target) {
        this.target = target;
    }
    public void doWork() {
        System.out.println("【前置增强】开启事务/记录日志");
        target.doWork();                   // 转发给真实对象
        System.out.println("【后置增强】提交事务/统计耗时");
    }
}
```

**缺点：**
- 接口每多一个方法，代理类就要跟着改。
- 每个真实类都要写一个对应的代理类，**类爆炸**、重复代码多。

因此实际项目几乎都用**动态代理**。

---

### 2. 动态代理

不用手写代理类，而是在**程序运行时由 JVM 动态生成**。Java 里有两种主流方案：**JDK 动态代理** 和 **CGLIB**。

---

## 四、JDK 动态代理（重点）

JDK 自带，基于 `java.lang.reflect.Proxy` 和 `InvocationHandler`，**要求被代理对象必须实现接口**。

### 核心 API

```java
Object proxy = Proxy.newProxyInstance(
        ClassLoader loader,       // 类加载器
        Class<?>[] interfaces,    // 代理要实现的接口
        InvocationHandler h);     // 调用处理器
```

| 参数 | 作用 |
| --- | --- |
| `ClassLoader` | 用哪个类加载器加载动态生成的代理类，一般用被代理类或当前类的 ClassLoader |
| `Class<?>[] interfaces` | 代理要实现哪些接口，决定了代理对象"长什么样"、能被强转成什么类型 |
| `InvocationHandler` | 调用代理的**任意方法**时，都会统一进入它的 `invoke(...)` 回调 |

### InvocationHandler 接口

```java
public interface InvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
```

| 参数 | 含义 |
| --- | --- |
| `proxy` | 动态生成的代理对象本身（很少直接用，注意别在里面再调它的方法，否则死循环） |
| `method` | 当前被调用的方法对象，可用 `method.getName()`、注解等判断 |
| `args` | 调用该方法时传入的实参数组（无参时为 `null`） |
| 返回值 | 作为该方法的返回结果交还给调用方 |

### 通用用法模板

```java
// 目标对象（实现了某接口 Service）
Service target = new RealService();

Service proxy = (Service) Proxy.newProxyInstance(
        target.getClass().getClassLoader(),
        target.getClass().getInterfaces(),
        (proxyObj, method, args) -> {
            long start = System.currentTimeMillis();
            System.out.println("【前置】调用方法：" + method.getName());

            // 用反射让真实对象执行业务
            Object result = method.invoke(target, args);

            System.out.println("【后置】耗时：" + (System.currentTimeMillis() - start) + "ms");
            return result;
        });

proxy.doWork();   // 实际会进入上面的 invoke，再转发到 target.doWork()
```

### 底层原理

1. 运行期 JDK 动态生成一个类，名字通常是 `com.sun.proxy.$Proxy0`。
2. 这个类实现了你传入的所有接口，因此能被强转成接口类型。
3. 它内部持有你传入的 `InvocationHandler`。
4. 每个接口方法的方法体都被改写成：**把调用转发给 `handler.invoke(this, 方法, 参数)`**。

```
proxy.doWork()
      │
      ▼
$Proxy0.doWork()               ← 运行期动态生成
      │ 转发
      ▼
handler.invoke(proxy, method, args)   ← 你写的增强逻辑
      │ method.invoke(target, args)
      ▼
RealService.doWork()           ← 真实业务
```

> ⚠️ **约束**：JDK 动态代理**只能代理接口**。目标对象必须实现至少一个接口，且只能通过接口类型来使用代理，不能强转成具体实现类。

---

## 五、CGLIB 动态代理（补充）

当目标类**没有实现接口**时，JDK 动态代理无能为力，可用 **CGLIB**（Code Generation Library）。

- 原理：运行期生成目标类的**子类**，重写其中的方法来做增强（基于继承 + 字节码生成）。
- 优点：不要求实现接口，能直接代理普通类。
- 限制：因为靠继承，**不能代理 `final` 类和 `final` / `private` 方法**。

**Spring 的选择策略：** 目标类实现了接口 → 默认用 JDK 动态代理；没有接口 → 用 CGLIB。（可通过配置强制全用 CGLIB）

### JDK 动态代理 vs CGLIB

| 对比项 | JDK 动态代理 | CGLIB |
| --- | --- | --- |
| 依赖 | JDK 自带 | 需引入 CGLIB / 由 Spring 内置 |
| 实现方式 | 实现目标**接口** | 继承目标**类**，生成子类 |
| 前提 | 目标必须有接口 | 目标是可继承的普通类 |
| 限制 | 只能按接口类型使用 | 不能代理 final 类 / final、private 方法 |
| 适用 | 面向接口编程的场景 | 无接口的类 |

---

## 六、静态代理 vs 动态代理

| 对比项 | 静态代理 | 动态代理 |
| --- | --- | --- |
| 代理类产生时机 | 编译前手写 | 运行期动态生成 |
| 代码量 | 每个类/接口都要写，重复多 | 一套处理逻辑通吃 |
| 灵活性 | 差，接口改动要同步改 | 高，接口增删方法无需改代理 |
| 维护性 | 差 | 好 |
| 典型应用 | 教学、简单场景 | Spring AOP、事务、RPC、MyBatis Mapper 等 |

---

## 七、实际应用中的代理

- **Spring AOP**：切面（日志、事务、权限、监控）本质就是动态代理，在目标方法前后织入增强。
- **Spring 声明式事务**：`@Transactional` 由代理在方法前开启事务、后提交或回滚。
- **MyBatis**：Mapper 接口没有实现类，运行期由动态代理生成实现，把方法调用转成 SQL 执行。
- **RPC 框架（Dubbo / Feign）**：本地拿到的是接口代理，调用被透明转发到远程服务。
- **数据库连接池**：代理 `Connection` 的 `close()`，把"关闭"改为"归还连接池"。

---

## 八、小结

- 代理 = **替身/中介**，在不改动真实对象的前提下，对方法调用做前后增强或访问控制。
- 三大角色：**抽象主题、真实主题、代理**。
- 实现分两类：**静态代理**（手写，会类爆炸）和**动态代理**（运行期生成，主流）。
- **JDK 动态代理**：基于接口，`Proxy.newProxyInstance` + `InvocationHandler.invoke`，所有调用汇聚到 `invoke` 里加料再转发。
- **CGLIB**：基于继承生成子类，可代理无接口的类，但不能代理 final。
- 代理是 **Spring AOP、事务、RPC、MyBatis** 等框架能力的底层基石。
