package com.itheima.day14.pkg;

// 只能 import 一个同名类；另一个必须使用全限定名（FQN）
import com.itheima.day14.pkg.domain1.Teacher;

/**
 * 包（package）知识点：
 *
 *  1. 包名遵循 公司域名倒写 的约定，例如 com.itheima.xxx
 *  2. import 用来引入其他包里的类，使代码更短
 *  3. java.lang 包下的类（如 String、System）默认导入，无需 import
 *  4. 同一文件中要使用两个同名类时：
 *       - 一个用 import 引入（短名字）
 *       - 另一个用全限定名（package.ClassName）
 *  5. 同包之间不需要 import
 */
public class PackageDemo {
    public static void main(String[] args) {
        // 这里的 Teacher 来自 domain1 —— 因为顶部 import 的就是它
        Teacher t1 = new Teacher("domain1-王老师", 40);
        System.out.println(t1);

        // domain2.Teacher 不能再 import，只能写全限定名
        com.itheima.day14.pkg.domain2.Teacher t2 =
                new com.itheima.day14.pkg.domain2.Teacher("domain2-李老师", 35);
        System.out.println(t2);

        // java.lang.String 默认导入，无需 import
        String s = "Hello, package!";
        System.out.println(s);
    }
}
