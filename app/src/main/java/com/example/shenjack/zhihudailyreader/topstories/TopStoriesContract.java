package com.example.shenjack.zhihudailyreader.topstories;

import com.example.shenjack.zhihudailyreader.BaseView;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;

import java.util.List;

/**
 * Created by ShenJack on 2017/6/18.
 */

public interface TopStoriesContract {

        interface View extends BaseView<TopStoriesContract.Presenter> {

            void showLoadingIndicator(boolean visible);

            void showStories(List<TodayPosts.TopStoriesBean> stories);

            void showStoryDetail(StoriesBean story);

            void setStories(List<TodayPosts.TopStoriesBean> stories);

        }

        interface Presenter {
            void loadTopStories();

            void openStoryDetail(int storyId, String title);

        }
}
