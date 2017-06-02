package com.example.shenjack.zhihudailyreader.data.source.remote;

import com.example.shenjack.zhihudailyreader.data.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ShenJack on 2017/6/2.
 */

public interface ZhuhuDailyService {

    public final String BASE_URL = "http://news-at.zhihu.com/api/";

//


    @GET("4/news/{id}")
    Call<Post> getPost(@Path("id") int id);

//    prefetch launch image
//    @GET("7/prefetch-launch-images/1080*1920")
//    Call

//    get latest news
    @GET("4/news/latest")
    Call<List<Post>> getLatestPosts();

//    get past news
    @GET("4/news/before/{date}")
    Call<List<Post>> getBeforePosts();

//    get news' extra
//    @GET("4/story-extra/{id}")
//    Call<>

//    get themes
//    @GET

//    get themes' content

//    get hot posts
    @GET("3/news/hot")
    Call<List<Post>> getHotPosts();


}
