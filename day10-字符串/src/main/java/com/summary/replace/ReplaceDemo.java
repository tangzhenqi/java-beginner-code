package com.summary.replace;

/**
 * 知识点 5：替换
 *
 *   str.replace(oldStr, newStr)
 *     —— 把 str 中所有 oldStr 替换为 newStr。
 *     —— String 不可变，所以返回一个新串，原串不变（务必接住返回值！）。
 *
 *   str.replaceAll(regex, newStr)
 *     —— 第一个参数是"正则表达式"，更强大，但也容易踩坑（如 . 是任意字符）。
 *
 * 拓展：敏感词替换可以用"敏感词数组 + 循环"批量处理。
 */
public class ReplaceDemo {
    public static void main(String[] args) {
        // 单次替换
        String talk = "你玩的真好，以后不要再玩了，TMD";
        String safe = talk.replace("TMD", "***");
        System.out.println(safe);

        // 批量敏感词替换
        String talk2 = "TMD 真的 CNM, SB!";
        String[] bad = {"TMD", "CNM", "SB", "MLGB"};
        for (int i = 0; i < bad.length; i++) {
            talk2 = talk2.replace(bad[i], "***");
        }
        System.out.println(talk2);
    }
}
