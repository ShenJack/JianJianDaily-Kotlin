package com.example.shenjack.zhihudailyreader.stoylist

import com.example.shenjack.zhihudailyreader.BasePresenter
import com.example.shenjack.zhihudailyreader.Util.Util
import com.example.shenjack.zhihudailyreader.data.StoriesBean
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository
import com.example.shenjack.zhihudailyreader.storydetail.DetailActivity

import java.io.Serializable

import io.reactivex.Observable
import io.reactivex.Observer

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable


import com.example.shenjack.zhihudailyreader.Util.Util.checkNotNull
import com.example.shenjack.zhihudailyreader.data.TodayStories
import io.reactivex.functions.Function
import io.reactivex.rxkotlin.subscribeBy


/**
 * Created by ShenJack on 2017/6/9.
 */

class StoryListPresenter(storyRepository: StoryRepository?,
                         view: StoryListContract.View) : BasePresenter(), StoryListContract.Presenter {

    private val mStoryRepositiry: StoryRepository

    private val mStoriesView: StoryListContract.View

    private var mStories: List<StoriesBean>? = null

    override fun executeHttpRequest(observable: Observable<Serializable>) {
        super.executeHttpRequest(observable)
    }

    override fun onNext(serializable: Serializable) {

    }

    init {
        mStoryRepositiry = checkNotNull(storyRepository)

        mStoriesView = checkNotNull<StoryListContract.View>(view)
    }

    override fun getTodayStories(forceUpdate: Boolean) {

        val todayPostsObservable = mStoryRepositiry.todayStories

        todayPostsObservable
                .observeOn(AndroidSchedulers.mainThread())
                .map({ t -> t.storiesX!! })
                .subscribe(object : Observer<List<StoriesBean>> {
                    override fun onSubscribe(@io.reactivex.annotations.NonNull d: Disposable) {

                    }

                    override fun onNext(@io.reactivex.annotations.NonNull stories: List<StoriesBean>) {
                        mStories = stories
                    }

                    override fun onError(@io.reactivex.annotations.NonNull e: Throwable) {
                        mStoriesView.notifyLoadingError()
                    }

                    override fun onComplete() {
                        mStoriesView.showLoadingIndicator(false)
                        mStoriesView.showStories(mStories!!)
                    }
                });
    }

    override fun fetchBeforeStories(date: String) {

        val todayPostsObservable = mStoryRepositiry.getBeforeStories(date)
        //        if(todayPostsObservable!=null)Log.d("BeforeStories","------>"+todayPostsObservable.toString());
        //        else Log.d(TAG, "loadStories: Null");
        if (todayPostsObservable != null) {
            todayPostsObservable
                    .map { t -> t.stories!!}
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<List<StoriesBean>> {
                        override fun onSubscribe(@io.reactivex.annotations.NonNull d: Disposable) {

                        }

                        override fun onNext(@io.reactivex.annotations.NonNull stories: List<StoriesBean>) {
                            mStories = stories
                        }

                        override fun onError(@io.reactivex.annotations.NonNull e: Throwable) {
                            mStoriesView.notifyLoadingError()
                        }

                        override fun onComplete() {
                            mStoriesView.showLoadingIndicator(false)
                            mStoriesView.showStories(mStories!!)
                        }
                    })
        }
    }

    override fun loadStories(date: String) {
        //        mStoriesView.showLoadingIndicator(true);

        if (Util.todayDate == date) {
            getTodayStories(true)
        } else
            fetchBeforeStories(date)
    }

    override fun openStoryDetail(storyId: Int, title: String) {
        DetailActivity.startActivity(StoryListActivity.instance!!, storyId, title)
    }

    override fun refreshStoryList() {
        loadStories(mStoriesView.date)
    }

}