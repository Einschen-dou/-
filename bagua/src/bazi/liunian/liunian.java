package bazi.liunian;

import java.util.*;

// 修改类名，符合Java命名规范（首字母大写）
public class liunian {
    // 天干地支基础数据
    private static final String[] TIANGAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] DIZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    // 天干五行映射
    private static final Map<Character, String> TIAN_GAN_WU_XING = new HashMap<>();
    // 地支五行映射
    private static final Map<Character, String> DI_ZHI_WU_XING = new HashMap<>();
    // 天干阴阳（阳为true，阴为false）
    private static final Map<Character, Boolean> TIAN_GAN_YINYANG = new HashMap<>();
    // 地支阴阳
    private static final Map<Character, Boolean> DI_ZHI_YINYANG = new HashMap<>();

    static {
        // 初始化天干数据
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

        // 初始化地支数据
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

        DI_ZHI_YINYANG.put('子', true);
        DI_ZHI_YINYANG.put('丑', false);
        DI_ZHI_YINYANG.put('寅', true);
        DI_ZHI_YINYANG.put('卯', false);
        DI_ZHI_YINYANG.put('辰', true);
        DI_ZHI_YINYANG.put('巳', false);
        DI_ZHI_YINYANG.put('午', true);
        DI_ZHI_YINYANG.put('未', false);
        DI_ZHI_YINYANG.put('申', true);
        DI_ZHI_YINYANG.put('酉', false);
        DI_ZHI_YINYANG.put('戌', true);
        DI_ZHI_YINYANG.put('亥', false);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入四柱
        System.out.println("请输入四柱（年柱、月柱、日柱、时柱，空格分隔，如：丁亥 壬寅 甲子 丙寅）：");
        String input = scanner.nextLine();
        // 修复：使用trim()和\\s+处理任意空格分隔
        String[] pillars = input.trim().split("\\s+");

        if (pillars.length != 4) {
            System.out.println("四柱输入错误！");
            return;
        }

        // 输入各干支的十神
        Map<String, String> shiShenMap = new HashMap<>();
        String[] positions = {"年干", "年支", "月干", "月支", "日干", "日支", "时干", "时支"};
        System.out.println("\n请依次输入以下位置的十神（直接输入十神名称）：");
        for (String pos : positions) {
            System.out.print(pos + "(" + getGanZhiByPosition(pillars, pos) + ")：");
            shiShenMap.put(pos, scanner.nextLine().trim());
        }

        // 添加：输入各干支的五行属性
        Map<String, String> wuXingInputMap = new HashMap<>();
        System.out.println("\n请依次输入以下位置的五行（木、火、土、金、水）：");
        for (String pos : positions) {
            System.out.print(pos + "(" + getGanZhiByPosition(pillars, pos) + ")：");
            wuXingInputMap.put(pos, scanner.nextLine().trim());
        }

        // 获取日主（日干）信息
        char riGan = pillars[2].charAt(0);
        String riZhuWuXing = TIAN_GAN_WU_XING.get(riGan);
        boolean riZhuYang = TIAN_GAN_YINYANG.get(riGan);
        System.out.println("\n日主（日干）：" + riGan +
                "，五行：" + riZhuWuXing +
                "，阴阳：" + (riZhuYang ? "阳" : "阴"));

        // 输入目标年份
        System.out.println("\n请输入要查询的流年年份（如：2024）：");
        int year = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        // 1. 计算流年干支
        String liuNianGanZhi = getLiuNianGanZhi(year);
        System.out.println("\n" + year + "年流年干支：" + liuNianGanZhi);
        char lnTianGan = liuNianGanZhi.charAt(0);
        char lnDiZhi = liuNianGanZhi.charAt(1);

        // 2. 自动计算流年干支的十神和五行
        String lnTgWuXing = TIAN_GAN_WU_XING.get(lnTianGan);
        String lnTgShiShen = getShiShen(lnTianGan, true, riGan);

        String lnDzWuXing = DI_ZHI_WU_XING.get(lnDiZhi);
        String lnDzShiShen = getShiShen(lnDiZhi, false, riGan);

        System.out.println("流年天干" + lnTianGan + "：十神=" + lnTgShiShen + "，五行=" + lnTgWuXing);
        System.out.println("流年地支" + lnDiZhi + "：十神=" + lnDzShiShen + "，五行=" + lnDzWuXing);

        // 3. 使用用户输入的五行属性（替换原自动计算五行的代码）
        Map<String, String> wuXingMap = wuXingInputMap;

        // 4. 显示原命局信息
        System.out.println("\n===== 原命局信息 =====");
        for (String pos : positions) {
            System.out.println(pos + "：" + getGanZhiByPosition(pillars, pos) +
                    "，十神：" + shiShenMap.get(pos) +
                    "，五行：" + wuXingMap.get(pos));
        }

        // 5. 显示流年信息
        System.out.println("\n===== " + year + "年流年信息 =====");
        System.out.println("流年干支：" + liuNianGanZhi);
        System.out.println("流年天干：" + lnTianGan + "，十神：" + lnTgShiShen + "，五行：" + lnTgWuXing);
        System.out.println("流年地支：" + lnDiZhi + "，十神：" + lnDzShiShen + "，五行：" + lnDzWuXing);

        // 6. 分析五行生克影响
        analyzeWuXingImpact(lnTgWuXing, lnDzWuXing, wuXingMap);

        // 7. 分析十神影响
        analyzeShiShenImpact(lnTgShiShen, lnDzShiShen, shiShenMap);

        // 8. 分析地支刑冲合害
        analyzeXingChong(lnDiZhi, pillars);

        scanner.close();
    }

    // 根据位置获取对应的干支
    private static String getGanZhiByPosition(String[] pillars, String position) {
        switch (position) {
            case "年干": return String.valueOf(pillars[0].charAt(0));
            case "年支": return String.valueOf(pillars[0].charAt(1));
            case "月干": return String.valueOf(pillars[1].charAt(0));
            case "月支": return String.valueOf(pillars[1].charAt(1));
            case "日干": return String.valueOf(pillars[2].charAt(0));
            case "日支": return String.valueOf(pillars[2].charAt(1));
            case "时干": return String.valueOf(pillars[3].charAt(0));
            case "时支": return String.valueOf(pillars[3].charAt(1));
            default: return "";
        }
    }

    // 计算流年干支
    private static String getLiuNianGanZhi(int year) {
        int offset = (year - 3) % 60;
        if (offset < 0) offset += 60;
        int tgIndex = offset % 10;
        int dzIndex = offset % 12;
        return TIANGAN[tgIndex] + DIZHI[dzIndex];
    }

    // 计算十神
    private static String getShiShen(char ganZhi, boolean isTianGan, char riGan) {
        String riGanWx = TIAN_GAN_WU_XING.get(riGan);
        boolean riGanYang = TIAN_GAN_YINYANG.get(riGan);

        String wx = isTianGan ? TIAN_GAN_WU_XING.get(ganZhi) : DI_ZHI_WU_XING.get(ganZhi);
        boolean yang = isTianGan ? TIAN_GAN_YINYANG.get(ganZhi) : DI_ZHI_YINYANG.get(ganZhi);

        if (wx.equals(riGanWx)) {
            return yang == riGanYang ? "比肩" : "劫财";
        } else if (isSheng(wx, riGanWx)) {
            return yang == riGanYang ? "偏印" : "正印";
        } else if (isSheng(riGanWx, wx)) {
            return yang == riGanYang ? "食神" : "伤官";
        } else if (isKe(wx, riGanWx)) {
            return yang == riGanYang ? "七杀" : "正官";
        } else if (isKe(riGanWx, wx)) {
            return yang == riGanYang ? "偏财" : "正财";
        }
        return "未知";
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

    // 分析五行生克影响
    private static void analyzeWuXingImpact(String lnTgWx, String lnDzWx, Map<String, String> yuanMingWuXing) {
        System.out.println("\n===== 五行生克影响分析 =====");

        // 五行相生关系
        Map<String, String> sheng = new HashMap<>();
        sheng.put("木", "火");
        sheng.put("火", "土");
        sheng.put("土", "金");
        sheng.put("金", "水");
        sheng.put("水", "木");

        // 五行相克关系
        Map<String, String> ke = new HashMap<>();
        ke.put("木", "土");
        ke.put("土", "水");
        ke.put("水", "火");
        ke.put("火", "金");
        ke.put("金", "木");

        // 分析流年天干对原命局的影响
        for (Map.Entry<String, String> entry : yuanMingWuXing.entrySet()) {
            String pos = entry.getKey();
            String wx = entry.getValue();

            if (sheng.getOrDefault(lnTgWx, "").equals(wx)) {
                System.out.println("流年天干五行" + lnTgWx + "生" + pos + "五行" + wx + "，增强其力量");
            } else if (ke.getOrDefault(lnTgWx, "").equals(wx)) {
                System.out.println("流年天干五行" + lnTgWx + "克" + pos + "五行" + wx + "，削弱其力量");
            }

            if (sheng.getOrDefault(lnDzWx, "").equals(wx)) {
                System.out.println("流年地支五行" + lnDzWx + "生" + pos + "五行" + wx + "，增强其力量");
            } else if (ke.getOrDefault(lnDzWx, "").equals(wx)) {
                System.out.println("流年地支五行" + lnDzWx + "克" + pos + "五行" + wx + "，削弱其力量");
            }
        }
    }

    // 分析十神影响
    private static void analyzeShiShenImpact(String lnTgShiShen, String lnDzShiShen, Map<String, String> yuanMingShiShen) {
        System.out.println("\n===== 十神影响分析 =====");

        // 十神关系简要说明
        Map<String, String> shiShenEffect = new HashMap<>();
        shiShenEffect.put("比肩", "代表同辈、朋友，可能有合作或竞争");
        shiShenEffect.put("劫财", "代表同辈、竞争者，可能有财物损耗");
        shiShenEffect.put("食神", "代表才华、福气，利于发挥能力");
        shiShenEffect.put("伤官", "代表创新、变动，可能有突破或是非");
        shiShenEffect.put("正财", "代表正财、稳定收入，利于财运");
        shiShenEffect.put("偏财", "代表偏财、意外之财，可能有额外收入");
        shiShenEffect.put("正官", "代表事业、压力，利于职位提升");
        shiShenEffect.put("七杀", "代表挑战、机遇，可能有重大变动");
        shiShenEffect.put("正印", "代表贵人、庇护，利于学习、获得支持");
        shiShenEffect.put("偏印", "代表灵感、研究，利于思考、特殊机遇");

        // 流年十神本身的影响
        System.out.println("流年天干十神" + lnTgShiShen + "：" + shiShenEffect.getOrDefault(lnTgShiShen, "未知影响"));
        System.out.println("流年地支十神" + lnDzShiShen + "：" + shiShenEffect.getOrDefault(lnDzShiShen, "未知影响"));

        // 流年十神与原命局十神的互动
        for (Map.Entry<String, String> entry : yuanMingShiShen.entrySet()) {
            String pos = entry.getKey();
            String ss = entry.getValue();

            if (lnTgShiShen.equals(ss)) {
                System.out.println("流年天干十神与" + pos + "十神相同（" + ss + "），该十神力量增强");
            }
            if (lnDzShiShen.equals(ss)) {
                System.out.println("流年地支十神与" + pos + "十神相同（" + ss + "），该十神力量增强");
            }
        }
    }

    // 分析地支刑冲合害
    private static void analyzeXingChong(char lnDz, String[] pillars) {
        System.out.println("\n===== 地支刑冲合害分析 =====");
        // 原命局地支
        List<Character> yuanMingDiZhi = new ArrayList<>();
        yuanMingDiZhi.add(pillars[0].charAt(1)); // 年支
        yuanMingDiZhi.add(pillars[1].charAt(1)); // 月支
        yuanMingDiZhi.add(pillars[2].charAt(1)); // 日支
        yuanMingDiZhi.add(pillars[3].charAt(1)); // 时支

        // 冲的关系
        Map<Character, Character> chong = new HashMap<>();
        chong.put('子', '午');
        chong.put('午', '子');
        chong.put('卯', '酉');
        chong.put('酉', '卯');
        chong.put('寅', '申');
        chong.put('申', '寅');
        chong.put('巳', '亥');
        chong.put('亥', '巳');
        chong.put('辰', '戌');
        chong.put('戌', '辰');
        chong.put('丑', '未');
        chong.put('未', '丑');

        // 合的关系
        Map<Character, Character> he = new HashMap<>();
        he.put('子', '丑');
        he.put('丑', '子');
        he.put('寅', '亥');
        he.put('亥', '寅');
        he.put('卯', '戌');
        he.put('戌', '卯');
        he.put('辰', '酉');
        he.put('酉', '辰');
        he.put('巳', '申');
        he.put('申', '巳');
        he.put('午', '未');
        he.put('未', '午');

        for (char dz : yuanMingDiZhi) {
            if (chong.containsKey(lnDz) && chong.get(lnDz) == dz) {
                System.out.println("流年地支" + lnDz + "与原命局地支" + dz + "相冲，可能有变动、冲突");
            }
            if (he.containsKey(lnDz) && he.get(lnDz) == dz) {
                System.out.println("流年地支" + lnDz + "与原命局地支" + dz + "相合，可能有合作、融合");
            }
        }
    }
}
    