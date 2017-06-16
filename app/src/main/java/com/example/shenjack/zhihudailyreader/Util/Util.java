package com.example.shenjack.zhihudailyreader.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ShenJack on 2017/6/7.
 */

public class Util {
    public static <T> T checkNotNull(T reference) {
        if(reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static String getTodayDate(){
        Date date = new Date();
        SimpleDateFormat formattor = new SimpleDateFormat("yyyyMMdd");

        return formattor.format(date);
    }

    public static String getBeforeDate(int daysBefore){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,daysBefore);
        SimpleDateFormat formattor = new SimpleDateFormat("yyyyMMdd");

        return formattor.format(calendar.getTime());
    }
}
