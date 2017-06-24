package com.example.shenjack.zhihudailyreader.data.source.remote


import com.example.shenjack.zhihudailyreader.data.BeforeStories
import com.example.shenjack.zhihudailyreader.data.Detail
import com.example.shenjack.zhihudailyreader.data.Extra
import com.example.shenjack.zhihudailyreader.data.TodayStories

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ShenJack on 2017/6/2.
 */

interface ZhihuDailyApi {

    //


    @GET("4/news/{id}")
    fun getStoryDetail(@Path("id") id: String): Observable<Detail>

    //    prefetch launch image
    //    @GET("7/prefetch-launch-images/1080*1920")
    //    Call

    //    get latest news
    @get:GET("4/news/latest")
    val todayStories: Observable<TodayStories>

    @get:GET("4/news/latest")
    val topStories: Observable<List<TodayStories.TopStoriesBean>>


    //    get past news
    @GET("4/news/before/{date}")
    fun getBeforeStories(@Path("date") date: String): Observable<BeforeStories>?


    //    get news' extra
    @GET("4/story-extra/{id}")
    fun getStoryExtra(@Path("id") id: Int): Observable<Extra>

    companion object {

        val BASE_URL = "http://news-at.zhihu.com/api/"
    }

    //    get themes

    //    get themes' content


}
