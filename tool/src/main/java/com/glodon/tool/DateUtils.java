package com.glodon.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 日期工具
 *
 * */
public class DateUtils {
    /*
     * 将时间转换为时间戳
     */
    public static long dateToTimeMillis(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }







    public static String timeMillis2Date(long selectedTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(selectedTime);

        return simpleDateFormat.format(date);
    }

    public static String getDefaultDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());

        return simpleDateFormat.format(date);
    }
    public final static String YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";
    public final static String YYYY_MM_DD_HH_MM="yyyy-MM-dd HH:mm";
    public final static String YYYY_MM_DD_HH="yyyy-MM-dd HH";
    public final static String YYYY_MM_DD="yyyy-MM-dd";

    @Deprecated
    public static String getDate(String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date=new Date(System.currentTimeMillis());

        return simpleDateFormat.format(date);
    }
    public static String getDate(String format,long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date=new Date(time);

        return simpleDateFormat.format(date);
    }


}
