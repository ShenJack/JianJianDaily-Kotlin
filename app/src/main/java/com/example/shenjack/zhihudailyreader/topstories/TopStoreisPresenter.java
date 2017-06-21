package com.example.shenjack.zhihudailyreader.topstories;

import android.util.Log;

import com.example.shenjack.zhihudailyreader.data.Detail;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository;
import com.example.shenjack.zhihudailyreader.storydetail.DetailActivity;
import com.example.shenjack.zhihudailyreader.stoylist.StoryListActivity;

import java.net.IDN;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by ShenJack on 2017/6/18.
 */

public class TopStoreisPresenter implements TopStoriesContract.Presenter {

    private List<TodayPosts.TopStoriesBean> mTopStories;
    private StoryRepository mStoryRepository;
    private TopStoriesContract.View mTopFragment;

    public TopStoreisPresenter(TopStoriesContract.View view) {
        mStoryRepository = StoryRepository.getInstance();
        mTopFragment = view;

    }

    @Override
    public void loadTopStories() {
        Observable<TodayPosts> todayStories =  mStoryRepository.getTodayStories();
        Log.d(TAG, "loadTopStories: ");
        todayStories
                .map(new Function<TodayPosts, List<TodayPosts.TopStoriesBean>>() {
                    @Override
                    public List<TodayPosts.TopStoriesBean> apply(@NonNull TodayPosts todayPosts) throws Exception {
                        return todayPosts.getTop_storiesX();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TodayPosts.TopStoriesBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<TodayPosts.TopStoriesBean> topStoriesBeen) {
                        mTopStories = topStoriesBeen;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: 123");
                    }

                    @Override
                    public void onComplete() {

                        mTopFragment.showStories(mTopStories);
                        Log.d(TAG, "onComplete: "+mTopStories.toString());
                    }
                });
    }

    @Override
    public void openStoryDetail(int storyId, String title) {
        DetailActivity.startActivity(StoryListActivity.getInstance(), storyId,title);
    }
}
