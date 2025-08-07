package bazi.wuxing;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class wuxing {
    // 天干与五行的对应关系 - 修复变量名
    private static final Map<Character, String> TIAN_GAN_WU_XING = new HashMap<>();
    // 地支与五行的对应关系 - 修复变量名
    private static final Map<Character, String> DI_ZHI_WU_XING = new HashMap<>();

    static {
        // 初始化天干五行 - 这里的引用已经正确
        TIAN_GAN_WU_XING.put('甲', "木");
        TIAN_GAN_WU_XING.put('乙', "木");
        TIAN_GAN_WU_XING.put('丙', "火");
        TIAN_GAN_WU_XING.put('丁', "火");
        TIAN_GAN_WU_XING.put('戊', "土");
        TIAN_GAN_WU_XING.put('己', "土");
        TIAN_GAN_WU_XING.put('庚', "金");
        TIAN_GAN_WU_XING.put('辛', "金");
        TIAN_GAN_WU_XING.put('壬', "水");
        TIAN_GAN_WU_XING.put('癸', "水");

        // 初始化地支五行 - 这里的引用已经正确
        DI_ZHI_WU_XING.put('子', "水");
        DI_ZHI_WU_XING.put('丑', "土");
        DI_ZHI_WU_XING.put('寅', "木");
        DI_ZHI_WU_XING.put('卯', "木");
        DI_ZHI_WU_XING.put('辰', "土");
        DI_ZHI_WU_XING.put('巳', "火");
        DI_ZHI_WU_XING.put('午', "火");
        DI_ZHI_WU_XING.put('未', "土");
        DI_ZHI_WU_XING.put('申', "金");
        DI_ZHI_WU_XING.put('酉', "金");
        DI_ZHI_WU_XING.put('戌', "土");
        DI_ZHI_WU_XING.put('亥', "水");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入四柱（年柱、月柱、日柱、时柱，用空格分隔，例如：丁亥 壬寅 甲子 丙寅）：");
        String input = scanner.nextLine();
        // 修复：使用 \\s+ 正则表达式处理任意数量的空白字符分隔，并先trim去除首尾空格
        String[] pillars = input.trim().split("\\s+");

        // 验证输入是否正确 - 后续验证逻辑不变
        if (pillars.length != 4) {
            System.out.println("输入格式错误，请输入四个柱，用空格分隔");
            return;
        }

        for (String pillar : pillars) {
            if (pillar.length() != 2) {
                System.out.println("输入格式错误，每个柱应该由两个字组成：" + pillar);
                return;
            }
        }

        // 计算五行分布
        Map<String, Integer> fiveElements = calculateFiveElements(pillars);

        // 输出结果
        System.out.println("\n五行分布统计：");
        System.out.println("木：" + fiveElements.get("木"));
        System.out.println("火：" + fiveElements.get("火"));
        System.out.println("土：" + fiveElements.get("土"));
        System.out.println("金：" + fiveElements.get("金"));
        System.out.println("水：" + fiveElements.get("水"));

        scanner.close();
    }

    /**
     * 根据四柱计算五行分布
     */
    private static Map<String, Integer> calculateFiveElements(String[] pillars) {
        Map<String, Integer> result = new HashMap<>();
        // 初始化五行计数
        result.put("木", 0);
        result.put("火", 0);
        result.put("土", 0);
        result.put("金", 0);
        result.put("水", 0);

        for (String pillar : pillars) {
            // 每个柱由一个天干和一个地支组成
            char tianGan = pillar.charAt(0);  // 天干
            char diZhi = pillar.charAt(1);    // 地支

            // 计算天干对应的五行并计数 - 已修复变量名引用
            String tgWx = TIAN_GAN_WU_XING.get(tianGan);
            if (tgWx != null) {
                result.put(tgWx, result.get(tgWx) + 1);
            } else {
                System.out.println("未知的天干：" + tianGan);
            }

            // 计算地支对应的五行并计数 - 已修复变量名引用
            String dzWx = DI_ZHI_WU_XING.get(diZhi);
            if (dzWx != null) {
                result.put(dzWx, result.get(dzWx) + 1);
            } else {
                System.out.println("未知的地支：" + diZhi);
            }
        }

        return result;
    }
}
