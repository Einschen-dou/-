
import java.util.List;
import java.util.Scanner;

/**
 * 塔罗牌解读类，处理占卜流程和解读
 */
public class TarotReading {
    private TarotDeck deck;
    private Scanner scanner;

    public TarotReading() {
        deck = new TarotDeck();
        scanner = new Scanner(System.in);
    }

    /**
     * 开始占卜流程
     */
    public void startReading() {
        System.out.println("=== 塔罗牌预测程序 ===");
        System.out.println("请记住：塔罗牌是一种自我探索的工具，结果仅供参考");
        System.out.println("请专注于你想询问的问题，然后按Enter键继续...");
        scanner.nextLine();

        // 洗牌
        System.out.println("正在洗牌...");
        deck.shuffle();
        try {
            Thread.sleep(1500); // 模拟洗牌时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 选择牌阵
        System.out.println("\n请选择牌阵：");
        System.out.println("1. 单牌占卜（快速解答）");
        System.out.println("2. 三牌阵（过去、现在、未来）");
        System.out.println("3. 六芒星阵（深入分析）");
        System.out.print("请输入选项（1-3）：");

        int choice = 0;
        while (choice < 1 || choice > 3) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("无效输入，请重新输入（1-3）：");
            }
        }

        // 根据选择进行解读
        switch (choice) {
            case 1:
                doSingleCardReading();
                break;
            case 2:
                doThreeCardReading();
                break;
            case 3:
                doSixPointedStarReading();
                break;
        }

        System.out.println("\n--- 占卜结束 ---");
        System.out.println("希望这次解读能给你带来启发");
        scanner.close();
    }

    /**
     * 单牌占卜
     */
    private void doSingleCardReading() {
        System.out.println("\n--- 单牌占卜 ---");
        System.out.println("这张牌代表你当前问题的核心");

        List<TarotCard> cards = deck.drawCards(1);
        TarotCard card = cards.get(0);

        System.out.println("\n抽到的牌：");
        System.out.println(card);
    }

    /**
     * 三牌阵占卜（过去、现在、未来）
     */
    private void doThreeCardReading() {
        System.out.println("\n--- 三牌阵占卜 ---");
        System.out.println("这三张牌分别代表：过去的影响、现在的状况、未来的可能");

        List<TarotCard> cards = deck.drawCards(3);

        System.out.println("\n第一张牌（过去的影响）：");
        System.out.println(cards.get(0));

        System.out.println("\n第二张牌（现在的状况）：");
        System.out.println(cards.get(1));

        System.out.println("\n第三张牌（未来的可能）：");
        System.out.println(cards.get(2));
    }

    /**
     * 六芒星阵占卜（更深入的分析）
     */
    private void doSixPointedStarReading() {
        System.out.println("\n--- 六芒星阵占卜 ---");
        System.out.println("这六张牌分别代表不同方面的影响");

        List<TarotCard> cards = deck.drawCards(6);

        System.out.println("\n第一张牌（当前状况）：");
        System.out.println(cards.get(0));

        System.out.println("\n第二张牌（挑战）：");
        System.out.println(cards.get(1));

        System.out.println("\n第三张牌（潜意识影响）：");
        System.out.println(cards.get(2));

        System.out.println("\n第四张牌（过去的影响）：");
        System.out.println(cards.get(3));

        System.out.println("\n第五张牌（未来的可能）：");
        System.out.println(cards.get(4));

        System.out.println("\n第六张牌（最终结果）：");
        System.out.println(cards.get(5));
    }

    public static void main(String[] args) {
        TarotReading reading = new TarotReading();
        reading.startReading();
    }
}
