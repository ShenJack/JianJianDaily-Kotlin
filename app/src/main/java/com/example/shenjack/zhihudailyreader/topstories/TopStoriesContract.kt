package com.example.shenjack.zhihudailyreader.topstories

import com.example.shenjack.zhihudailyreader.BaseView
import com.example.shenjack.zhihudailyreader.data.StoriesBean
import com.example.shenjack.zhihudailyreader.data.TodayStories

/**
 * Created by ShenJack on 2017/6/18.
 */

interface TopStoriesContract {

    interface View : BaseView<TopStoriesContract.Presenter> {

        fun showLoadingIndicator(visible: Boolean)

        fun showStories(stories: List<TodayStories.TopStoriesBean>)

        fun showStoryDetail(story: StoriesBean)

        fun setStories(stories: List<TodayStories.TopStoriesBean>)

    }

    interface Presenter {
        fun loadTopStories()

        fun openStoryDetail(storyId: Int, title: String)

    }
}
