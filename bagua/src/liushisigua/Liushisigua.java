package liushisigua;


import java.util.Random;

/**
 * 该类用于生成和解读卦象。
 */

public class Liushisigua {
    /**
     * 主函数，程序入口。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 创建一个Random对象，用于生成随机数
        Random random = new Random();
        // 创建一个StringBuilder对象，用于存储生成的卦象
        StringBuilder gua = new StringBuilder();

        // 生成6个爻，每个爻由3个随机数组成
        for (int i = 0; i < 6; i++) {
            gua.append(getYao(random));
        }

        // 从下往上输出卦象
        for (int i = gua.length() - 1; i >= 0; i--) {
            System.out.print(gua.charAt(i));
        }
        System.out.println();

        // 解读卦象
        String guaInterpretation = interpretGua(gua.toString());
        System.out.println("卦象解读：" + guaInterpretation);

        // 六十四卦卦象解读
        String sixtyFourGuaInterpretation = interpretSixtyFourGua(gua.toString());
        if (sixtyFourGuaInterpretation!= null &&!sixtyFourGuaInterpretation.isEmpty()) {
            System.out.println("六十四卦卦象解读：" + sixtyFourGuaInterpretation);
        } else {
            System.out.println("未匹配到对应的六十四卦卦象解读");
        }
    }
    /**
     * 生成一个爻，由3个随机数组成。
     *
     * @param random Random对象，用于生成随机数
     * @return 返回生成的爻，0代表阴爻，1代表阳爻
     */
    private static String getYao(Random random) {
        int yangCount = 0;
        // 生成3个随机数，统计其中1的个数
        for (int j = 0; j < 3; j++) {
            // 生成0或1，0代表阴面，1代表阳面
            int side = random.nextInt(2);
            if (side == 1) {
                yangCount++;
            }
        }
        // 根据1的个数返回对应的爻
        if (yangCount == 3) {
            return "1";
        } else if (yangCount == 0) {
            return "0";
        } else if (yangCount == 2) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * 解读卦象。
     *
     * @param gua 要解读的卦象
     * @return 返回卦象的解读结果
     */
    private static String interpretGua(String gua) {
        // 如果卦象中同时包含1和0，说明有阴阳变化
        if (gua.contains("1") && gua.contains("0")) {
            return "此卦象暗示变化与转折，需灵活应对各种情况。";
        } else if (gua.contains("1")) {
            // 如果卦象中只包含1，说明全是阳爻
            return "卦中有阳爻，意味着积极向上的力量，但也要注意物极必反。";
        } else if (gua.contains("0")) {
            // 如果卦象中只包含0，说明全是阴爻
            return "卦中有阴爻，可能会面临一些困境，但困境中也蕴含着转机。";
        } else if (gua.replace("1", "").length() == 0) {
            // 如果卦象中所有的0都被替换为空字符串后长度为0，说明全是1，即全阳之卦
            return "全阳之卦，展现出强大的阳刚之气，行事果断易成功。";
        } else if (gua.replace("0", "").length() == 0) {
            // 如果卦象中所有的1都被替换为空字符串后长度为0，说明全是0，即全阴之卦
            return "全阴之卦，代表柔顺包容，但也需注意过于保守。";
        } else {
            // 如果以上条件都不满足，说明卦象中阴阳爻数量相等，即阴阳平衡
            return "此卦象表示阴阳平衡，事物处于平稳发展阶段。";
        }
    }

    // 定义枚举表示六十四卦
    private enum SixtyFourGua {
        QIAN("111111", "乾卦，象征天，刚健中正，自强不息。"),
        KUN("000000", "坤卦，象征地，柔顺包容，厚德载物。"),
        TUN("111000", "屯卦，象征初生，困难重重但充满希望，宜守正待机。"),
        MENG("000111", "蒙卦，象征蒙昧，宜启蒙教育，培养品德。"),
        XU("111110", "需卦，象征等待，时机未到，需耐心等待。"),
        SONG("000001", "讼卦，象征争讼，宜避免争端，以和为贵。"),
        SHI("111001", "师卦，象征军队，兴师动众，需师出有名。"),
        BI("000110", "比卦，象征亲比，团结互助，相互依附。"),
        XIAO_XU("111101", "小畜卦，象征小有蓄积，不可贪多，适可而止。"),
        LV("000010", "履卦，象征履行，遵循礼仪，小心行事。"),
        TAI("111100", "泰卦，象征通泰，天地交泰，吉祥顺利。"),
        PI("000011", "否卦，象征闭塞，天地不交，需谨慎行事。"),
        TONG_REN("111011", "同人卦，象征和同于人，团结众人，求同存异。"),
        DA_YOU("000100", "大有卦，象征大有收获，富足昌盛，需谦虚谨慎。"),
        QIAN_GUA("111010", "谦卦，象征谦虚，谦虚待人，必有收获。"),
        YU("000101", "豫卦，象征安乐，居安思危，不可懈怠。"),
        SUI("111011", "随卦，象征随从，随从正道，顺势而为。"),
        GU("000010", "蛊卦，象征整治，革故鼎新，去除弊端。"),
        LIN("111010", "临卦，象征监临，以正道监临，关怀下属。"),
        GUAN("000101", "观卦，象征观察，观察时势，审时度势。"),
        SHI_HE("111110", "噬嗑卦，象征咬合，明辨是非，去除阻碍。"),
        Bi("000001", "贲卦，象征文饰，修饰外表，注重内涵。"),
        BO("111001", "剥卦，象征剥落，阴气盛极，阳气将生。"),
        FU("000110", "复卦，象征回复，阳气回复，生机再现。"),
        WU_WANG("111011", "无妄卦，象征不妄为，遵循正道，不可妄动。"),
        DA_CHU("000100", "大畜卦，象征大为蓄积，厚积薄发，培养品德。"),
        YI("111010", "颐卦，象征颐养，保养身体，修养品德。"),
        DA_GUO("000101", "大过卦，象征大为过甚，应把握平衡，防止过度。"),
        KAN("111001", "坎卦，象征陷险，陷入困境，需勇敢面对。"),
        LI("000110", "离卦，象征附丽，依附光明，追求美好。"),
        XIAN("111010", "咸卦，象征感应，相互感应，心心相印。"),
        HENG("000101", "恒卦，象征恒久，坚守正道，持之以恒。"),
        DUN("111011", "遁卦，象征退避，时机不利，宜退而自保。"),
        DA_ZHUANG("000100", "大壮卦，象征强盛，盛极则衰，需谨慎行事。"),
        JIN("111101", "晋卦，象征晋升，积极进取，前途光明。"),
        MING_YI("000010", "明夷卦，象征光明受损，韬光养晦，等待时机。"),
        JIA_REN("111010", "家人卦，象征家庭，注重家庭，和睦相处。"),
        KUI("000101", "睽卦，象征乖离，意见不合，需求同存异。"),
        JIAN("111001", "蹇卦，象征艰难，处境艰难，需坚守正道。"),
        JIE("000110", "解卦，象征解除，解除困境，获得自由。"),
        SUN("111011", "损卦，象征减损，减少损失，适当舍弃。"),
        Yi("000100", "益卦，象征增益，增益自身，帮助他人。"),
        GUAI("111101", "夬卦，象征决断，果断决策，去除小人。"),
        GOU("000010", "姤卦，象征相遇，机缘巧合，相遇相知。"),
        CUI("111110", "萃卦，象征聚集，人才聚集，共创事业。"),
        SHENG("000001", "升卦，象征上升，步步高升，顺势发展。"),
        KUN_GUA("111001", "困卦，象征困穷，陷入困境，需坚守信念。"),
        JING("000110", "井卦，象征水井，滋养万物，默默奉献。"),
        GE("111100", "革卦，象征变革，顺应时势，进行变革。"),
        DING("000011", "鼎卦，象征鼎器，稳重可靠，权威象征。"),
        ZHEN("111010", "震卦，象征震动，震惊警惕，保持清醒。"),
        GEN("000101", "艮卦，象征止息，适可而止，坚守本分。"),
        JIAN_GUA("111011", "渐卦，象征渐进，循序渐进，稳步发展。"),
        GUI_ME("000100", "归妹卦，象征嫁妹，遵循正道，婚姻美满。"),
        FENG("111101", "丰卦，象征丰盛，繁荣昌盛，保持谦虚。"),
        LV_GUA("000010", "旅卦，象征旅行，外出远行，谨慎小心。"),
        XUN("111010", "巽卦，象征顺从，顺从自然，顺势而为。"),
        DUI("000101", "兑卦，象征喜悦，心情愉悦，和谐相处。"),
        HUAN("111011", "涣卦，象征涣散，消除涣散，凝聚力量。"),
        JIE_GUA("000100", "节卦，象征节制，节制欲望，适可而止。"),
        ZHONG_FU("111010", "中孚卦，象征诚信，诚实守信，立身之本。"),
        XIAO_GUO("000101", "小过卦，象征小有过越，注意言行，避免过错。"),
        JI_JI("111011", "既济卦，象征已经成功，保持警惕，防止变故。"),
        WEI_JI("000100", "未济卦，象征尚未成功，继续努力，充满希望。");

        private final String code;
        private final String interpretation;

        SixtyFourGua(String code, String interpretation) {
            this.code = code;
            this.interpretation = interpretation;
        }

        public static String getInterpretation(String code) {
            for (SixtyFourGua gua : values()) {
                if (gua.code.equals(code)) {
                    return gua.interpretation;
                }
            }
            return "";
        }
    }

    private static String interpretSixtyFourGua(String gua) {
        String normalizedGua = gua.replace("\n", "");
        return SixtyFourGua.getInterpretation(normalizedGua);
    }
}
