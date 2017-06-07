package com.example.shenjack.zhihudailyreader.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.MyApplication;
import com.example.shenjack.zhihudailyreader.data.DaoSession;
import com.example.shenjack.zhihudailyreader.data.Post;
import com.example.shenjack.zhihudailyreader.data.PostDao;
import com.example.shenjack.zhihudailyreader.data.source.PostDataSource;

import java.util.List;

/**
 * Created by ShenJack on 2017/6/3.
 */

public class PostLocalDataSource implements PostDataSource {

    private static PostLocalDataSource instance;

    private DaoSession daoSession;

    private SQLiteDatabase mDb;

    private PostDao mPostDao;


    public PostLocalDataSource(@NonNull Context context) {
        MyApplication myApplication  = MyApplication.getInstance();
        myApplication.getDaoSession();
        mPostDao = daoSession.getPostDao();
    }


    @Override
    public void init() {

    }

    @Nullable
    @Override
    public void getTodayPosts() {
        return null;
    }


    @Override
    public List<Post> getBeforePosts(String  date) {
        List<Post> posts = mPostDao.queryBuilder()
                .where(PostDao.Properties.Date.eq(date))
                .orderAsc(PostDao.Properties.Id)
                .list();
        return posts;
    }

    @Nullable
    @Override
    public void getPost(@NonNull String postId) {
        return null;
    }

    @Override
    public void savePost(@NonNull Post post) {
        mPostDao.insert(post);
    }

    @Override
    public void readPost(@NonNull Post post) {
        post.setHasBeenReaden(true);
        mPostDao.update(post);
    }

    @Override
    public void readPost(@NonNull String postId) {

    }

    @Override
    public void refreshPosts() {

    }

    public static PostLocalDataSource getInstance(@NonNull Context context){
        if(instance != null){
            instance = new PostLocalDataSource(context);
        }
        return instance;
    }


}
