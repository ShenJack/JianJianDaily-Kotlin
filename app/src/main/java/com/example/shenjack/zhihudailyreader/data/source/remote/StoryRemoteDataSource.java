package com.example.shenjack.zhihudailyreader.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.data.BeforePosts;
import com.example.shenjack.zhihudailyreader.data.Detail;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.data.source.StoryDataSource;

import java.util.List;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * Created by ShenJack on 2017/6/5.
 */

public class StoryRemoteDataSource implements StoryDataSource {

    private ZhihuDailyApi service;
    private static StoryDataSource instance;


    public StoryRemoteDataSource() {
        Retrofit retrofit = ZhihuDailyService.getRetrofitInstance();
        service = ZhihuDailyService.getZhihuDailyServiceInstance();
    }

    public static StoryDataSource getInstance() {
        if(instance==null)instance = new StoryRemoteDataSource();
        return instance;
    }

    @Nullable
    @Override
    public io.reactivex.Observable<TodayPosts> getTodayStories() {
        return service.getTodayStories().subscribeOn(io.reactivex.schedulers.Schedulers.io());
    }

    @Override
    public io.reactivex.Observable<List<TodayPosts.TopStoriesBean>> getTopStories() {
        return service.getTopStories().subscribeOn(Schedulers.io());
    }

    @Override
    public io.reactivex.Observable<BeforePosts> getBeforeStories(String date) {
        return service.getBeforeStories(date).subscribeOn(Schedulers.io());
    }

    @Nullable
    @Override
    public io.reactivex.Observable<Detail> getStoryDetail(@NonNull String storyId) {

        return service.getStoryDetail(storyId).subscribeOn(Schedulers.io());
    }

    @Override
    public void savePost(@NonNull StoriesBean storiesBean) {

    }

    @Override
    public void readPost(@NonNull StoriesBean storiesBean) {

    }

    @Override
    public void readPost(@NonNull int postId) {

    }


//    @Override
//    public void savePost(@NonNull BeforePosts.StoriesBean post) {
//        StoryRepository.getInstance().savePost(post);
//    }
//
//    @Override
//    public void readPost(@NonNull BeforePosts.StoriesBean post) {
//        StoryRepository.getInstance().readPost(post);
//    }
//
//    @Override
//    public void readPost(@NonNull int postId) {
//        StoryRepository.getInstance().readPost(postId);
//    }


    @Override
    public void refreshPosts() {

    }
}
