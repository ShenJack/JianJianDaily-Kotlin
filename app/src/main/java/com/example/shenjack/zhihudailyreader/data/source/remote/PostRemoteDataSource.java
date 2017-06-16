package com.example.shenjack.zhihudailyreader.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.data.BeforePosts;
import com.example.shenjack.zhihudailyreader.data.Detail;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.data.source.PostDataSource;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * Created by ShenJack on 2017/6/5.
 */

public class PostRemoteDataSource implements PostDataSource {

    private ZhihuDailyApi service;
    private static PostDataSource instance;


    public PostRemoteDataSource() {
        Retrofit retrofit = ZhihuDailyService.getRetrofitInstance();
        service = ZhihuDailyService.getZhihuDailyServiceInstance();
    }

    public static PostDataSource getInstance() {
        if(instance==null)instance = new PostRemoteDataSource();
        return instance;
    }

    @Nullable
    @Override
    public io.reactivex.Observable<TodayPosts> getTodayPosts() {
        return service.getTodayPosts().subscribeOn(io.reactivex.schedulers.Schedulers.io());
    }

    @Override
    public io.reactivex.Observable<List<TodayPosts.TopStoriesBean>> getTopPosts() {
        return service.getTopPosts().subscribeOn(Schedulers.io());
    }

    @Override
    public io.reactivex.Observable<BeforePosts> getBeforePosts(String date) {
        return service.getBeforePosts(date).subscribeOn(Schedulers.io());
    }

    @Nullable
    @Override
    public io.reactivex.Observable<Detail> getPostDetail(@NonNull String postId) {

        return service.getPostDetail(postId).subscribeOn(Schedulers.io());
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
