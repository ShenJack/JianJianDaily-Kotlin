package com.example.shenjack.zhihudailyreader.data.source

import com.example.shenjack.zhihudailyreader.data.BeforeStories
import com.example.shenjack.zhihudailyreader.data.Detail
import com.example.shenjack.zhihudailyreader.data.StoriesBean
import com.example.shenjack.zhihudailyreader.data.TodayStories
import com.example.shenjack.zhihudailyreader.data.source.local.StoryLocalDataSource
import com.example.shenjack.zhihudailyreader.data.source.remote.StoryRemoteDataSource

import java.util.ArrayList

import com.example.shenjack.zhihudailyreader.Util.Util.checkNotNull
import io.reactivex.Observable


/**
 * Created by ShenJack on 2017/6/1.
 */

class StoryRepository(postLocalDataSource: StoryDataSource?, postRemoteDataSource: StoryDataSource?) : StoryDataSource {
    override val topStories: Observable<List<TodayStories.TopStoriesBean>>
        get() = mStoryRemoteDataSource.topStories!! //To change initializer of created properties use File | Settings | File Templates.
    override val todayStories: Observable<TodayStories>
        get() = mStoryRemoteDataSource.todayStories!! //To change initializer of created properties use File | Settings | File Templates.

    private val mStoryLocalDataSource: StoryDataSource

    private val mStoryRemoteDataSource: StoryDataSource

    init {
        mStoryLocalDataSource = postLocalDataSource!!
        mStoryRemoteDataSource = postRemoteDataSource!!
    }




    override fun getBeforeStories(date: String): io.reactivex.Observable<BeforeStories>? {
        return mStoryRemoteDataSource.getBeforeStories(date)
    }


    override fun getStoryDetail(storyId: String): io.reactivex.Observable<Detail>? {
        return mStoryRemoteDataSource.getStoryDetail(storyId)
    }




    interface PostRepositoryObserver {

        fun onPostChanged()

    }

    companion object {

        var instance: StoryRepository? = null
        get() {
            if(field == null) instance = StoryRepository(StoryLocalDataSource.instance,StoryRemoteDataSource.instance)
            return field
        }


        fun getInstance(storyLocalDataSource: StoryLocalDataSource,
                        postRemoteDataSource: StoryRemoteDataSource): StoryRepository? {
            if (instance == null) {
                instance = StoryRepository(storyLocalDataSource, postRemoteDataSource)
            }

            return instance
        }

    }

}
