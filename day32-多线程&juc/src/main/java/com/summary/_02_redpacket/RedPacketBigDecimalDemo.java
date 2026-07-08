package com.summary._02_redpacket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * 抢红包（BigDecimal 精确版）：
 * 涉及金额必须用 BigDecimal，避免 double 累加误差导致"总额不对账"。
 * <p>
 * 注意：
 * - add / subtract / multiply 这些方法都"不会修改原对象"，必须用返回值赋值；
 * - setScale(2, HALF_UP) 设置小数位与四舍五入策略。
 */
public class RedPacketBigDecimalDemo {

    public static void main(String[] args) {
        String[] names = {"小A", "小QQ", "小哈哈", "小诗诗", "小丹丹"};
        for (String name : names) {
            RedPacket rp = new RedPacket();
            rp.setName(name);
            rp.start();
        }
    }

    static class RedPacket extends Thread {
        static BigDecimal money = BigDecimal.valueOf(100.0);
        static int count = 3;
        static final BigDecimal MIN = BigDecimal.valueOf(0.01);

        @Override
        public void run() {
            synchronized (RedPacket.class) {
                if (count == 0) {
                    System.out.println(getName() + " 没抢到红包！");
                    return;
                }
                BigDecimal prize;
                if (count == 1) {
                    prize = money;
                } else {
                    double bounds = money.subtract(BigDecimal.valueOf(count - 1).multiply(MIN)).doubleValue();
                    prize = BigDecimal.valueOf(new Random().nextDouble() * bounds);
                }
                prize = prize.setScale(2, RoundingMode.HALF_UP);
                money = money.subtract(prize);
                count--;
                System.out.println(getName() + " 抢到了 " + prize + " 元");
            }
        }
    }
}
