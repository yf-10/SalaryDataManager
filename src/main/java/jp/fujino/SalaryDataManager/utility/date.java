package jp.fujino.SalaryDataManager.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class date {

    private static final String dateFormat = "yyyy/MM/dd HH:mm:ss";

    public static Date getNowTimestamp() {
        return new Date();
    }

}
