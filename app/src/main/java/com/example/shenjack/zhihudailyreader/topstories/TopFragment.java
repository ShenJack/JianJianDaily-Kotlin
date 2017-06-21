package com.example.shenjack.zhihudailyreader.topstories;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.stoylist.StoryListAdapterOnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;


public class TopFragment extends Fragment implements
        TopStoriesContract.View , StoryListAdapterOnClickListener{

    private TopStoriesContract.Presenter mTopStoriesPresenter;
    private List<TodayPosts.TopStoriesBean> mTopStories;
    private TopViewPagerAdapter mTopViewPagerAdapter;




    public TopFragment(){
        mTopStoriesPresenter = new TopStoreisPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        initView(view);

        mTopStoriesPresenter.loadTopStories();

        return view;

    }

    private void initView(View view) {
        mTopStories = new ArrayList<>();
        mTopViewPagerAdapter = new TopViewPagerAdapter(mTopStories,this);


        ViewPager viewPager = ButterKnife.findById(view,R.id.top_stories_viewpager);
        viewPager.setAdapter(mTopViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: "+ position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });





    }

    public static TopFragment newInstance() {

        Bundle args = new Bundle();

        TopFragment fragment = new TopFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void setPresenter(TopStoriesContract.Presenter presenter) {
        mTopStoriesPresenter = presenter;
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
        mTopViewPagerAdapter.setTopStories(stories);
    }

    @Override
    public void onClick(int storyId, String title) {
        mTopStoriesPresenter.openStoryDetail(storyId,title);
    }
}
