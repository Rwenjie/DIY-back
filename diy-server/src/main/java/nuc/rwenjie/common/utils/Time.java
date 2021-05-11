package nuc.rwenjie.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Rwenjie
 * @ClassName Time
 * @Description TODO 时间
 * @Date 2021/5/10 20:56
 **/


public class Time {
    
    public static String NowTime() {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        return ft.format(dNow);
    }

}
