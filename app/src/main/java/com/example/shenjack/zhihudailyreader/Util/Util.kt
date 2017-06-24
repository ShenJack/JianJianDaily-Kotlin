package com.example.shenjack.zhihudailyreader.Util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * Created by ShenJack on 2017/6/7.
 */

object Util {
    fun <T> checkNotNull(reference: T?): T {
        if (reference == null) {
            throw NullPointerException()
        } else {
            return reference
        }
    }

    val todayDate: String
        get() {
            val date = Date()
            val formattor = SimpleDateFormat("yyyyMMdd")

            return formattor.format(date)
        }

    fun getBeforeDate(daysBefore: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, daysBefore)
        val formattor = SimpleDateFormat("yyyyMMdd")

        return formattor.format(calendar.time)
    }

    fun generateUrl(storyId: Int): String {
        return "https://daily.zhihu.com/story/" + storyId
    }
}
