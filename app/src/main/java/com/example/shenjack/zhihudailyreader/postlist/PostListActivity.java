package com.example.shenjack.zhihudailyreader.postlist;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class PostListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            fragments.add(new PostListFragment());
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPager viewPager = ButterKnife.findById(this, R.id.viewpager);

        PostListViewPagerAdapter postListViewPagerAdapter = new PostListViewPagerAdapter(fragmentManager,fragments,this);

        viewPager.setAdapter(postListViewPagerAdapter);

        TabLayout tabs = ButterKnife.findById(this,R.id.tabs);

        tabs.setupWithViewPager(viewPager);

    }
}
