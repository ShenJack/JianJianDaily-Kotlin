package com.example.shenjack.zhihudailyreader

import java.io.Serializable

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ShenJack on 2017/6/9.
 */


/**
 * Created by ShenJack on 2017/6/8.
 */

abstract class BasePresenter {
    fun getPosts() {

    }

    fun start() {

    }

    open fun executeHttpRequest(observable: Observable<Serializable>) {
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Serializable> {
                    override fun onSubscribe(@NonNull d: Disposable) {

                    }

                    override fun onNext(@NonNull serializable: Serializable) {
                        onNext(serializable)
                    }

                    override fun onError(@NonNull e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }

    protected abstract fun onNext(serializable: Serializable)

}

