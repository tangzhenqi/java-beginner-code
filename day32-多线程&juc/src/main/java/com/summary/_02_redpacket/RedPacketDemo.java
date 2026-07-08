package com.summary._02_redpacket;

import java.util.Random;

/**
 * 抢红包（double 简单版）：
 * 100 元拆成 3 个包，5 个人来抢，3 人能抢到、2 人空手。
 * <p>
 * 难点：
 * 1. 最后一个红包不再随机，剩多少给多少，避免精度丢失到 0；
 * 2. 随机范围必须给后面的人留出至少 MIN 元，否则可能出现 0 元红包；
 * 3. double 仅供入门，金融场景请用 BigDecimal —— 见 RedPacketBigDecimalDemo。
 */
public class RedPacketDemo {

    public static void main(String[] args) {
        String[] names = {"小A", "小QQ", "小哈哈", "小诗诗", "小丹丹"};
        for (String name : names) {
            RedPacket rp = new RedPacket();
            rp.setName(name);
            rp.start();
        }
    }

    static class RedPacket extends Thread {
        static double money = 100;
        static int count = 3;
        static final double MIN = 0.01;

        @Override
        public void run() {
            synchronized (RedPacket.class) {
                if (count == 0) {
                    System.out.println(getName() + " 没抢到红包！");
                    return;
                }
                double prize;
                if (count == 1) {
                    prize = money;
                } else {
                    double bounds = money - (count - 1) * MIN;
                    prize = new Random().nextDouble() * bounds;
                    if (prize < MIN) {
                        prize = MIN;
                    }
                }
                money -= prize;
                count--;
                System.out.printf("%s 抢到了 %.2f 元%n", getName(), prize);
            }
        }
    }
}
