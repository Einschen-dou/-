package xiaoliuren;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

public class Xiaoliuren {
    public static void main(String[] args) {
        // 获取当前日期和时间
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // 将当前日期和时间转换为农历日期和时辰
        int lunarMonth = currentDate.get(ChronoField.MONTH_OF_YEAR);
        int lunarDay = currentDate.get(ChronoField.DAY_OF_MONTH);
        int lunarHour = currentTime.get(ChronoField.HOUR_OF_DAY) / 2 + 1;

        // 小六壬起卦
        String[] guaXiang = {"大安：身不动时，五行属木，颜色青色，方位东方，临青龙。代表一切静止、平安、吉祥，问事主吉，如出行平安，谋事易成，求财可得。",
                "留连：人未归时，五行属水，颜色黑色，方位北方，临玄武。象征事情会有延迟、纠缠，问事多为不利，如失物难寻，出行遇阻，求财难成。",
                "速喜：人即至时，五行属火，颜色红色，方位南方，临朱雀。意味着事情会快速发展且有喜事，如问官事有理，求财有喜，寻人可见。",
                "赤口：官事凶时，五行属金，颜色白色，方位西方，临白虎。表示会有口舌是非、争斗，诸事不利，如出行有灾，求财无利，谨防小人。",
                "小吉：人来喜时，五行属木，临六合。代表有小的吉祥、顺利，多有和合之事，如出行顺利，交易可成，求财可得。",
                "空亡：音信稀时，五行属土，颜色黄色，方位中央，临勾陈。寓意诸事无成、落空，如求财无利，寻人不见，出行有失。"};

        int result = (lunarMonth + lunarDay + lunarHour) % 6;
        System.out.println("小六壬起卦结果为：" + guaXiang[result]);
    }
}
