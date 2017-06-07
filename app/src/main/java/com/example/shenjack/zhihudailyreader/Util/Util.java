package com.example.shenjack.zhihudailyreader.Util;

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
}
