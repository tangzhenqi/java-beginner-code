package com.itheima._05_method_reference;

/**
 * 方法引用规则总结（无可运行逻辑，纯文字速查表）。
 * <p>
 * 5 种形式：
 * <pre>
 *  形式                          示例                  等价 lambda
 *  ─────────────────────────────────────────────────────────────────
 *  类名::静态方法                Integer::parseInt     s -> Integer.parseInt(s)
 *  对象::成员方法                op::toUpper          s -> op.toUpper(s)
 *  this:: / super::成员方法      this::check          s -> this.check(s)
 *  类名::成员方法                String::toUpperCase  s -> s.toUpperCase()
 *  类名::new                     Student::new         s -> new Student(s)
 *  类型[]::new                   String[]::new        n -> new String[n]
 * </pre>
 * <p>
 * 共通的 4 条规则：
 * <ol>
 *   <li>必须有函数式接口作为目标类型（Function / Predicate / Consumer / Supplier...）</li>
 *   <li>被引用的方法必须已经存在</li>
 *   <li>形参与返回值要与抽象方法匹配</li>
 *   <li>语义上要满足当前需求</li>
 * </ol>
 * <p>
 * 「对象::方法」与「类名::方法」如何区分？
 *   - 若调用对象在外面已确定（变量 / this / super） → 写「对象::方法」
 *   - 若调用对象就是流中的元素本身             → 写「类名::方法」
 */
public class MRRulesSummaryDemo {
    public static void main(String[] args) {
        System.out.println("方法引用 5 种形式速查请阅读源码注释。");
    }
}
