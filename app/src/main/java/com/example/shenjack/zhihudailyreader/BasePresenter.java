package com.example.shenjack.zhihudailyreader;

import java.io.Serializable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ShenJack on 2017/6/9.
 */


/**
 * Created by ShenJack on 2017/6/8.
 */

public abstract class BasePresenter {
    public void getPosts() {

    }

    public void start() {

    }

    public void executeHttpRequest(Observable<Serializable> observable) {
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Serializable>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Serializable serializable) {
                        onNext(serializable);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    protected abstract void onNext(Serializable serializable);

}

