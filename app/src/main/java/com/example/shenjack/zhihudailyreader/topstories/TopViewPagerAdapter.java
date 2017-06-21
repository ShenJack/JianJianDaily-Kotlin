package com.example.shenjack.zhihudailyreader.topstories;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.StreamAssetPathFetcher;
import com.bumptech.glide.load.engine.GlideException;
import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.stoylist.StoryListActivity;
import com.example.shenjack.zhihudailyreader.stoylist.StoryListAdapterOnClickListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by ShenJack on 2017/6/19.
 */

public class TopViewPagerAdapter extends PagerAdapter {
    Context mContext;
    private StoryListAdapterOnClickListener storyListAdapterOnClickListener;

    private List<TodayPosts.TopStoriesBean> topStories;
    private List<View> views = new ArrayList<>();

    public TopViewPagerAdapter(List<TodayPosts.TopStoriesBean> topStories, StoryListAdapterOnClickListener newStoryListAdapterOnClickListener) {
        storyListAdapterOnClickListener = newStoryListAdapterOnClickListener;
        this.mContext = StoryListActivity.getInstance();
        this.topStories = topStories; Log.d(TAG, "TopViewPagerAdapter: "+topStories.toString());

        initPages();

    }

    @Override
    public int getCount() {
        return topStories.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

//        View view = views.get(position);
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_post_thumbnail,null);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;

    }


    public void setTopStories(List<TodayPosts.TopStoriesBean> topStories) {
        this.topStories = topStories;
        initPages();
        notifyDataSetChanged();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
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
