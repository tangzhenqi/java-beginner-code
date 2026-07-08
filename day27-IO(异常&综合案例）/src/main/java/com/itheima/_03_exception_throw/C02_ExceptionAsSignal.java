package com.itheima._03_exception_throw;

/**
 * 知识点 8：异常的两大用途
 *
 *   作用一：调试 bug 的关键线索（栈轨迹定位）
 *   作用二：作为方法的一种"特殊返回"，向调用者传递"业务上失败"的信号
 *
 * 下面用一个 Student.setAge 演示"作用二"：
 *   非法年龄不应当被静默处理，而是 throw 出去，让调用者决定怎么办。
 */
public class C02_ExceptionAsSignal {
    public static void main(String[] args) {
        Student s = new Student();
        try {
            s.setAge(200);
        } catch (IllegalArgumentException e) {
            System.out.println("调用者收到失败信号：" + e.getMessage());
        }
    }

    static class Student {
        private int age;
        public void setAge(int age) {
            if (age < 0 || age > 150) {
                // 用异常通知调用者，比 return false / 偷偷设默认值 更显眼
                throw new IllegalArgumentException("年龄非法: " + age);
            }
            this.age = age;
        }
        public int getAge() { return age; }
    }
}
