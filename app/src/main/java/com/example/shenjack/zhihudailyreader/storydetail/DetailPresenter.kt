package com.example.shenjack.zhihudailyreader.storydetail

import android.util.Log

import com.example.shenjack.zhihudailyreader.BasePresenter
import com.example.shenjack.zhihudailyreader.data.Detail
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository
import com.example.shenjack.zhihudailyreader.stoylist.StoryListContract

import java.io.Serializable
import java.security.KeyStore

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable

/**
 * Created by ShenJack on 2017/6/17.
 */

class DetailPresenter(private val mStoryRepositiry: StoryRepository, private val mStoryDetailView: DetailContract.View) : BasePresenter(), DetailContract.Presenter {
    private var mDetail: Detail? = null

    override fun loadStoryDetail(id: String) {
        mStoryDetailView.showLoadingIndicator(true)

        val detailObservable : Observable<Detail>? = mStoryRepositiry.getStoryDetail(id)

        detailObservable!!
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Detail> {
                    override fun onSubscribe(@NonNull d: Disposable) {

                    }

                    override fun onNext(@NonNull detail: Detail) {
                        mDetail = detail
                    }

                    override fun onError(@NonNull e: Throwable) {

                    }

                    override fun onComplete() {
                        mStoryDetailView.showLoadingIndicator(false)
                        mStoryDetailView.showStoryDetail(mDetail!!)
                    }
                })

    }

    override fun onNext(serializable: Serializable) {

    }
}
