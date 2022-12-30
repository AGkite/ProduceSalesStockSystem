package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getDateTimeNow() {
        Date time =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
        return sdf.format(time);
    }
}

