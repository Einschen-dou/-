
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 塔罗牌组类，管理塔罗牌的初始化、洗牌和抽牌
 */
public class TarotDeck {
    private List<TarotCard> deck;

    public TarotDeck() {
        deck = new ArrayList<>();
        initializeMajorArcana();
    }

    /**
     * 初始化大阿尔卡那牌（22张）
     */
    private void initializeMajorArcana() {
        // 添加大阿尔卡那牌，这里只列出部分示例，实际可以补充完整22张
        deck.add(new TarotCard("0. 愚人", "大阿尔卡那",
                "代表新的开始、自由、冒险精神和天真无邪",
                "代表鲁莽、天真、缺乏方向和不负责任"));

        deck.add(new TarotCard("1. 魔术师", "大阿尔卡那",
                "代表创造力、行动力、资源和自信",
                "代表缺乏自信、能力不足、欺骗和操纵"));

        deck.add(new TarotCard("2. 女祭司", "大阿尔卡那",
                "代表直觉、神秘、智慧和内心的声音",
                "代表隐藏的秘密、直觉失灵、缺乏洞察力"));

        deck.add(new TarotCard("3. 女皇", "大阿尔卡那",
                "代表丰饶、母性、创造力和享受生活",
                "代表 infertility、依赖、过度放纵和缺乏自律"));

        deck.add(new TarotCard("4. 国王", "大阿尔卡那",
                "代表权威、控制力、责任感和成熟",
                "代表独裁、固执、缺乏同理心和滥用权力"));

        deck.add(new TarotCard("5. 教皇", "大阿尔卡那",
                "代表传统、灵性指导、教育和道德",
                "代表教条、盲目跟随、缺乏独立思考"));

        deck.add(new TarotCard("6. 恋人", "大阿尔卡那",
                "代表爱情、选择、和谐关系和价值观",
                "代表关系问题、错误的选择、不和谐和诱惑"));

        deck.add(new TarotCard("7. 战车", "大阿尔卡那",
                "代表决心、控制、胜利和前进的动力",
                "代表缺乏方向、冲突、失败和失控"));

        deck.add(new TarotCard("8. 力量", "大阿尔卡那",
                "代表勇气、内在力量、耐心和温柔的力量",
                "代表软弱、恐惧、缺乏勇气和滥用力量"));

        deck.add(new TarotCard("9. 隐士", "大阿尔卡那",
                "代表内省、智慧、孤独和寻求真相",
                "代表孤立、逃避现实、缺乏社交和过度谨慎"));

        // 可以继续添加剩余的大阿尔卡那牌...
    }

    /**
     * 洗牌
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * 抽取指定数量的牌
     * @param count 要抽取的牌数
     * @return 抽取的牌列表
     */
    public List<TarotCard> drawCards(int count) {
        if (count < 1 || count > deck.size()) {
            throw new IllegalArgumentException("无效的抽牌数量");
        }

        List<TarotCard> drawn = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawn.add(deck.remove(0)); // 从牌组顶部抽牌
        }
        return drawn;
    }

    /**
     * 获取牌组剩余牌数
     */
    public int getRemainingCards() {
        return deck.size();
    }
}
