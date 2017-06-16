package com.example.shenjack.zhihudailyreader.stoylist;

import android.support.annotation.NonNull;

import com.example.shenjack.zhihudailyreader.BasePresenter;
import com.example.shenjack.zhihudailyreader.data.BeforePosts;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository;

import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


import static com.example.shenjack.zhihudailyreader.Util.Util.checkNotNull;
import static com.example.shenjack.zhihudailyreader.Util.Util.getTodayDate;

/**
 * Created by ShenJack on 2017/6/9.
 */

public class StoryListPresenter extends BasePresenter implements StoryListContract.Presenter {

    @NonNull
    private StoryRepository mStoryRepositiry;

    @NonNull
    private StoryListContract.View mStoriesView;

    private List<StoriesBean> mStories;

    @Override
    public void executeHttpRequest(Observable<Serializable> observable) {
        super.executeHttpRequest(observable);
    }

    @Override
    protected void onNext(Serializable serializable) {

    }

    public StoryListPresenter(StoryRepository storyRepository,
                              StoryListContract.View view) {
        mStoryRepositiry = checkNotNull(storyRepository);

        mStoriesView = checkNotNull(view);
    }

    @Override
    public void getTodayStories(boolean forceUpdate) {

        Observable<TodayPosts> todayPostsObservable = mStoryRepositiry.getTodayPosts();
        todayPostsObservable
                .map(new Function<TodayPosts, List<StoriesBean>>() {
                    @Override
                    public List<StoriesBean> apply(@io.reactivex.annotations.NonNull TodayPosts todayPosts) throws Exception {
                        return todayPosts.getStoriesX();
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StoriesBean>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mStoriesView.notifyLoadingError();
                    }

                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(List<StoriesBean> stories) {
                        mStories = stories;
                        mStoriesView.showLoadingIndicator(false);
                        mStoriesView.setStories(mStories);
                    }
                });
    }

    @Override
    public void fetchBeforeStories(String date) {

        Observable<BeforePosts> todayPostsObservable = mStoryRepositiry.getBeforePosts(date);
//        if(todayPostsObservable!=null)Log.d("BeforePosts","------>"+todayPostsObservable.toString());
//        else Log.d(TAG, "loadStories: Null");
        todayPostsObservable
                .map(new Function<BeforePosts, List<StoriesBean>>() {
                    @Override
                    public List<StoriesBean> apply(@io.reactivex.annotations.NonNull BeforePosts beforePosts) throws Exception {
                        return beforePosts.getStories();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StoriesBean>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List<StoriesBean> stories) {
                        mStories = stories;
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        mStoriesView.notifyLoadingError();
                    }

                    @Override
                    public void onComplete() {
                        mStoriesView.showLoadingIndicator(false);
                        mStoriesView.showStories(mStories);
                    }
                });
    }

    @Override
    public void loadStories(String date) {
//        mStoriesView.showLoadingIndicator(true);

        if(getTodayDate().equals(date)){
            getTodayStories(true);
        }
        else fetchBeforeStories(date);
    }

    @Override
    public void openStoryDetail(int storyId) {

    }

    @Override
    public void refreshStoryList() {
        loadStories(mStoriesView.getDate());
    }

}