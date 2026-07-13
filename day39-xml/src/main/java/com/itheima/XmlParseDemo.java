package com.itheima;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 实战案例：用 DOM4J 把 students.xml 解析成 List&lt;Student&gt;
 * <p>
 * 这是 XML 最典型的用途——把配置/数据文件读进来变成对象。
 * 解析步骤：SAXReader 读文档 -> 拿根元素 -> 遍历子元素 -> 取属性/文本 -> 封装成对象。
 */
public class XmlParseDemo {

    public List<Student> parse() throws Exception {
        List<Student> students = new ArrayList<>();

        // 1. 创建解析器
        SAXReader reader = new SAXReader();

        // 2. 读取 classpath 下的 students.xml，得到 Document
        //    （用类加载器拿输入流，保证打成 jar 后依然能读到）
        InputStream is = XmlParseDemo.class.getClassLoader().getResourceAsStream("students.xml");
        Document document = reader.read(is);

        // 3. 获取根元素 <students>
        Element root = document.getRootElement();

        // 4. 获取所有名为 student 的子元素
        List<Element> studentNodes = root.elements("student");

        // 5. 逐个解析
        for (Element node : studentNodes) {
            Student student = new Student();

            // 5.1 读取属性 id：<student id="1">
            Attribute idAttr = node.attribute("id");
            student.setId(Integer.parseInt(idAttr.getValue()));

            // 5.2 读取子标签文本：<name>张三</name>
            student.setName(node.element("name").getText());
            student.setAge(Integer.parseInt(node.element("age").getText()));
            student.setMajor(node.element("major").getText());

            students.add(student);
        }

        is.close();
        return students;
    }

    public static void main(String[] args) throws Exception {
        List<Student> students = new XmlParseDemo().parse();
        System.out.println("从 students.xml 解析到 " + students.size() + " 名学生：");
        for (Student s : students) {
            System.out.println("  " + s);
        }
    }
}
