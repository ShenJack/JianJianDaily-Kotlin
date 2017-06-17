package com.example.shenjack.zhihudailyreader.stoylist;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

import static com.example.shenjack.zhihudailyreader.Util.Util.getBeforeDate;

public class StoryListActivity extends AppCompatActivity {

    private static StoryListActivity  instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_list);

        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);



        initView();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    private void initView(){



        setTitle("Reader");

        List<Fragment> storyListFragments = new ArrayList<>();
        for (int i = 0; i < 7; i++) {

            String date = getBeforeDate(-i);
            StoryListFragment storyListFragment = StoryListFragment.newInstance(date);
            storyListFragments.add(storyListFragment);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPager viewPager = ButterKnife.findById(this, R.id.viewpager);

        StoryListViewPagerAdapter storyListViewPagerAdapter = new StoryListViewPagerAdapter(fragmentManager,storyListFragments,this);

        viewPager.setAdapter(storyListViewPagerAdapter);
        viewPager.setOffscreenPageLimit(1);

        TabLayout tabs = ButterKnife.findById(this,R.id.tabs);

        tabs.setupWithViewPager(viewPager);

    }

    public static StoryListActivity getInstance() {

        return instance;
    }
}
