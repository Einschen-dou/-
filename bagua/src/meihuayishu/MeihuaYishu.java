package meihuayishu;


import java.util.Calendar;
import java.util.GregorianCalendar;

// 梅花易数时间起卦类
public class MeihuaYishu {
    // 地支对应的数字
    private static final int[] EARTHLY_BRANCHES = {4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3};
    // 八卦名称
    private static final String[] TRIGRAMS = {"乾", "兑", "离", "震", "巽", "坎", "艮", "坤"};

    public static void main(String[] args) {
        // 获取当前时间
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // 计算地支对应的数字
        int earthlyBranchYear = getEarthlyBranch(year);
        int earthlyBranchHour = getEarthlyBranch(hour);

        // 计算上卦、下卦和动爻
        int upperTrigram = getTrigram(earthlyBranchYear, month, day);
        int lowerTrigram = getTrigram(earthlyBranchYear, month, day, earthlyBranchHour);
        int movingLine = getMovingLine(earthlyBranchYear, month, day, earthlyBranchHour);

        // 输出结果
        System.out.println("上卦: " + TRIGRAMS[upperTrigram]);
        System.out.println("下卦: " + TRIGRAMS[lowerTrigram]);
        System.out.println("动爻: " + movingLine + "爻");
    }

    // 获取地支对应的数字
    private static int getEarthlyBranch(int yearOrHour) {
        if (yearOrHour >= 23 || yearOrHour < 1) {
            return EARTHLY_BRANCHES[11];
        } else if (yearOrHour >= 1 && yearOrHour < 3) {
            return EARTHLY_BRANCHES[0];
        } else if (yearOrHour >= 3 && yearOrHour < 5) {
            return EARTHLY_BRANCHES[1];
        } else if (yearOrHour >= 5 && yearOrHour < 7) {
            return EARTHLY_BRANCHES[2];
        } else if (yearOrHour >= 7 && yearOrHour < 9) {
            return EARTHLY_BRANCHES[3];
        } else if (yearOrHour >= 9 && yearOrHour < 11) {
            return EARTHLY_BRANCHES[4];
        } else if (yearOrHour >= 11 && yearOrHour < 13) {
            return EARTHLY_BRANCHES[5];
        } else if (yearOrHour >= 13 && yearOrHour < 15) {
            return EARTHLY_BRANCHES[6];
        } else if (yearOrHour >= 15 && yearOrHour < 17) {
            return EARTHLY_BRANCHES[7];
        } else if (yearOrHour >= 17 && yearOrHour < 19) {
            return EARTHLY_BRANCHES[8];
        } else if (yearOrHour >= 19 && yearOrHour < 21) {
            return EARTHLY_BRANCHES[9];
        } else {
            return EARTHLY_BRANCHES[10];
        }
    }

    // 计算卦数
    private static int getTrigram(int... numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum % 8;
    }

    // 计算动爻
    private static int getMovingLine(int... numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum % 6 + 1;
    }
}

