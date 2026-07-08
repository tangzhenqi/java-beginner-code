package com.summary.objectarray;

/**
 * 配套案例：求平均之后，筛选出"低于平均价格"的手机并打印。
 *
 * 这个套路相当通用：
 *   1. 第一次遍历做累加，得到聚合值（平均、最大...）
 *   2. 第二次遍历做条件筛选/计数
 */
public class AverageFilterDemo {
    public static void main(String[] args) {
        Phone[] arr = {
                new Phone("小米", 1999, "白色"),
                new Phone("华为", 4999, "蓝色"),
                new Phone("魅族", 3999, "红色"),
                new Phone("苹果", 8999, "黑色")
        };

        // 第 1 次：求平均
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i].getPrice();
        }
        double avg = sum * 1.0 / arr.length;
        System.out.println("均价：" + avg);

        // 第 2 次：筛选 + 计数
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            Phone p = arr[i];
            if (p.getPrice() < avg) {
                System.out.println("低于均价：" + p.getBrand() + " - " + p.getPrice());
                count++;
            }
        }
        System.out.println("共 " + count + " 部低于均价");
    }
}
