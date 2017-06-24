package com.example.shenjack.zhihudailyreader.data.source.remote

import com.example.shenjack.zhihudailyreader.data.BeforeStories
import com.example.shenjack.zhihudailyreader.data.Detail
import com.example.shenjack.zhihudailyreader.data.StoriesBean
import com.example.shenjack.zhihudailyreader.data.TodayStories
import com.example.shenjack.zhihudailyreader.data.source.StoryDataSource
import io.reactivex.Observable

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit


/**
 * Created by ShenJack on 2017/6/5.
 */

class StoryRemoteDataSource : StoryDataSource {

    private val service: ZhihuDailyApi?

    init {
        val retrofit = ZhihuDailyService.retrofitInstance
        service = ZhihuDailyService.zhihuDailyServiceInstance
    }

    override val todayStories: Observable<TodayStories>
        get() = service!!.todayStories.subscribeOn(io.reactivex.schedulers.Schedulers.io())

    override val topStories: io.reactivex.Observable<List<TodayStories.TopStoriesBean>>
        get() = service!!.topStories.subscribeOn(Schedulers.io())

    override fun getBeforeStories(date: String): io.reactivex.Observable<BeforeStories>? {
        return service!!.getBeforeStories(date)!!.subscribeOn(Schedulers.io())
    }

    override fun getStoryDetail(storyId: String): io.reactivex.Observable<Detail>? {

        return service!!.getStoryDetail(storyId).subscribeOn(Schedulers.io())
    }

    companion object {
        var instance: StoryDataSource? = null
            get() {
                if (field == null) instance = StoryRemoteDataSource()
                return field
            }


    }
}
