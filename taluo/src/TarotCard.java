
/**
 * 塔罗牌实体类，代表一张塔罗牌
 */
public class TarotCard {
    private String name;         // 牌名
    private String type;         // 类型（大阿尔卡那）
    private String uprightMeaning;  // 正位含义
    private String reversedMeaning; // 逆位含义
    private boolean isReversed;   // 是否逆位

    public TarotCard(String name, String type, String uprightMeaning, String reversedMeaning) {
        this.name = name;
        this.type = type;
        this.uprightMeaning = uprightMeaning;
        this.reversedMeaning = reversedMeaning;
        this.isReversed = Math.random() < 0.3; // 30%概率逆位
    }

    // getter和setter方法
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMeaning() {
        return isReversed ? reversedMeaning : uprightMeaning;
    }

    public boolean isReversed() {
        return isReversed;
    }

    @Override
    public String toString() {
        return name + " (" + (isReversed ? "逆位" : "正位") + "): " + getMeaning();
    }
}
