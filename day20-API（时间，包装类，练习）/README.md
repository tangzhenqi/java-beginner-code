# day20 知识点归纳与拓展

本模块对 day20 的 **时间API**、**包装类**、**练习** 进行整理，并在原内容基础上做了拓展。

## 目录结构

```
src/main/java/com/itheima/
├── _01_jdk7_date/        JDK7 旧版时间 API
│   ├── DateApi.java                 Date 构造、setTime、getTime
│   ├── DateCompareDemo.java         比较两个时间先后
│   ├── SimpleDateFormatApi.java     格式化与解析
│   ├── SimpleDateFormatTransform.java 字符串日期格式互转
│   ├── SimpleDateFormatRange.java   秒杀活动时间范围判断
│   └── CalendarApi.java             Calendar 抽象类、字段常量、查表法
├── _02_jdk8_datetime/    JDK8 新版时间 API
│   ├── ZoneIdApi.java               时区
│   ├── InstantApi.java              时间戳
│   ├── ZonedDateTimeApi.java        带时区日期时间
│   ├── DateTimeFormatterApi.java    新格式化器
│   ├── LocalDateApi.java            年月日
│   ├── LocalTimeApi.java            时分秒
│   ├── LocalDateTimeApi.java        年月日时分秒
│   ├── PeriodApi.java               日期间隔（年月日）
│   ├── DurationApi.java             时间间隔（时分秒）
│   └── ChronoUnitApi.java           万能单位间隔
├── _03_wrapper/          包装类
│   ├── IntegerCreateDemo.java       Integer 的获取方式
│   ├── IntegerCacheDemo.java        Integer 缓存池 (-128~127)
│   ├── AutoBoxUnboxDemo.java        自动装箱、自动拆箱
│   ├── IntegerStaticApi.java        进制转换、parseInt
│   └── WrapperOverview.java         拓展：8 种包装类总览
├── _04_extend/           拓展知识点
│   ├── ImmutableDemo.java           JDK8 时间对象的不可变性
│   ├── ThreadSafeFormatterDemo.java SimpleDateFormat 线程安全问题
│   ├── TemporalAdjusterDemo.java    时间矫正器（本月第一天、下周一等）
│   ├── ZoneConversionDemo.java      跨时区时间换算
│   └── TimestampUtilDemo.java       时间戳常用工具方法
└── _05_practice/         练习
    ├── PracticeSumList.java         键盘录入累加（自动装箱）
    ├── PracticeMyParseInt.java      自实现 parseInt
    ├── PracticeMyToBinary.java      自实现 toBinaryString
    ├── PracticeAliveDays.java       计算活了多少天
    └── PracticeLeapYear.java        判断闰年
```

每个文件聚焦一个独立的知识点，main 方法可直接运行验证。
