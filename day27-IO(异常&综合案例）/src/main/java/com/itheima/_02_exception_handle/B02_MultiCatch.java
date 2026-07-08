package com.itheima._02_exception_handle;

/**
 * 知识点 4：多异常捕获
 *
 *   规则：
 *     1) 多个 catch 之间互斥按顺序匹配；只会进入"第一个匹配成功"的那个 catch。
 *     2) 子类异常的 catch 必须在父类异常 catch 之前，否则编译错误（unreachable）。
 *     3) JDK 7 起，允许使用 | 在一个 catch 块中处理多个互不存在继承关系的异常 (multi-catch)。
 */
public class B02_MultiCatch {
    public static void main(String[] args) {
        try {
            run("abc");
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
            // JDK7 多异常合并捕获：注意此处的 e 是 effectively final，不能被赋值
            System.out.println("数值/越界类异常：" + e);
        } catch (NullPointerException e) {
            System.out.println("空指针异常：" + e);
        } catch (Exception e) {
            // 父类放最后，作为兜底
            System.out.println("其它异常：" + e);
        }
    }

    private static void run(String type) {
        switch (type) {
            case "arr":
                int[] a = new int[1];
                System.out.println(a[5]);
                break;
            case "div":
                System.out.println(1 / 0);
                break;
            case "npe":
                String s = null;
                s.length();
                break;
            default:
                throw new IllegalStateException("未知 type: " + type);
        }
    }
}
