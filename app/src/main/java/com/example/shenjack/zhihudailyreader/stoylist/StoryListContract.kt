package com.example.shenjack.zhihudailyreader.stoylist

import com.example.shenjack.zhihudailyreader.BaseView
import com.example.shenjack.zhihudailyreader.data.StoriesBean

/**
 * Created by ShenJack on 2017/5/29.
 */

interface StoryListContract {

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator(visible: Boolean)

        fun showStories(stories: List<StoriesBean>)

        fun showStoryDetail(story: StoriesBean)

        fun showLoadingError()

        fun showDateSelector()

        var date:String

        fun setStories(stories: List<StoriesBean>)


        fun notifyLoadingComplete()

        fun notifyLoadingError()

    }

    interface Presenter {
        fun getTodayStories(forceUpdate: Boolean)

        fun fetchBeforeStories(date: String)

        fun loadStories(data: String)

        fun openStoryDetail(storyId: Int, title: String)

        fun refreshStoryList()

    }
}
