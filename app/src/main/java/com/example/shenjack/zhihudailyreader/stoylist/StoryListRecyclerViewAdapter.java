package com.example.shenjack.zhihudailyreader.stoylist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by ShenJack on 2017/5/30.
 */

public class StoryListRecyclerViewAdapter extends RecyclerView.Adapter<StoryListRecyclerViewAdapter.StoryViewHolder> {


    Context mContext;
    List<StoriesBean> mStoryList;
    final private StoryListAdapterOnClickListener storyListAdapterOnClickListener;


    public StoryListRecyclerViewAdapter(Context context,List<StoriesBean> newStoryList, StoryListAdapterOnClickListener storyListAdapterOnClickListener) {
        mContext = context;
        mStoryList = newStoryList;
        this.storyListAdapterOnClickListener = storyListAdapterOnClickListener;
    }

    public void setStoryList(List<StoriesBean> newStoryList){
        mStoryList = newStoryList;
        notifyDataSetChanged();
    }


    public void updateStoriesBeanList(List<StoriesBean> newStoriesBeanList){
        setStoryList(newStoriesBeanList);
        notifyDataSetChanged();
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null) Log.e("error","error");
        Log.d("pass","pass");
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_post_thumbnail,null);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        StoriesBean storiesBean = mStoryList.get(position);
        holder.title.setText(storiesBean.getTitle());
        URL url = null;
        try {
            url = new URL(storiesBean.getImages().get(0));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (holder.imageView == null) {
            Log.e(TAG, "onBindViewHolder: " );
        }

        Glide.with(mContext).load(url).into(holder.imageView);

        Log.d(TAG, "onBindViewHolder: hh"+url);

        holder.storyId =storiesBean.getId();
    }


    @Override
    public int getItemCount() {
        return mStoryList.size();
    }

    class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        int  storyId;
        TextView title;
        ImageView imageView;
        public StoryViewHolder(View itemView) {
            super(itemView);
            title = ButterKnife.findById(itemView, R.id.title);
            imageView = ButterKnife.findById(itemView,R.id.thumbnail_image);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            storyListAdapterOnClickListener.onClick(getStoryId());
        }
        public int getStoryId() {
            return storyId;
        }
    }

}
