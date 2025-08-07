package qimendunjia;





import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 奇门遁甲核心类
 * 实现了排盘、九星、八门、八神等核心功能
 */
public class QiMenDunJia {
    // 天干
    private static final String[] HEAVENLY_STEMS = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    // 地支
    private static final String[] EARTHLY_BRANCHES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    // 九星
    private static final String[] NINE_STARS = {"天蓬", "天芮", "天冲", "天辅", "天禽", "天心", "天柱", "天任", "天英"};
    // 八门
    private static final String[] EIGHT_DOORS = {"休门", "生门", "伤门", "杜门", "景门", "死门", "惊门", "开门"};
    // 八神
    private static final String[] EIGHT_SPIRITS = {"值符", "腾蛇", "太阴", "六合", "白虎", "玄武", "九地", "九天"};
    // 九宫格方位
    private static final String[] PALACE_DIRECTIONS = {"坎一宫", "坤二宫", "震三宫", "巽四宫", "中五宫", "乾六宫", "兑七宫", "艮八宫", "离九宫"};


    // 节气
    private static final String[] SOLAR_TERMS = {
            "冬至", "小寒", "大寒", "立春", "雨水", "惊蛰",
            "春分", "清明", "谷雨", "立夏", "小满", "芒种",
            "夏至", "小暑", "大暑", "立秋", "处暑", "白露",
            "秋分", "寒露", "霜降", "立冬", "小雪", "大雪"
    };

    // 三元九运
    private static final int[][] THREE_CYCLES = {
            {1, 2, 3},  // 上元
            {4, 5, 6},  // 中元
            {7, 8, 9}   // 下元
    };


    private String[] stars;           // 九星
    private String[] doors;           // 八门
    private String[] spirits;         // 八神
    private int currentJiaZi;         // 当前甲子
    private int currentDun;           // 当前遁
    private int[] tianPan;            // 天盘
    private int[] diPan;              // 地盘
    private int[] renPan;             // 人盘
    private int[] shenPan;            // 神盘
    private int yearGan;              // 年干
    private int yearZhi;              // 年支
    private int monthGan;             // 月干
    private int monthZhi;             // 月支
    private int dayGan;               // 日干
    private int dayZhi;               // 日支
    private int hourGan;              // 时干
    private int hourZhi;              // 时支
    private int currentSolarTerm;     // 当前节气
    private int currentCycle;         // 当前元
    private int currentYun;           // 当前运

    /**
     * 构造函数，根据日期时间排盘
     */
    public QiMenDunJia(LocalDateTime dateTime) {
        initialize(dateTime);
    }

    /**
     * 初始化排盘
     */
    private void initialize(LocalDateTime dateTime) {

        stars = new String[9];
        doors = new String[9];
        spirits = new String[9];
        tianPan = new int[9];
        diPan = new int[9];
        renPan = new int[9];
        shenPan = new int[9];

        // 计算干支
        int[] ganZhi = calculateGanZhi(dateTime);
        yearGan = ganZhi[0];
        yearZhi = ganZhi[1];
        monthGan = ganZhi[2];
        monthZhi = ganZhi[3];
        dayGan = ganZhi[4];
        dayZhi = ganZhi[5];
        hourGan = ganZhi[6];
        hourZhi = ganZhi[7];

        // 计算节气
        currentSolarTerm = calculateSolarTerm(dateTime);

        // 计算三元九运
        calculateCycleAndYun(dateTime);

        // 计算局数
        currentDun = calculateDun(dateTime);

        // 确定旬首
        currentJiaZi = determineXunShou(hourGan, hourZhi);

        // 排地盘
        arrangeDiPan();

        // 排天盘
        arrangeTianPan();

        // 排九星
        arrangeStars();

        // 排八门
        arrangeDoors();

        // 排八神
        arrangeSpirits();

        // 排人盘
        arrangeRenPan();

        // 排神盘
        arrangeShenPan();
    }

    /**
     * 计算干支
     */
    private int[] calculateGanZhi(LocalDateTime dateTime) {
        int[] result = new int[8];

        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();

        // 计算年干支
        int baseYear = 4; // 公元前4年为甲子年
        int offset = (year - baseYear) % 60;
        if (offset < 0) offset += 60;
        yearGan = offset % 10;
        yearZhi = offset % 12;

        // 计算月干支
        // 寅月为正月，故加2
        monthGan = (yearGan * 2 + month + 2) % 10;
        monthZhi = (month + 1) % 12;

        // 计算日干支
        // 以1900年1月31日为基准(甲子日)
        long days = daysBetween(LocalDateTime.of(1900, 1, 31, 0, 0), dateTime);

        dayGan = (int) ((days % 10 + 10) % 10);
        dayZhi = (int) ((days % 12 + 12) % 12);

        // 计算时干支
        hourGan = (dayGan * 2 + hour / 2) % 10;
        hourZhi = hour / 2;

        result[0] = yearGan;
        result[1] = yearZhi;
        result[2] = monthGan;
        result[3] = monthZhi;
        result[4] = dayGan;
        result[5] = dayZhi;
        result[6] = hourGan;
        result[7] = hourZhi;

        return result;
    }

    /**
     * 计算两个日期之间的天数
     */
    private long daysBetween(LocalDateTime start, LocalDateTime end) {
        return java.time.Duration.between(start, end).toDays();
    }

    /**
     * 确定旬首
     */
    private int determineXunShou(int gan, int zhi) {
        // 计算天干与地支的差值
        int diff = (gan - zhi + 12) % 12;

        // 根据差值确定旬首，使用增强 switch
        return switch (diff) {
            case 0 -> 0; // 甲子旬
            case 2 -> 1; // 甲戌旬
            case 4 -> 2; // 甲申旬
            case 6 -> 3; // 甲午旬
            case 8 -> 4; // 甲辰旬
            case 10 -> 5; // 甲寅旬
            default -> 0;
        };
    }

    /**
     * 计算节气
     */
    private int calculateSolarTerm(LocalDateTime dateTime) {
        int month = dateTime.getMonthValue();
        // 移除未使用变量
        // int year = dateTime.getYear();
        // int day = dateTime.getDayOfMonth();


        return (month - 1) * 2;
    }

    /**
     * 计算三元九运
     */
    private void calculateCycleAndYun(LocalDateTime dateTime) {
        int year = dateTime.getYear();

        // 简化计算，实际应根据三元九运的周期精确计算
        // 这里假设1864-1923为上元，1924-1983为中元，1984-2043为下元
        if (year >= 1864 && year <= 1923) {
            currentCycle = 0; // 上元
            currentYun = (year - 1864) / 20;
        } else if (year >= 1924 && year <= 1983) {
            currentCycle = 1; // 中元
            currentYun = (year - 1924) / 20;
        } else if (year >= 1984 && year <= 2043) {
            currentCycle = 2; // 下元
            currentYun = (year - 1984) / 20;
        } else {
            // 默认值
            currentCycle = 2;
            currentYun = 0;
        }
    }

    /**
     * 计算局数
     */
    private int calculateDun(LocalDateTime dateTime) {
        int month = dateTime.getMonthValue();

        // 确定阴阳遁
        boolean isYangDun = isYangDun(dateTime);

        // 计算节气对应的局数
        int baseDun = calculateBaseDun(dateTime);

        // 计算具体局数
        return isYangDun ? baseDun : 10 - baseDun;
    }

    /**
     * 判断阴阳遁
     */
    private boolean isYangDun(LocalDateTime dateTime) {
        int month = dateTime.getMonthValue();

        // 简化判断，冬至后为阳遁，夏至后为阴遁
        return month >= 1 && month <= 6;
    }

    /**
     * 计算基础局数
     */
    private int calculateBaseDun(LocalDateTime dateTime) {
        int month = dateTime.getMonthValue();

        // 简化计算，实际应根据节气精确计算
        // 这里仅作示例，返回月份对应的基础局数
        int baseDun = month % 9;
        return baseDun == 0 ? 9 : baseDun;
    }

    /**
     * 排地盘
     */
    private void arrangeDiPan() {
        // 地盘固定顺序
        diPan[0] = 1; // 坎一宫
        diPan[1] = 2; // 坤二宫
        diPan[2] = 3; // 震三宫
        diPan[3] = 4; // 巽四宫
        diPan[4] = 5; // 中五宫
        diPan[5] = 6; // 乾六宫
        diPan[6] = 7; // 兑七宫
        diPan[7] = 8; // 艮八宫
        diPan[8] = 9; // 离九宫
    }

    /**
     * 排天盘
     */
    private void arrangeTianPan() {
        // 根据旬首和局数排天盘
        int zhiFu = getZhiFuPalace(); // 值符所在宫位

        // 阳遁顺行，阴遁逆行
        boolean isYangDun = currentDun <= 5;

        // 排九星
        for (int i = 0; i < 9; i++) {
            int index;
            if (isYangDun) {
                index = (zhiFu + i) % 9;
            } else {
                index = (zhiFu - i + 9) % 9;
            }
            tianPan[i] = diPan[index];
        }
    }

    /**
     * 获取值符所在宫位
     */
    private int getZhiFuPalace() {
        // 根据旬首确定值符所在宫位
        // 简化计算，实际应根据奇门遁甲规则确定
        return currentJiaZi % 9;
    }

    /**
     * 排九星
     */
    private void arrangeStars() {
        // 根据天盘九星位置排列
        for (int i = 0; i < 9; i++) {
            stars[i] = NINE_STARS[tianPan[i] - 1];
        }
    }

    /**
     * 排八门
     */
    private void arrangeDoors() {
        // 根据值使随时支排八门
        int zhiShi = getZhiShiDoor(); // 值使所在门

        // 阳遁顺行，阴遁逆行
        boolean isYangDun = currentDun <= 5;

        // 排八门
        for (int i = 0; i < 8; i++) {
            int index;
            if (isYangDun) {
                index = (zhiShi + i) % 8;
            } else {
                index = (zhiShi - i + 8) % 8;
            }
            doors[i] = EIGHT_DOORS[index];
        }
        doors[8] = doors[1]; // 中五宫寄于坤二宫
    }

    /**
     * 获取值使所在门
     */
    private int getZhiShiDoor() {
        // 根据旬首确定值使所在门
        // 简化计算，实际应根据奇门遁甲规则确定
        return currentJiaZi % 8;
    }

    /**
     * 排八神
     */
    private void arrangeSpirits() {
        // 根据值符随时干排八神
        int zhiFu = getZhiFuPalace(); // 值符所在宫位

        // 阳遁顺行，阴遁逆行
        boolean isYangDun = currentDun <= 5;

        // 排八神
        for (int i = 0; i < 8; i++) {
            int index;
            if (isYangDun) {
                index = (zhiFu + i) % 9;
            } else {
                index = (zhiFu - i + 9) % 9;
            }
            spirits[index] = EIGHT_SPIRITS[i];
        }
        spirits[8] = spirits[0]; // 中五宫寄于坤二宫
    }

    /**
     * 排人盘
     */
    private void arrangeRenPan() {
        // 人盘即八门的位置
        for (int i = 0; i < 9; i++) {
            renPan[i] = i + 1;
        }
    }

    /**
     * 排神盘
     */
    private void arrangeShenPan() {
        // 神盘即八神的位置
        for (int i = 0; i < 9; i++) {
            shenPan[i] = i + 1;
        }
    }

    /**
     * 获取排盘信息
     */
    public String getChartInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("奇门遁甲排盘信息\n");
        sb.append("-----------------------------\n");
        sb.append("当前时间: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        sb.append("四柱八字: ").append(HEAVENLY_STEMS[yearGan]).append(EARTHLY_BRANCHES[yearZhi]).append("年 ")
                .append(HEAVENLY_STEMS[monthGan]).append(EARTHLY_BRANCHES[monthZhi]).append("月 ")
                .append(HEAVENLY_STEMS[dayGan]).append(EARTHLY_BRANCHES[dayZhi]).append("日 ")
                .append(HEAVENLY_STEMS[hourGan]).append(EARTHLY_BRANCHES[hourZhi]).append("时\n");
        sb.append("当前节气: ").append(SOLAR_TERMS[currentSolarTerm]).append("\n");
        sb.append("三元九运: ").append(getCycleName()).append(" ").append(getYunName()).append("\n");
        sb.append("当前局数: ").append(currentDun).append("局\n");
        sb.append("当前旬首: ").append(HEAVENLY_STEMS[currentJiaZi]).append("\n");
        sb.append("-----------------------------\n");

        // 九宫格排列
        for (int i = 0; i < 9; i++) {
            sb.append(PALACE_DIRECTIONS[i]).append("\n");
            sb.append("天盘: ").append(tianPan[i]).append("\n");
            sb.append("地盘: ").append(diPan[i]).append("\n");
            sb.append("九星: ").append(stars[i]).append("\n");
            sb.append("八门: ").append(doors[i]).append("\n");
            sb.append("八神: ").append(spirits[i]).append("\n");
            sb.append("-----------------------------\n");
        }

        return sb.toString();
    }

    /**
     * 获取元的名称
     */
    private String getCycleName() {
        return switch (currentCycle) { // 使用增强 switch
            case 0 -> "上元";
            case 1 -> "中元";
            case 2 -> "下元";
            default -> "未知";
        };
    }

    /**
     * 获取运的名称
     */
    private String getYunName() {
        if (currentYun >= 0 && currentYun < 3) {
            return THREE_CYCLES[currentCycle][currentYun] + "运";
        }
        return "未知";
    }

    /**
     * 主方法，用于测试
     */
    public static void main(String[] args) {
        // 使用当前时间排盘
        LocalDateTime now = LocalDateTime.now();
        QiMenDunJia qmdj = new QiMenDunJia(now);
        System.out.println(qmdj.getChartInfo());

    }
}
