package com.example.shenjack.zhihudailyreader.data.source.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ShenJack on 2017/6/8.
 */

object ZhihuDailyService {
    private var retrofit: Retrofit? = null

    private var okHttpClient : OkHttpClient? = null

    private var zhihuDailyService: ZhihuDailyApi? = null

    // TODO: 2017/6/8 Gson not yet
    val retrofitInstance: Retrofit?
        get() {

            val gson = GsonBuilder()
                    .setDateFormat("yyyymmdd")
                    .create()
            if (retrofit != null)
                return retrofit
            else
                okHttpClient = OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(ZhihuDailyApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient!!)
                        .build()
            return retrofit
        }

    val zhihuDailyServiceInstance: ZhihuDailyApi?
        get() {
            if (zhihuDailyService != null)
                return zhihuDailyService
            else
                zhihuDailyService = retrofitInstance!!.create(ZhihuDailyApi::class.java)
            return zhihuDailyService
        }
}
