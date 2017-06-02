package com.example.shenjack.zhihudailyreader.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.data.Post;

import java.util.List;

/**
 * Created by ShenJack on 2017/6/1.
 */

public interface PostDataSource {
    interface GetPostCallBack{
        void onPostLoaded(Post post);

        void onDataNotAvailable();
    }

    @Nullable
    List<Post> getPosts();

    @Nullable
    Post getPost(@NonNull String postId);

    void savePost(@NonNull Post post);

    void readPost(@NonNull Post post);

    void readPost(@NonNull String postId);

    void refreshPosts();

}
