package com.example.shenjack.zhihudailyreader.data.source;

import com.google.gson.annotations.SerializedName;

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
