package com.example.shenjack.zhihudailyreader.data;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ShenJack on 2017/6/2.
 */
public class Extra {
    @SerializedName("long_comments")
    private int amountOfLongComments;

    @SerializedName("short_comments")
    private int amountOfShortComments;

    @SerializedName("comments")
    private int amountOfComments;

    @SerializedName("popularity")
    private int popularity;

}
