# 时间 API 学习顺序

按知识点循序渐进的顺序，对 `com.itheima` 下两个日期时间相关子包的 Java 文件进行了重命名，文件名格式为 `_01_xxx`，类名与文件名同步。

## 一、JDK7 日期 API（`_01_jdk7_date`）

| 序号 | 文件名 | 知识点 |
|---|---|---|
| 01 | `_01_DateApi.java` | Date 日期类基础 |
| 02 | `_02_DateCompareDemo.java` | Date 日期比较练习 |
| 03 | `_03_SimpleDateFormatApi.java` | SimpleDateFormat 格式化 |
| 04 | `_04_SimpleDateFormatTransform.java` | 字符串 ↔ Date 解析转换 |
| 05 | `_05_SimpleDateFormatRange.java` | 时间范围判断练习 |
| 06 | `_06_CalendarApi.java` | Calendar 日历类 |

**学习思路**：Date 基础 → Date 比较练习 → SimpleDateFormat 格式化 → 解析转换 → 时间范围练习 → Calendar 日历。

> **提示**：`SimpleDateFormat` 是非线程安全的。JDK 8 之后更推荐用 `java.time` 包的 `DateTimeFormatter` + `LocalDateTime`，它不可变、线程安全，pattern 字母基本通用。见下面 JDK8 部分的 `_07_DateTimeFormatterApi.java`。

## 二、JDK8 日期时间 API（`_02_jdk8_datetime`）

| 序号 | 文件名 | 知识点 |
|---|---|---|
| 01 | `_01_LocalDateApi.java` | LocalDate 日期 |
| 02 | `_02_LocalTimeApi.java` | LocalTime 时间 |
| 03 | `_03_LocalDateTimeApi.java` | LocalDateTime 日期时间 |
| 04 | `_04_ZoneIdApi.java` | ZoneId 时区 |
| 05 | `_05_ZonedDateTimeApi.java` | ZonedDateTime 带时区的日期时间 |
| 06 | `_06_InstantApi.java` | Instant 时间戳 |
| 07 | `_07_DateTimeFormatterApi.java` | DateTimeFormatter 格式化 |
| 08 | `_08_PeriodApi.java` | Period 日期间隔 |
| 09 | `_09_DurationApi.java` | Duration 时间间隔 |
| 10 | `_10_ChronoUnitApi.java` | ChronoUnit 时间单位间隔 |

**学习思路**：先学基础的「日期 → 时间 → 日期时间」，再到「时区相关」，然后「时间戳」和「格式化」，最后是三种计算时间差的方式（Period / Duration / ChronoUnit）。

## 备注

- 所有文件的 `public class` 类名均已改为与新文件名一致，包名未变，可正常编译。
- macOS 自带的 BSD `sed` 不支持 `\b` 单词边界，重命名类名时需用 `class Xxx {` 这类精确匹配。
