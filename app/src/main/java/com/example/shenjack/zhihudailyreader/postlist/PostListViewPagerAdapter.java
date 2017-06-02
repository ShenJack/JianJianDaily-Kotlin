package com.example.shenjack.zhihudailyreader.postlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ShenJack on 2017/5/29.
 */

class PostListViewPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 7;
    private List<Fragment> mFragments;


    private Context context;

    public PostListViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
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
        date.add(Calendar.DAY_OF_WEEK,-position);

        if(position==0){
            return "Today";
        }else {
            return DateFormat.getDateInstance().format(date.getTime()).substring(5);
        }
    }
}
