package bazi.geju;

import java.util.*;

public class geju {
    // 天干地支基础数据
    private static final String[] TIANGAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] DIZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    // 地支藏干映射 (本气, 中气, 余气)
    private static final Map<Character, List<Character>> DIZHI_CANGGAN = new HashMap<>();
    // 天干五行映射
    private static final Map<Character, String> TIAN_GAN_WU_XING = new HashMap<>();
    // 地支五行映射
    private static final Map<Character, String> DI_ZHI_WU_XING = new HashMap<>();
    // 天干阴阳 (阳为true，阴为false)
    private static final Map<Character, Boolean> TIAN_GAN_YINYANG = new HashMap<>();

    static {
        // 初始化地支藏干
        DIZHI_CANGGAN.put('子', Arrays.asList('癸'));
        DIZHI_CANGGAN.put('丑', Arrays.asList('己', '辛', '癸'));
        DIZHI_CANGGAN.put('寅', Arrays.asList('甲', '丙', '戊'));
        DIZHI_CANGGAN.put('卯', Arrays.asList('乙'));
        DIZHI_CANGGAN.put('辰', Arrays.asList('戊', '乙', '癸'));
        DIZHI_CANGGAN.put('巳', Arrays.asList('丙', '庚', '戊'));
        DIZHI_CANGGAN.put('午', Arrays.asList('丁', '己'));
        DIZHI_CANGGAN.put('未', Arrays.asList('己', '丁', '乙'));
        DIZHI_CANGGAN.put('申', Arrays.asList('庚', '壬', '戊'));
        DIZHI_CANGGAN.put('酉', Arrays.asList('辛'));
        DIZHI_CANGGAN.put('戌', Arrays.asList('戊', '丁', '辛'));
        DIZHI_CANGGAN.put('亥', Arrays.asList('壬', '甲'));

        // 初始化天干五行
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

        // 初始化地支五行
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

        // 初始化天干阴阳
        TIAN_GAN_YINYANG.put('甲', true);
        TIAN_GAN_YINYANG.put('乙', false);
        TIAN_GAN_YINYANG.put('丙', true);
        TIAN_GAN_YINYANG.put('丁', false);
        TIAN_GAN_YINYANG.put('戊', true);
        TIAN_GAN_YINYANG.put('己', false);
        TIAN_GAN_YINYANG.put('庚', true);
        TIAN_GAN_YINYANG.put('辛', false);
        TIAN_GAN_YINYANG.put('壬', true);
        TIAN_GAN_YINYANG.put('癸', false);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入八字四柱（年柱、月柱、日柱、时柱，空格分隔，如：甲午 丙寅 辛丑 壬辰）：");
        String input = scanner.nextLine().trim();
        String[] pillars = input.split("\\s+");

        if (pillars.length != 4) {
            System.out.println("输入错误，请输入四个柱！");
            return;
        }

        // 验证输入格式
        for (int i = 0; i < pillars.length; i++) {
            if (pillars[i].length() != 2) {
                System.out.println("第" + (i+1) + "柱格式错误，应为两个字符！");
                return;
            }
        }

        // 提取各柱信息
        char[] yearGan = {pillars[0].charAt(0)};
        char[] yearZhi = {pillars[0].charAt(1)};
        char[] monthGan = {pillars[1].charAt(0)};
        char[] monthZhi = {pillars[1].charAt(1)};
        char[] dayGan = {pillars[2].charAt(0)};
        char[] dayZhi = {pillars[2].charAt(1)};
        char[] hourGan = {pillars[3].charAt(0)};
        char[] hourZhi = {pillars[3].charAt(1)};

        // 收集所有天干
        Set<Character> allTiangan = new HashSet<>();
        allTiangan.add(yearGan[0]);
        allTiangan.add(monthGan[0]);
        allTiangan.add(dayGan[0]);
        allTiangan.add(hourGan[0]);

        // 显示基本信息
        System.out.println("\n===== 八字基本信息 =====");
        System.out.println("年柱：" + pillars[0] + " 月柱：" + pillars[1] + " 日柱：" + pillars[2] + " 时柱：" + pillars[3]);
        System.out.println("日主（日干）：" + dayGan[0] + "，五行：" + TIAN_GAN_WU_XING.get(dayGan[0]));

        // 分析月令藏干
        System.out.println("\n===== 月令分析 =====");
        char yueLing = monthZhi[0];
        System.out.println("月令为：" + yueLing + "，五行：" + DI_ZHI_WU_XING.get(yueLing));
        List<Character> cangGan = DIZHI_CANGGAN.get(yueLing);
        System.out.println("月令藏干（本气、中气、余气）：" + cangGan);

        // 查找透出的藏干
        List<Character> touChuGan = new ArrayList<>();
        for (Character gan : cangGan) {
            if (allTiangan.contains(gan)) {
                touChuGan.add(gan);
            }
        }

        if (!touChuGan.isEmpty()) {
            System.out.println("透出的藏干：" + touChuGan);
        } else {
            System.out.println("月令藏干均未透出");
        }

        // 手动输入十神（替换原自动计算逻辑）
        Map<Character, String> shiShenMap = new HashMap<>();
        System.out.println("\n请依次输入以下天干的十神（直接输入名称，如：比肩、正官）：");
        // 按年干、月干、日干、时干顺序输入，明确对应关系
        String[] positions = {"年干", "月干", "日干", "时干"};
        char[] gans = {yearGan[0], monthGan[0], dayGan[0], hourGan[0]};

        for (int i = 0; i < positions.length; i++) {
            System.out.print(positions[i] + "(" + gans[i] + ")：");
            shiShenMap.put(gans[i], scanner.nextLine().trim());
        }

        // 分析格局（使用用户输入的shiShenMap）
        String pattern = analyzePattern(dayGan[0], monthZhi[0], cangGan, touChuGan, shiShenMap);
        System.out.println("\n===== 格局分析结果 =====");
        System.out.println("此八字格局为：" + pattern);

        scanner.close();
    }

    // 分析格局
    private static String analyzePattern(char riGan, char yueLing, List<Character> cangGan,
                                         List<Character> touChuGan, Map<Character, String> shiShenMap) {
        // 1. 有透出的藏干，以透出的十神立格
        if (!touChuGan.isEmpty()) {
            // 优先考虑本气透出
            Character bengQi = cangGan.get(0);
            if (touChuGan.contains(bengQi)) {
                String shiShen = shiShenMap.get(bengQi);
                return getPatternName(shiShen) + "格（月令本气透出）";
            }

            // 没有本气透出则考虑中气或余气
            for (int i = 1; i < cangGan.size(); i++) {
                Character gan = cangGan.get(i);
                if (touChuGan.contains(gan)) {
                    String shiShen = shiShenMap.get(gan);
                    return getPatternName(shiShen) + "格（月令" + (i == 1 ? "中气" : "余气") + "透出）";
                }
            }
        }

        // 2. 藏干未透出，以月令本气立格
        Character benQi = cangGan.get(0);
        String benQiWuXing = TIAN_GAN_WU_XING.get(benQi);
        String riGanWuXing = TIAN_GAN_WU_XING.get(riGan);
        boolean riGanYang = TIAN_GAN_YINYANG.get(riGan);
        boolean benQiYang = TIAN_GAN_YINYANG.get(benQi);

        String shiShen;
        if (benQiWuXing.equals(riGanWuXing)) {
            shiShen = benQiYang == riGanYang ? "比肩" : "劫财";
        } else if (isSheng(benQiWuXing, riGanWuXing)) {
            shiShen = benQiYang == riGanYang ? "偏印" : "正印";
        } else if (isSheng(riGanWuXing, benQiWuXing)) {
            shiShen = benQiYang == riGanYang ? "食神" : "伤官";
        } else if (isKe(benQiWuXing, riGanWuXing)) {
            shiShen = benQiYang == riGanYang ? "七杀" : "正官";
        } else if (isKe(riGanWuXing, benQiWuXing)) {
            shiShen = benQiYang == riGanYang ? "偏财" : "正财";
        } else {
            return "特殊格局（无法直接判定）";
        }

        return getPatternName(shiShen) + "格（月令本气未透出）";
    }

    // 获取格局名称
    private static String getPatternName(String shiShen) {
        switch (shiShen) {
            case "正官": return "正官";
            case "七杀": return "七杀";
            case "正财": return "正财";
            case "偏财": return "偏财";
            case "正印": return "正印";
            case "偏印": return "偏印";
            case "食神": return "食神";
            case "伤官": return "伤官";
            case "比肩": return "比肩";
            case "劫财": return "劫财";
            default: return "特殊";
        }
    }

    // 五行相生：a生b
    private static boolean isSheng(String a, String b) {
        return (a.equals("木") && b.equals("火")) ||
                (a.equals("火") && b.equals("土")) ||
                (a.equals("土") && b.equals("金")) ||
                (a.equals("金") && b.equals("水")) ||
                (a.equals("水") && b.equals("木"));
    }

    // 五行相克：a克b
    private static boolean isKe(String a, String b) {
        return (a.equals("木") && b.equals("土")) ||
                (a.equals("土") && b.equals("水")) ||
                (a.equals("水") && b.equals("火")) ||
                (a.equals("火") && b.equals("金")) ||
                (a.equals("金") && b.equals("木"));
    }
}
