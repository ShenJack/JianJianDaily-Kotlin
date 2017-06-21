package com.example.shenjack.zhihudailyreader.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.stoylist.StoryListAdapterOnClickListener;

import butterknife.ButterKnife;

/**
 * Created by ShenJack on 2017/6/18.
 */

public class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public int  storyId;
        public TextView title;
        public ImageView imageView;
        StoryListAdapterOnClickListener mStoryListAdapterOnClickListener;
        public StoryViewHolder(View itemView , StoryListAdapterOnClickListener storyListAdapterOnClickListener) {
            super(itemView);
            title = ButterKnife.findById(itemView, R.id.title);
            imageView = ButterKnife.findById(itemView,R.id.thumbnail_image);
            mStoryListAdapterOnClickListener = storyListAdapterOnClickListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            mStoryListAdapterOnClickListener.onClick(getStoryId(), (String) title.getText());
        }
        public int getStoryId() {
            return storyId;
        }

}
