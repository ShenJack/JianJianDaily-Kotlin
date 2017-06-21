package com.example.shenjack.zhihudailyreader.stoylist;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.topstories.TopFragment;
import com.example.shenjack.zhihudailyreader.topstories.TopFragmentPagerAdapter;
import com.example.shenjack.zhihudailyreader.topstories.TopStoreisPresenter;
import com.example.shenjack.zhihudailyreader.topstories.TopStoriesContract;
import com.example.shenjack.zhihudailyreader.topstories.TopViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;
import static com.example.shenjack.zhihudailyreader.Util.Util.getBeforeDate;

public class StoryListActivity extends AppCompatActivity implements
        TopStoriesContract.View, StoryListAdapterOnClickListener{

    private static StoryListActivity  instance;
    private AppBarLayout appBarLayout;
    private List<TodayPosts.TopStoriesBean> mTopStories;
    private TopFragmentPagerAdapter mTopFragmentPagerAdapter;
    private TopStoriesContract.Presenter mTopStoriesPresenter;
    private ViewPager viewPager;

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


        mTopStoriesPresenter = new TopStoreisPresenter(this);




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    private void initView(){




        List<Fragment> storyListFragments = new ArrayList<>();
        for (int i = 0; i < 7; i++) {

            String date = getBeforeDate(-i);
            StoryListFragment storyListFragment = StoryListFragment.newInstance(date);
            storyListFragments.add(storyListFragment);
        }



        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPager daysViewPager = ButterKnife.findById(this, R.id.viewpager);

        StoryListViewPagerAdapter storyListViewPagerAdapter = new StoryListViewPagerAdapter(fragmentManager,storyListFragments,this);

        daysViewPager.setAdapter(storyListViewPagerAdapter);
        daysViewPager.setOffscreenPageLimit(1);

        final TabLayout tabs = ButterKnife.findById(this,R.id.tabs);

        tabs.setupWithViewPager(daysViewPager);




        appBarLayout = ButterKnife.findById(this,R.id.list_app_bar);

//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//
//
//                Log.d("verticalOffSet", String.valueOf(verticalOffset));
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    int color = ContextCompat.getColor(getInstance(),R.color.colorPrimary);
//
//
//
//
//
//                    Window window = getWindow();
//                    if(Math.abs(verticalOffset)>=appBarLayout.getTotalScrollRange() - tabs.getHeight()){
//                        window.setStatusBarColor(ContextCompat.getColor(getInstance(),R.color.colorPrimaryDark));
//                    }
//                    else window.setStatusBarColor(ContextCompat.getColor(getInstance(),R.color.tranparent));
//                }
//            }
//        });
//

        mTopStories = new ArrayList<>();


        viewPager = ButterKnife.findById(this,R.id.topviewpager);
        mTopFragmentPagerAdapter= new TopFragmentPagerAdapter(getSupportFragmentManager()
                ,this,mTopStories,this);
        viewPager.setAdapter(mTopFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(1);

    }

    public static StoryListActivity getInstance() {

        return instance;
    }

    @Override
    public void setPresenter(TopStoriesContract.Presenter presenter) {

    }

    @Override
    public void showLoadingIndicator(boolean visible) {

    }

    @Override
    public void showStories(List<TodayPosts.TopStoriesBean> stories) {
        mTopStories =stories;
        setStories(stories);
    }

    @Override
    public void showStoryDetail(StoriesBean story) {

    }

    @Override
    public void setStories(List<TodayPosts.TopStoriesBean> stories) {
        Log.d(TAG, "setStories: "+stories.toString());
        mTopFragmentPagerAdapter.setTopStories(stories);
    }

    @Override
    public void onClick(int storyId, String title) {
        mTopStoriesPresenter.openStoryDetail(storyId,title);
    }

    @Override
    protected void onResume() {
        mTopStoriesPresenter.loadTopStories();
        super.onResume();
    }
}
