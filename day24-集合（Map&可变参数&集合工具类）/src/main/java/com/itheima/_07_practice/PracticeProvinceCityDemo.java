package com.itheima._07_practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * 练习四：省份 -> 多个城市。
 *
 * 需求：
 *   Map 的键是省份名，值是市的集合（一个省有多个市）。
 *   按以下格式输出：
 *       江苏省 = 南京市, 扬州市, 苏州市, 无锡市, 常州市
 *
 * 关键点：值的类型可以是任意对象 —— 包括另一个集合。
 */
public class PracticeProvinceCityDemo {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> hm = new HashMap<>();

        ArrayList<String> jiangsu = new ArrayList<>();
        jiangsu.add("南京市"); jiangsu.add("扬州市"); jiangsu.add("苏州市");
        jiangsu.add("无锡市"); jiangsu.add("常州市");

        ArrayList<String> hubei = new ArrayList<>();
        hubei.add("武汉市"); hubei.add("孝感市"); hubei.add("十堰市");
        hubei.add("宜昌市"); hubei.add("鄂州市");

        ArrayList<String> hebei = new ArrayList<>();
        hebei.add("石家庄市"); hebei.add("唐山市"); hebei.add("邢台市");
        hebei.add("保定市");   hebei.add("张家口市");

        hm.put("江苏省", jiangsu);
        hm.put("湖北省", hubei);
        hm.put("河北省", hebei);

        // 遍历输出
        hm.forEach((province, cities) -> {
            StringJoiner sj = new StringJoiner(", ");
            cities.forEach(sj::add);
            System.out.println(province + " = " + sj);
        });
    }
}
