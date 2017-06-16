package com.example.shenjack.zhihudailyreader.storydetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.stoylist.StoryListContract;

import java.util.List;

public class StoryDetailActivity extends AppCompatActivity implements StoryListContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
    }


    @Override
    public void setPresenter(StoryListContract.Presenter presenter) {

    }

    @Override
    public void showLoadingIndicator(boolean visible) {

    }

    @Override
    public void showStories(List<StoriesBean> stories) {

    }

    @Override
    public void showStoryDetail(StoriesBean story) {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void showDateSelector() {

    }

    @Override
    public void setStories(List<StoriesBean> mStories) {

    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public void notifyLoadingComplete() {

    }

    @Override
    public void notifyLoadingError() {

    }
}
