package ziweidoushu;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 定义宫位类
class Palace {
    private String name;
    private List<String> stars;
    // 用于存储宫位与其他宫位的关系，例如夫妻宫与命宫的关系
    private Map<String, String> relationships;

    public Palace(String name) {
        this.name = name;
        this.stars = new ArrayList<>();
        this.relationships = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getStars() {
        return stars;
    }

    public void addStar(String star) {
        stars.add(star);
    }

    public void addRelationship(String relatedPalace, String relationship) {
        relationships.put(relatedPalace, relationship);
    }

    public Map<String, String> getRelationships() {
        return relationships;
    }
}

// 定义星曜类，包含星曜的基本属性和作用
class Star {
    private String name;
    private String type; // 例如甲级星、乙级星等
    private String nature; // 星曜的性质，如吉星、凶星等
    private int power; // 星曜的力量值

    public Star(String name, String type, String nature, int power) {
        this.name = name;
        this.type = type;
        this.nature = nature;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getNature() {
        return nature;
    }

    public int getPower() {
        return power;
    }
}

// 定义天干地支数组
public class ZiWeiDouShu {
    private static final String[] heavenlyStems = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] earthlyBranches = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    // 预先定义所有星曜
    private static final Star[] allStars = {
            new Star("紫微星", "甲级星", "吉星", 100),
            new Star("天机星", "甲级星", "善星", 80),
            new Star("太阳星", "甲级星", "吉星", 90),
            // 省略更多星曜定义
    };

    // 计算天干
    public static String calculateHeavenlyStem(int year) {
        return heavenlyStems[(year - 4) % 10];
    }

    // 计算地支
    public static String calculateEarthlyBranch(int year) {
        return earthlyBranches[(year - 4) % 12];
    }

    // 计算命宫
    public static Palace calculateLifePalace(int year, int month, int day, int hour) {
        String heavenlyStem = calculateHeavenlyStem(year);
        String earthlyBranch = calculateEarthlyBranch(year);
        int earthlyBranchIndex = getIndexOfArray(earthlyBranches, earthlyBranch);
        int monthIndex = month - 1;
        int hourIndex = getHourIndex(hour);

        int lifePalaceIndex = (earthlyBranchIndex + monthIndex + hourIndex) % 12;
        String lifePalaceName = earthlyBranches[lifePalaceIndex];
        Palace lifePalace = new Palace(lifePalaceName);

        // 安置主要星曜到命宫
        placeMainStars(lifePalace, year);
        return lifePalace;
    }

    // 安置主要星曜的方法，这里简单示例几个星曜的安置规则
    private static void placeMainStars(Palace palace, int year) {
        String earthlyBranch = calculateEarthlyBranch(year);
        if ("子".equals(palace.getName())) {
            palace.addStar(allStars[0].getName()); // 紫微星
        }
        if ("丑".equals(palace.getName()) && "甲".equals(calculateHeavenlyStem(year))) {
            palace.addStar(allStars[1].getName()); // 天机星
        }
        // 可添加更多星曜安置规则
    }

    // 构建完整命盘，包含所有宫位及相互关系
    public static List<Palace> buildFullChart(int year, int month, int day, int hour) {
        List<Palace> chart = new ArrayList<>();
        Palace lifePalace = calculateLifePalace(year, month, day, hour);
        chart.add(lifePalace);

        // 构建其他宫位，这里简单示例构建夫妻宫
        int spousePalaceIndex = (getIndexOfArray(earthlyBranches, lifePalace.getName()) + 6) % 12;
        String spousePalaceName = earthlyBranches[spousePalaceIndex];
        Palace spousePalace = new Palace(spousePalaceName);
        chart.add(spousePalace);

        // 建立宫位关系，例如命宫与夫妻宫的关系
        lifePalace.addRelationship(spousePalace.getName(), "夫妻");
        spousePalace.addRelationship(lifePalace.getName(), "夫妻");

        // 为夫妻宫安置星曜，简单示例
        placeMainStars(spousePalace, year);

        // 可继续构建更多宫位及关系
        return chart;
    }

    private static int getIndexOfArray(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private static int getHourIndex(int hour) {
        // 假设每两小时为一个时辰，对应地支顺序
        return (hour + 1) / 2 % 12;
    }

    public static void main(String[] args) {
        int year = 1990;
        int month = 5;
        int day = 15;
        int hour = 10;
        List<Palace> chart = buildFullChart(year, month, day, hour);

        for (Palace palace : chart) {
            System.out.println("宫位：" + palace.getName());
            System.out.println("星曜：" + palace.getStars());
            System.out.println("关系：" + palace.getRelationships());
            System.out.println();
        }
    }
}


