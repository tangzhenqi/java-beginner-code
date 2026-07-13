# day39 XML

## 知识点
- XML：可扩展标记语言，常用于**配置文件**和**数据传输**。
- 语法规则：唯一根标签、标签成对、正确嵌套、属性值加引号、大小写敏感。
- XML 解析方式：DOM / SAX；本例用开源库 **DOM4J**（Dom For Java）。
- 核心 API：`SAXReader.read()` → `Document` → `getRootElement()` → `elements()` / `element()` / `attribute()`。

## 目录
- [docs/xml.md](docs/xml.md) —— 学习笔记（含 DTD / schema 约束、图）
- [src/main/resources/students.xml](src/main/resources/students.xml) —— 待解析的 XML
- [Student.java](src/main/java/com/itheima/Student.java) —— 与节点对应的 JavaBean
- [XmlParseDemo.java](src/main/java/com/itheima/XmlParseDemo.java) —— **实战案例：DOM4J 解析 students.xml**

## 运行
```bash
mvn -q compile exec:java -Dexec.mainClass=com.itheima.XmlParseDemo
```
