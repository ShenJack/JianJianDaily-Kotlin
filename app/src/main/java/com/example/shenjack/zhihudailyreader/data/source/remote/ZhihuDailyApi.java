package com.example.shenjack.zhihudailyreader.data.source.remote;


import com.example.shenjack.zhihudailyreader.data.BeforePosts;
import com.example.shenjack.zhihudailyreader.data.Detail;
import com.example.shenjack.zhihudailyreader.data.Extra;
import com.example.shenjack.zhihudailyreader.data.Post;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ShenJack on 2017/6/2.
 */

public interface ZhihuDailyApi {

    public final String BASE_URL = "http://news-at.zhihu.com/api/";

//


    @GET("4/news/{id}")
    Observable<Detail> getPostDetail(@Path("id") String  id);

//    prefetch launch image
//    @GET("7/prefetch-launch-images/1080*1920")
//    Call

//    get latest news
    @GET("4/news/latest")
    Observable<TodayPosts> getTodayPosts();

    @GET("4/news/latest")
    Observable<List<TodayPosts.TopStoriesBean>> getTopPosts();


//    get past news
    @GET("4/news/before/{date}")
    Observable<BeforePosts> getBeforePosts(@Path("date") String date);


//    get news' extra
    @GET("4/story-extra/{id}")
    Observable<Extra> getPostExtra(@Path("id") int id);

//    get themes

//    get themes' content



}
