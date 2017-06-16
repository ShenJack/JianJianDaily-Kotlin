package com.example.shenjack.zhihudailyreader.stoylist;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ShenJack on 2017/5/29.
 */

class StoryListViewPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 7;
    private List<Fragment> mFragments;


    private Context context;

    public StoryListViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
        super(fm);
        this.context = context;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_WEEK, -position);


        switch (position) {
            case 0:
                return "今天";
            case 1:
                return "昨天";
            case 2:
                return "前天";
            default:
                return DateFormat.getDateInstance().format(date.getTime());
        }
    }
}