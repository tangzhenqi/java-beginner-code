package com.itheima;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实战案例：订单服务
 * <p>
 * 演示在真实业务里如何用日志代替 System.out.println：
 * 用不同级别记录“正常流程 / 关键节点 / 异常告警”，
 * 既能在控制台看，又能持久化到文件（见 logback.xml）。
 */
public class OrderService {

    // 每个类持有自己的 Logger，参数一般传入当前类的 Class，方便定位日志来源
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    /**
     * 下单：库存不足则抛异常，金额非法则告警。
     *
     * @param userId  下单用户
     * @param product 商品名
     * @param count   购买数量
     * @param stock   当前库存
     * @param price   单价
     * @return 本次订单总金额
     */
    public double createOrder(String userId, String product, int count, int stock, double price) {
        // DEBUG：排查用的细节，生产环境通常关闭（当前 root=info，不会打印）
        log.debug("进入 createOrder，参数 userId={}, product={}, count={}, stock={}, price={}",
                userId, product, count, stock, price);

        // INFO：业务关键节点，记录“谁在什么时候做了什么”
        log.info("用户[{}]开始下单，商品={}，数量={}", userId, product, count);

        if (price <= 0) {
            // WARN：不影响主流程但值得关注的异常数据
            log.warn("商品[{}]单价异常：price={}，按 0 处理", product, price);
            price = 0;
        }

        if (count > stock) {
            // ERROR：业务失败，必须记录，通常还会触发告警
            log.error("下单失败：商品[{}]库存不足，需要{}件，仅剩{}件", product, count, stock);
            throw new IllegalStateException("库存不足");
        }

        double total = count * price;
        log.info("用户[{}]下单成功，商品={}，应付金额={}", userId, product, total);
        return total;
    }

    public static void main(String[] args) {
        OrderService service = new OrderService();

        // 场景一：正常下单
        service.createOrder("u1001", "机械键盘", 2, 10, 299.0);

        // 场景二：单价异常，触发 WARN，但仍下单成功
        service.createOrder("u1002", "赠品鼠标垫", 1, 100, -5.0);

        // 场景三：库存不足，触发 ERROR 并抛异常
        try {
            service.createOrder("u1003", "限量显卡", 5, 1, 4999.0);
        } catch (IllegalStateException e) {
            log.error("捕获到下单异常：{}", e.getMessage());
        }

        log.info("演示结束，日志已同时写入 logs/order.log");
    }
}
