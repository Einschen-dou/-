package bazi.sizhu;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class sizhu {
    // 天干数组
    private static final String[] TIANGAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    // 地支数组
    private static final String[] DIZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    // 月份地支数组，正月为寅，依次类推
    private static final String[] MONTH_DIZHI = {"寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥", "子", "丑"};
    // 五虎遁年起月诀映射，用于根据年干确定月干
    private static final Map<Character, Integer> YEAR_MAP = new HashMap<>();
    // 五鼠遁日起时诀映射，用于根据日干确定时干
    private static final Map<Character, Integer> DAY_MAP = new HashMap<>();

    static {
        // 初始化五虎遁年起月诀映射
        YEAR_MAP.put('甲', 2);
        YEAR_MAP.put('乙', 4);
        YEAR_MAP.put('丙', 6);
        YEAR_MAP.put('丁', 8);
        YEAR_MAP.put('戊', 10);
        YEAR_MAP.put('己', 2);
        YEAR_MAP.put('庚', 4);
        YEAR_MAP.put('辛', 6);
        YEAR_MAP.put('壬', 8);
        YEAR_MAP.put('癸', 10);

        // 初始化五鼠遁日起时诀映射
        DAY_MAP.put('甲', 0);
        DAY_MAP.put('乙', 2);
        DAY_MAP.put('丙', 4);
        DAY_MAP.put('丁', 6);
        DAY_MAP.put('戊', 8);
        DAY_MAP.put('己', 0);
        DAY_MAP.put('庚', 2);
        DAY_MAP.put('辛', 4);
        DAY_MAP.put('壬', 6);
        DAY_MAP.put('癸', 8);
    }

    /**
     * 根据年份获取年柱
     * @param year 年份
     * @return 年柱，如 "甲辰"
     */
    public static String getYearColumn(int year) {
        int tgIndex = (year - 3) % 10;
        int dzIndex = (year - 3) % 12;
        return TIANGAN[tgIndex] + DIZHI[dzIndex];
    }

    /**
     * 根据年柱和月份获取月柱
     * @param yearColumn 年柱
     * @param month 月份
     * @return 月柱，如 "丁卯"
     */
    public static String getMonthColumn(String yearColumn, int month) {
        int tgIndex = YEAR_MAP.get(yearColumn.charAt(0)) + month - 1;
        tgIndex = tgIndex % 10;
        int dzIndex = month - 1;
        return TIANGAN[tgIndex] + MONTH_DIZHI[dzIndex];
    }

    /**
     * 根据蔡勒公式计算日柱，此处简化处理
     * 实际应用中需要完整实现蔡勒公式并进行精确计算
     * @param year 年份
     * @param month 月份
     * @param day 日期
     * @return 日柱，如 "丙午"
     */
    public static String getDayColumn(int year, int month, int day) {
        // 蔡勒公式核心逻辑省略，这里简单假设一个固定结果
        int tgIndex = 0;
        int dzIndex = 0;
        return TIANGAN[tgIndex] + DIZHI[dzIndex];
    }

    /**
     * 根据日柱和时辰获取时柱
     * @param dayColumn 日柱
     * @param hour 时辰
     * @return 时柱，如 "戊子"
     */
    public static String getHourColumn(String dayColumn, int hour) {
        int tgIndex = DAY_MAP.get(dayColumn.charAt(0)) + hour / 2;
        tgIndex = tgIndex % 10;
        int dzIndex = hour / 2;
        return TIANGAN[tgIndex] + DIZHI[dzIndex];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入出生年份：");
        int year = scanner.nextInt();

        System.out.print("请输入出生月份：");
        int month = scanner.nextInt();

        System.out.print("请输入出生日期：");
        int day = scanner.nextInt();

        System.out.print("请输入出生时辰（0 - 23）：");
        int hour = scanner.nextInt();

        // 计算年柱
        String yearColumn = getYearColumn(year);
        // 计算月柱
        String monthColumn = getMonthColumn(yearColumn, month);
        // 计算日柱
        String dayColumn = getDayColumn(year, month, day);
        // 计算时柱
        String hourColumn = getHourColumn(dayColumn, hour);

        System.out.println("八字为：" + yearColumn + " " + monthColumn + " " + dayColumn + " " + hourColumn);

        scanner.close();
    }
}
