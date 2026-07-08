package com.itheima._02_jdk8_datetime;

import java.time.ZoneId;
import java.util.Set;

/**
 * 知识点：ZoneId 时区
 * <p>
 * 常用 API：
 * - static Set<String> getAvailableZoneIds() 所有可用时区 ID
 * - static ZoneId systemDefault()            系统默认时区
 * - static ZoneId of(String zoneId)          指定时区
 * <p>
 * 拓展：
 * - 常见时区：Asia/Shanghai、Asia/Tokyo、America/New_York、Europe/London、UTC
 * - 通过 ZoneId 可以把 Instant（绝对时间）转成 ZonedDateTime（本地时间）
 */
public class _04_ZoneIdApi {
    public static void main(String[] args) {
        Set<String> all = ZoneId.getAvailableZoneIds();
        System.out.println("可用时区总数：" + all.size());

        ZoneId def = ZoneId.systemDefault();
        System.out.println("系统默认时区：" + def);

        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        System.out.println("指定时区：" + tokyo);
    }
}
