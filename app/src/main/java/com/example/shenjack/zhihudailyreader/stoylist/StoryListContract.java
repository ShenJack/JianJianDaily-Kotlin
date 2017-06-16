package com.example.shenjack.zhihudailyreader.stoylist;

import com.example.shenjack.zhihudailyreader.BaseView;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;

import java.util.List;

/**
 * Created by ShenJack on 2017/5/29.
 */

public interface StoryListContract {

    interface View extends BaseView<Presenter>{
        void showLoadingIndicator(boolean visible);

        void showStories(List<StoriesBean> stories);

        void showStoryDetail(StoriesBean story);

        void showLoadingError();

        void showDateSelector();


        void setStories(List<StoriesBean> mStories);

        String getDate();

        void notifyLoadingComplete();

        void notifyLoadingError();


    }

    interface Presenter {
        void getTodayStories(boolean forceUpdate);

        void fetchBeforeStories(String date);

        void loadStories(String data);

        void openStoryDetail(int storyId);

        void refreshStoryList();

    }
}
