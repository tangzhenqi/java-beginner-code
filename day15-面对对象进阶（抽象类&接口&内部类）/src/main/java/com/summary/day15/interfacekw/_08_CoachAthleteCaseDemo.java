package com.summary.day15.interfacekw;

/**
 * 综合案例（来源 day15 的"教练 & 运动员"练习，做了拓展）：
 *
 *      Person（抽象类）
 *          ├── Coach   （抽象类，新增 teach()）
 *          │       ├── PingPongCoach    implements EnglishSpeakable
 *          │       └── BasketballCoach
 *          └── Athlete （抽象类，新增 train()）
 *                  ├── PingPongAthlete  implements EnglishSpeakable
 *                  └── BasketballAthlete
 *
 *  设计要点：
 *      1. 共性属性放抽象类（name/age）；
 *      2. "说英语" 不是所有教练 / 运动员都具备，是"额外能力" → 接口；
 *      3. 调用方面向 Person / Coach / Athlete / EnglishSpeakable 编程（多态）。
 */
public class _08_CoachAthleteCaseDemo {

    public static void main(String[] args) {
        Coach   c1 = new PingPongCoach("刘国梁", 50);
        Coach   c2 = new BasketballCoach("杜锋", 47);
        Athlete a1 = new PingPongAthlete("孙颖莎", 24);
        Athlete a2 = new BasketballAthlete("姚明", 45);

        c1.teach();
        c2.teach();
        a1.train();
        a2.train();

        // 国际比赛要求会说英语 → 找出所有"会说英语"的人统一开会
        Person[] people = {c1, c2, a1, a2};
        System.out.println("---- 召集会说英语的人 ----");
        for (Person p : people) {
            if (p instanceof EnglishSpeakable) {
                ((EnglishSpeakable) p).speakEnglish();
            }
        }
    }
}

abstract class Person {
    private final String name;
    private final int age;
    protected Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public int getAge()     { return age; }
}

abstract class Coach extends Person {
    protected Coach(String name, int age) { super(name, age); }
    public abstract void teach();
}

abstract class Athlete extends Person {
    protected Athlete(String name, int age) { super(name, age); }
    public abstract void train();
}

/** 额外能力 → 接口。 */
interface EnglishSpeakable {
    void speakEnglish();
}

class PingPongCoach extends Coach implements EnglishSpeakable {
    public PingPongCoach(String name, int age) { super(name, age); }
    @Override public void teach()        { System.out.println(getName() + " 教授乒乓球技战术"); }
    @Override public void speakEnglish() { System.out.println(getName() + " 用英文做战术布置"); }
}

class BasketballCoach extends Coach {
    public BasketballCoach(String name, int age) { super(name, age); }
    @Override public void teach() { System.out.println(getName() + " 教授篮球战术"); }
}

class PingPongAthlete extends Athlete implements EnglishSpeakable {
    public PingPongAthlete(String name, int age) { super(name, age); }
    @Override public void train()        { System.out.println(getName() + " 在练习正手弧圈球"); }
    @Override public void speakEnglish() { System.out.println(getName() + " 用英文做赛后采访"); }
}

class BasketballAthlete extends Athlete {
    public BasketballAthlete(String name, int age) { super(name, age); }
    @Override public void train() { System.out.println(getName() + " 在加练三分球"); }
}
