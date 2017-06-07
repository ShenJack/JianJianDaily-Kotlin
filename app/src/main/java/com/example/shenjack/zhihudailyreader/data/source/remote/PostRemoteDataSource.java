package com.example.shenjack.zhihudailyreader.data.source.remote;

import android.content.Context;
import android.database.Observable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.data.Post;
import com.example.shenjack.zhihudailyreader.data.source.PostDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ShenJack on 2017/6/5.
 */

public class PostRemoteDataSource implements PostDataSource {

    private Retrofit retrofit;

    ZhihuDailyService service;


    Context mContext;

    @Override
    public void init() {

        retrofit =  new Retrofit
                .Builder()
                .baseUrl(ZhihuDailyService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service =  retrofit
                .create(ZhihuDailyService.class);
    }

    @Nullable
    @Override
    public Observable<List<Post>> getTodayPosts() {

    }

    @Nullable
    @Override
    public Observable<List<Post>> getTopPosts() {
        Call<List<Post>> call = service.getTodayPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                return response.body();
                // TODO: 2017/6/5 Handler on mainThread ????
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
        return null;
    }

    @Override
    public Observable<List<Post>> getBeforePosts(String date) {
        return null;
    }

    @Nullable
    @Override
    public Observable<Post> getPost(@NonNull String postId) {
        return null;
    }

    @Override
    public void savePost(@NonNull Post post) {

    }

    @Override
    public void readPost(@NonNull Post post) {

    }

    @Override
    public void readPost(@NonNull String postId) {

    }

    @Override
    public void refreshPosts() {

    }
}
