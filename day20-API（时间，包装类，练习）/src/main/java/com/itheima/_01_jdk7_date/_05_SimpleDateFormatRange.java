package com.itheima._01_jdk7_date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 知识点：时间范围判断（秒杀活动）
 * <p>
 * 规则：只要对时间进行计算或比较，都要先转成毫秒值再判断。
 * 秒杀活动 0:00:00 - 0:10:00，判断订单时间是否落在范围内。
 */
public class _05_SimpleDateFormatRange {
    public static void main(String[] args) throws ParseException {
        String startStr = "2023年11月11日 0:0:0";
        String endStr   = "2023年11月11日 0:10:0";
        String order1   = "2023年11月11日 0:01:00";
        String order2   = "2023年11月11日 0:11:00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        long start = sdf.parse(startStr).getTime();
        long end   = sdf.parse(endStr).getTime();

        check(order1, sdf.parse(order1).getTime(), start, end);
        check(order2, sdf.parse(order2).getTime(), start, end);
    }

    private static void check(String label, long t, long start, long end) {
        if (t >= start && t <= end) {
            System.out.println(label + " → 参加秒杀成功");
        } else {
            System.out.println(label + " → 参加秒杀失败");
        }
    }
}
