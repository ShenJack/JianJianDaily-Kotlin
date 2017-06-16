package com.example.shenjack.zhihudailyreader.data.source.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ShenJack on 2017/6/8.
 */

public class ZhihuDailyService {
    private static Retrofit retrofit;

    private static ZhihuDailyApi zhihuDailyService;

    public static Retrofit getRetrofitInstance() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyymmdd")
                .create();
        // TODO: 2017/6/8 Gson not yet
        if(retrofit !=null)return retrofit;
        else retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuDailyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static ZhihuDailyApi getZhihuDailyServiceInstance(){
        if(zhihuDailyService!=null)return zhihuDailyService;
        else zhihuDailyService = getRetrofitInstance().create(ZhihuDailyApi.class);
        return zhihuDailyService;
    }
}
