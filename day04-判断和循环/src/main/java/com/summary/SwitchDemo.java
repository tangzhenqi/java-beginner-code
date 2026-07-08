package com.summary;

/**
 * 知识点3：switch 语句基本结构
 *
 *   switch (表达式) {
 *       case 值1:
 *           语句体1;
 *           break;
 *       case 值2:
 *           语句体2;
 *           break;
 *       ...
 *       default:
 *           语句体n;
 *           break;
 *   }
 *
 *   执行流程：
 *     拿小括号中表达式的值依次和每个 case 后面的值比较，
 *     匹配到哪个 case 就执行哪个 case 里的语句体，遇到 break 结束整个 switch。
 *
 *   switch 支持的表达式类型：
 *     基本类型：byte、short、int、char（不支持 long、float、double、boolean）
 *     引用类型：String（JDK7+）、枚举
 */
public class SwitchDemo {
    public static void main(String[] args) {
        String noodles = "海鲜龙虾面";
        switch (noodles) {
            case "兰州拉面":
                System.out.println("吃兰州拉面");
                break;
            case "武汉热干面":
                System.out.println("吃武汉热干面");
                break;
            case "北京炸酱面":
                System.out.println("吃北京炸酱面");
                break;
            case "陕西油泼面":
                System.out.println("吃陕西油泼面");
                break;
            default:
                System.out.println("吃方便面");
                break;
        }
    }
}
