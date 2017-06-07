package com.example.shenjack.zhihudailyreader.data.source;

import android.database.Observable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.data.Post;

import java.util.Date;
import java.util.List;
import java.util.Observer;

/**
 * Created by ShenJack on 2017/6/1.
 */

public interface PostDataSource {
    interface GetPostCallBack{
        void onPostLoaded(Post post);

        void onDataNotAvailable();
    }

    void init();

    void getTodayPosts();

    void getBeforePosts(String  date);

    void getPost(@NonNull String postId);

    void savePost(@NonNull Post post);

    void readPost(@NonNull Post post);

    void readPost(@NonNull String postId);

    void refreshPosts();

}
