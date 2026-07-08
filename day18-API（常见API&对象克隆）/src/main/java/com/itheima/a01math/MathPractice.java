package com.itheima.a01math;

/**
 * 知识点一：Math 类 —— 配套练习
 * <p>
 * 1. 判断质数（利用 sqrt 优化）
 * 2. 统计水仙花数、四叶玫瑰数、五角星数（利用 pow）
 * 3. 没有两位自幂数的原因（思考题）
 */
public class MathPractice {
    public static void main(String[] args) {
        // 1. 质数判断
        System.out.println("997 是质数？" + isPrime(997));

        // 2. 自幂数：每一位的 n 次方之和等于本身
        System.out.println("三位水仙花数共 " + countNarcissistic(3) + " 个");
        System.out.println("四位四叶玫瑰数共 " + countNarcissistic(4) + " 个");
        System.out.println("五位五角星数共 " + countNarcissistic(5) + " 个");

        // 3. 两位自幂数为什么没有：
        //    设 n = 10*a + b （a∈[1..9], b∈[0..9]）
        //    则 a² + b² <= 9² + 9² = 162 < 99 是错的，需要 = 10a + b
        //    通过遍历可验证：任何两位数都不等于其各位平方之和。
        boolean found = false;
        for (int i = 10; i <= 99; i++) {
            int ge = i % 10;
            int shi = i / 10;
            if (ge * ge + shi * shi == i) {
                found = true;
                System.out.println("竟然找到了：" + i);
            }
        }
        if (!found) System.out.println("两位自幂数确实不存在");
    }

    public static boolean isPrime(int number) {
        if (number < 2) return false;
        // sqrt 优化：因数总是成对出现 (a,b)，其中至少一个 <= sqrt(n)
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    /**
     * 统计 digits 位数的自幂数个数。
     * 自幂数：每一位的 digits 次方之和等于本身。
     */
    public static int countNarcissistic(int digits) {
        int start = (int) Math.pow(10, digits - 1);
        int end = (int) Math.pow(10, digits) - 1;
        int count = 0;
        for (int i = start; i <= end; i++) {
            int sum = 0;
            int tmp = i;
            while (tmp > 0) {
                int d = tmp % 10;
                sum += Math.pow(d, digits);
                tmp /= 10;
            }
            if (sum == i) count++;
        }
        return count;
    }
}
