package com.example.shenjack.zhihudailyreader.stoylist

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * Created by ShenJack on 2017/5/29.
 */

internal class StoryListViewPagerAdapter(fm: FragmentManager, private val mFragments: List<Fragment>, private val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {
        val date = Calendar.getInstance()
        date.add(Calendar.DAY_OF_WEEK, -position)


        when (position) {
            0 -> return "今天"
            1 -> return "昨天"
            2 -> return "前天"
            else -> return DateFormat.getDateInstance().format(date.time)
        }
    }

    companion object {
        private val PAGE_COUNT = 7
    }
}