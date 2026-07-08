package com.summary.objectarray;

/**
 * 知识点 5：对象数组的基本操作
 *
 * 三步走：
 *   1. new 一个数组（长度固定）：    Phone[] arr = new Phone[3];
 *   2. new 若干个对象，依次塞进数组： arr[0] = new Phone(...);
 *   3. 遍历数组：用 for 循环按索引取出每一个对象，再调对象方法。
 *
 * 典型聚合操作：
 *   - 求总和、求平均
 *   - 求最大 / 最小
 *   - 条件筛选与计数（比如：年龄低于平均的有几个）
 *
 * 注意整数除法会丢失小数：
 *   int sum = 11000; int len = 3;
 *   int avg  = sum / len;          // = 3666  ←错
 *   double a = sum * 1.0 / len;    // = 3666.66...  ←对（强转一个 double 上来即可）
 */
public class ObjectArrayDemo {
    public static void main(String[] args) {
        Phone[] arr = new Phone[3];
        arr[0] = new Phone("小米", 1999, "白色");
        arr[1] = new Phone("华为", 4999, "蓝色");
        arr[2] = new Phone("魅族", 3999, "红色");

        // 遍历 + 求和
        int sum = 0;
        int maxPrice = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            Phone p = arr[i];
            System.out.println(p.getBrand() + ", " + p.getPrice() + ", " + p.getColor());
            sum += p.getPrice();
            if (p.getPrice() > maxPrice) {
                maxPrice = p.getPrice();
            }
        }

        // 平均：通过 * 1.0 强转，防止整数除法
        double avg = sum * 1.0 / arr.length;
        System.out.println("总价：" + sum);
        System.out.println("均价：" + avg);
        System.out.println("最高价：" + maxPrice);
    }
}
