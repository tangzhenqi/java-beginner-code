package com.itheima.regex.validate;

/**
 * 知识点 5：手机号 / 座机 / 邮箱 / 24 小时时间 校验
 */
public class B02_PhoneAndEmail {
    public static void main(String[] args) {

        // ---------- 1. 手机号 ----------
        // 分三段：1 + [3-9] + \d{9}
        String phone = "1[3-9]\\d{9}";
        System.out.println("13112345678".matches(phone));   // true
        System.out.println("139456790271".matches(phone));  // false（多 1 位）
        System.out.println("12345678901".matches(phone));   // false（第 2 位不在 3-9）

        // ---------- 2. 座机 ----------
        // 区号 0 + 2~3 位数字  -? 可选连字符  号码首位非 0，后续数字共 5~10 位
        String tel = "0\\d{2,3}-?[1-9]\\d{4,9}";
        System.out.println("020-2324242".matches(tel));  // true
        System.out.println("02122442".matches(tel));     // true
        System.out.println("0712-3242434".matches(tel)); // true

        // ---------- 3. 邮箱 ----------
        // @ 左：\w+
        // @ 右主域：[\w&&[^_]]{2,6}   不能含下划线
        // 顶级域：(\.[a-zA-Z]{2,3}){1,2}   .com / .com.cn
        String email = "\\w+@[\\w&&[^_]]{2,6}(\\.[a-zA-Z]{2,3}){1,2}";
        System.out.println("3232323@qq.com".matches(email));      // true
        System.out.println("dlei0009@pci.com.cn".matches(email)); // true
        System.out.println("bad@_x.com".matches(email));          // false

        // ---------- 4. 24 小时时间 ----------
        // 小时：00~19 或 20~23   →  ([01]\d|2[0-3])
        // 分秒：00~59             →  [0-5]\d
        String time = "([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d";
        System.out.println("23:11:11".matches(time)); // true
        System.out.println("24:00:00".matches(time)); // false

        // 等价写法：把 :[0-5]\d 当作 1 组重复 2 次
        String time2 = "([01]\\d|2[0-3])(:[0-5]\\d){2}";
        System.out.println("09:30:00".matches(time2)); // true
    }
}
