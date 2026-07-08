package com.itheima._01_getclass;

/**
 * 知识点：获取 Class 字节码对象的三种方式。
 *
 * 1. Class.forName("全类名")
 *    最常用。读取配置文件、按字符串名加载类时使用。
 *
 * 2. 类名.class
 *    属于"类字面量"。常用于把 Class 当参数传入。
 *
 * 3. 对象.getClass()
 *    已经有对象时使用，多用于运行期判断"我到底是哪个子类"。
 *
 * 同一个类无论用哪种方式获得，得到的都是 JVM 中唯一的同一个 Class 对象，
 * 所以下面三者用 == 比较都是 true。
 *
 * 关于类型：变量声明成 Class<?>（通配符，“某个未知类型的 Class”），
 * 是为了让三种方式的返回类型统一好比较（Class.forName 编译期不知道具体类，
 * 只能是 Class<?>）。若明确知道是 Student，可写成 Class<Student>，
 * 好处是 getDeclaredConstructor().newInstance() 能直接返回 Student，省一次强转。
 */
public class GetClassDemo {

    public static void main(String[] args) throws ClassNotFoundException {

        // 方式一：Class.forName
        Class<?> clazz1 = Class.forName("com.itheima._01_getclass.Student");

        // 方式二：类名.class
        // Student.class 叫“类字面量(class literal)” 是一个表达式.
        // 求值结果就是 JVM 中代表 Student 这个类的 Class 对象(类的“说明书/图纸”)
        // 其精确类型是 Class<Student>, 这里向上赋给 Class<?>
        // 注意 class 是关键字, 后面不加括号; 编译期就能确定，不会抛异常
        Class<?> clazz2 = Student.class;

        // 方式三：对象.getClass()
        Student stu = new Student();
        Class<?> clazz3 = stu.getClass();

        System.out.println("clazz1 == clazz2 ? " + (clazz1 == clazz2));
        System.out.println("clazz2 == clazz3 ? " + (clazz2 == clazz3));

        // 补充：Class 对象常用方法
        System.out.println("全类名: " + clazz1.getName());
        System.out.println("简单类名: " + clazz1.getSimpleName());
        System.out.println("包名: " + clazz1.getPackageName());
    }
}
