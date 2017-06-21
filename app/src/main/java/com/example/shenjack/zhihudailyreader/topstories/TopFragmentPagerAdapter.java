package com.example.shenjack.zhihudailyreader.topstories;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.stoylist.StoryListAdapterOnClickListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by ShenJack on 2017/6/21.
 */

public class TopFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<TodayPosts.TopStoriesBean> topStories;
    Context mContext;
    private StoryListAdapterOnClickListener storyListAdapterOnClickListener;
    private List<View> views;

    public TopFragmentPagerAdapter(FragmentManager fm
            , Context context
            , List<TodayPosts.TopStoriesBean> topStories
            , StoryListAdapterOnClickListener storyListAdapterOnClickListener) {
        super(fm);
        mContext = context;
        this.topStories = topStories;
        this.storyListAdapterOnClickListener = storyListAdapterOnClickListener;
        views = new ArrayList<>();
        initPages();
    }

    @Override
    public Fragment getItem(int position) {
        return NewFragment.newInstance(views.get(position));
    }

    @Override
    public int getCount() {
        return topStories.size();
    }

    public void setTopStories(List<TodayPosts.TopStoriesBean> topStories) {
        this.topStories = topStories;
        initPages();
        notifyDataSetChanged();
    }

    private void initPages(){
        for (TodayPosts.TopStoriesBean topStoriesBean :
                topStories) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.top_thumbnail, null);
            ImageView headerImage = ButterKnife.findById(view, R.id.thumbnail_image);
            final String title = topStoriesBean.getTitle();
            final TextView titleTextView = ButterKnife.findById(view, R.id.title);
            final int storyId = topStoriesBean.getId();
            URL url = null;
            try {
                url = new URL(topStoriesBean.getImage());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            titleTextView.setText(title);

            Glide.with(mContext).load(url).into(headerImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storyListAdapterOnClickListener.onClick(storyId, title);
                }
            });

            Log.d(TAG, "initPages: title:" + title);

            views.add(view);
        }
    }
}
