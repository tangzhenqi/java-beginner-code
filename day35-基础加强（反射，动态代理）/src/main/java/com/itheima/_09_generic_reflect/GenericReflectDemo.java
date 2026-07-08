package com.itheima._09_generic_reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 拓展：反射读取泛型实参。
 *
 * Java 的泛型在运行期会被"类型擦除"：List&lt;String&gt; 编译完就只剩 List。
 * 但是 —— 写在 **字段声明 / 方法签名 / 父类继承** 里的泛型实参会保留在
 * 字节码的 Signature 属性里，反射通过 getGenericType / getGenericReturnType
 * 拿到的就是这部分信息（ParameterizedType）。
 *
 * 这是 Jackson、Gson、Spring 等框架"知道你要的是 List&lt;User&gt; 而不是
 * 单个 User"的关键。
 */
public class GenericReflectDemo {

    public static void main(String[] args) throws Exception {

        Class<UserRepository> clazz = UserRepository.class;

        System.out.println("==== 字段上的泛型 ====");
        for (Field f : clazz.getDeclaredFields()) {
            Type type = f.getGenericType();
            System.out.println(f.getName() + " 的声明类型: " + type);
            printActualTypes(type, "    ");
        }

        System.out.println();
        System.out.println("==== 方法返回值上的泛型 ====");
        Method m = clazz.getDeclaredMethod("findUsers", int.class, int.class);
        Type retType = m.getGenericReturnType();
        System.out.println(m.getName() + " 的返回类型: " + retType);
        printActualTypes(retType, "    ");
    }

    /** 如果是参数化类型，就把它的"<...>"里的实参一个个打印出来。 */
    private static void printActualTypes(Type type, String indent) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            for (Type arg : pt.getActualTypeArguments()) {
                System.out.println(indent + "实参: " + arg.getTypeName());
            }
        }
    }
}
