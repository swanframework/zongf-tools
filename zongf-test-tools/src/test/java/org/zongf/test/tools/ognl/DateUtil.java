package org.zongf.test.tools.ognl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zongf
 * @date 2020-06-03
 */
public class DateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String now() {
        return format(new Date(), DATETIME_FORMAT);
    }

    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String sayHello(Integer id) {
        return "Integer:" + id;
    }

    public static String sayHello(Double id) {
        return "Double:" + id;
    }

}
