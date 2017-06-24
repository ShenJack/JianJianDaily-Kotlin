package com.example.shenjack.zhihudailyreader.obsevable

import com.example.shenjack.zhihudailyreader.data.StoriesBean

import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Created by ShenJack on 2017/6/14.
 */

class StoryObservable : Observable<StoriesBean>() {
    override fun subscribeActual(observer: Observer<in StoriesBean>) {

    }
}
