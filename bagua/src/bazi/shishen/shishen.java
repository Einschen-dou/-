package bazi.shishen;

import java.util.*;

public class shishen {
    // 天干的阴阳属性: 阳为true，阴为false
    private static final Map<Character, Boolean> tianGanYinYang = new HashMap<>();
    // 天干的五行属性
    private static final Map<Character, String> tianGanWuXing = new HashMap<>();
    // 地支的五行属性
    private static final Map<Character, String> diZhiWuXing = new HashMap<>();
    // 地支的阴阳属性
    private static final Map<Character, Boolean> diZhiYinYang = new HashMap<>();

    static {
        // 初始化天干阴阳属性
        tianGanYinYang.put('甲', true);   // 阳
        tianGanYinYang.put('乙', false);  // 阴
        tianGanYinYang.put('丙', true);   // 阳
        tianGanYinYang.put('丁', false);  // 阴
        tianGanYinYang.put('戊', true);   // 阳
        tianGanYinYang.put('己', false);  // 阴
        tianGanYinYang.put('庚', true);   // 阳
        tianGanYinYang.put('辛', false);  // 阴
        tianGanYinYang.put('壬', true);   // 阳
        tianGanYinYang.put('癸', false);  // 阴

        // 初始化天干五行
        tianGanWuXing.put('甲', "木");
        tianGanWuXing.put('乙', "木");
        tianGanWuXing.put('丙', "火");
        tianGanWuXing.put('丁', "火");
        tianGanWuXing.put('戊', "土");
        tianGanWuXing.put('己', "土");
        tianGanWuXing.put('庚', "金");
        tianGanWuXing.put('辛', "金");
        tianGanWuXing.put('壬', "水");
        tianGanWuXing.put('癸', "水");

        // 初始化地支五行
        diZhiWuXing.put('子', "水");
        diZhiWuXing.put('丑', "土");
        diZhiWuXing.put('寅', "木");
        diZhiWuXing.put('卯', "木");
        diZhiWuXing.put('辰', "土");
        diZhiWuXing.put('巳', "火");
        diZhiWuXing.put('午', "火");
        diZhiWuXing.put('未', "土");
        diZhiWuXing.put('申', "金");
        diZhiWuXing.put('酉', "金");
        diZhiWuXing.put('戌', "土");
        diZhiWuXing.put('亥', "水");

        // 初始化地支阴阳属性
        diZhiYinYang.put('子', true);   // 阳
        diZhiYinYang.put('丑', false);  // 阴
        diZhiYinYang.put('寅', true);   // 阳
        diZhiYinYang.put('卯', false);  // 阴
        diZhiYinYang.put('辰', true);   // 阳
        diZhiYinYang.put('巳', false);  // 阴
        diZhiYinYang.put('午', true);   // 阳
        diZhiYinYang.put('未', false);  // 阴
        diZhiYinYang.put('申', true);   // 阳
        diZhiYinYang.put('酉', false);  // 阴
        diZhiYinYang.put('戌', true);   // 阳
        diZhiYinYang.put('亥', false);  // 阴
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入四柱（年柱、月柱、日柱、时柱，用空格分隔，例如：丁亥 壬寅 甲子 丙寅）：");
        String input = scanner.nextLine();
        String[] pillars = input.split(" ");

        // 验证输入是否正确
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

        // 获取日干(日柱的天干)，作为参照点
        char riGan = pillars[2].charAt(0);
        System.out.println("\n日干（日主）为：" + riGan);
        System.out.println("日干五行：" + tianGanWuXing.get(riGan));
        System.out.println("日干阴阳：" + (tianGanYinYang.get(riGan) ? "阳" : "阴"));

        // 计算并输出十神及其五行
        System.out.println("\n十神分布及五行：");
        calculateAndPrintTenGods(pillars, riGan);

        scanner.close();
    }

    /**
     * 计算并打印十神及其五行
     */
    private static void calculateAndPrintTenGods(String[] pillars, char riGan) {
        String riGanWuXing = tianGanWuXing.get(riGan);
        boolean riGanYang = tianGanYinYang.get(riGan);

        // 遍历四柱中的每个干支
        String[] positions = {"年干", "年支", "月干", "月支", "日干", "日支", "时干", "时支"};
        int index = 0;

        for (String pillar : pillars) {
            // 处理天干
            char tianGan = pillar.charAt(0);
            String wuXing = tianGanWuXing.get(tianGan);
            String tenGod = getTenGod(tianGan, true, riGanWuXing, riGanYang);
            System.out.println(positions[index++] + "(" + tianGan + ")：" + tenGod + "，五行：" + wuXing);

            // 处理地支
            char diZhi = pillar.charAt(1);
            wuXing = diZhiWuXing.get(diZhi);
            tenGod = getTenGod(diZhi, false, riGanWuXing, riGanYang);
            System.out.println(positions[index++] + "(" + diZhi + ")：" + tenGod + "，五行：" + wuXing);
        }
    }

    /**
     * 根据干支与日干的关系确定十神
     * @param ganZhi 干支字符
     * @param isTianGan 是否为天干
     * @param riGanWuXing 日干五行
     * @param riGanYang 日干是否为阳
     * @return 十神名称
     */
    private static String getTenGod(char ganZhi, boolean isTianGan, String riGanWuXing, boolean riGanYang) {
        // 获取当前干支的五行和阴阳
        String wuXing = isTianGan ? tianGanWuXing.get(ganZhi) : diZhiWuXing.get(ganZhi);
        boolean yang = isTianGan ? tianGanYinYang.get(ganZhi) : diZhiYinYang.get(ganZhi);

        // 日干与当前干支为同一五行
        if (wuXing.equals(riGanWuXing)) {
            return yang == riGanYang ? "比肩" : "劫财";
        }

        // 判断五行生克关系
        if (isGenerating(wuXing, riGanWuXing)) {
            // 生我者为印星
            return yang == riGanYang ? "偏印" : "正印";
        } else if (isGenerating(riGanWuXing, wuXing)) {
            // 我生者为食伤
            return yang == riGanYang ? "食神" : "伤官";
        } else if (isRestraining(wuXing, riGanWuXing)) {
            // 克我者为官杀
            return yang == riGanYang ? "七杀" : "正官";
        } else if (isRestraining(riGanWuXing, wuXing)) {
            // 我克者为财星
            return yang == riGanYang ? "偏财" : "正财";
        }

        return "未知";
    }

    /**
     * 判断五行a是否生五行b (a生b)
     */
    private static boolean isGenerating(String a, String b) {
        // 相生关系：木生火，火生土，土生金，金生水，水生木
        return (a.equals("木") && b.equals("火")) ||
                (a.equals("火") && b.equals("土")) ||
                (a.equals("土") && b.equals("金")) ||
                (a.equals("金") && b.equals("水")) ||
                (a.equals("水") && b.equals("木"));
    }

    /**
     * 判断五行a是否克五行b (a克b)
     */
    private static boolean isRestraining(String a, String b) {
        // 相克关系：木克土，土克水，水克火，火克金，金克木
        return (a.equals("木") && b.equals("土")) ||
                (a.equals("土") && b.equals("水")) ||
                (a.equals("水") && b.equals("火")) ||
                (a.equals("火") && b.equals("金")) ||
                (a.equals("金") && b.equals("木"));
    }
}
