package com.example.shenjack.zhihudailyreader.topstories

import android.util.Log

import com.example.shenjack.zhihudailyreader.data.TodayStories
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository
import com.example.shenjack.zhihudailyreader.storydetail.DetailActivity
import com.example.shenjack.zhihudailyreader.stoylist.StoryListActivity

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable

import android.content.ContentValues.TAG

/**
 * Created by ShenJack on 2017/6/18.
 */

class TopStoreisPresenter(private val mTopFragment: TopStoriesContract.View) : TopStoriesContract.Presenter {

    private var mTopStories: List<TodayStories.TopStoriesBean> =  ArrayList()
    private val mStoryRepository: StoryRepository?

    init {
        mStoryRepository = StoryRepository.instance

    }

    override fun loadTopStories() {
        val todayStories = mStoryRepository!!.todayStories
        todayStories
                .map<List<TodayStories.TopStoriesBean>> { todayPosts -> todayPosts.top_storiesX }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<TodayStories.TopStoriesBean>> {
                    override fun onSubscribe(@NonNull d: Disposable) {

                    }

                    override fun onNext(@NonNull topStoriesBeen: List<TodayStories.TopStoriesBean>) {
                        mTopStories = topStoriesBeen
                    }

                    override fun onError(@NonNull e: Throwable) {
                    }

                    override fun onComplete() {

                        mTopFragment.showStories(mTopStories)
                    }
                })
    }

    override fun openStoryDetail(storyId: Int, title: String) {
        DetailActivity.startActivity(StoryListActivity.instance!!, storyId, title)
    }
}
