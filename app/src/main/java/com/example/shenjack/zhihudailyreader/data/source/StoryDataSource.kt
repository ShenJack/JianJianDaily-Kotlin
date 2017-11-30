package com.example.shenjack.zhihudailyreader.data.source

import com.example.shenjack.zhihudailyreader.data.*
import io.reactivex.Observable

/**
 * Created by ShenJack on 2017/6/1.
 */

interface StoryDataSource {


    val todayStories: io.reactivex.Observable<TodayStories>?

    val topStories: io.reactivex.Observable<List<TodayStories.TopStoriesBean>>?


    fun getBeforeStories(date: String): Observable<BeforeStories>?

    fun getStoryDetail(postId: String): Observable<Detail>?


    fun getNightStories(timestamp: Int): Observable<NightStories>?

    fun getFirstNightStories(): Observable<NightStories>?
}
