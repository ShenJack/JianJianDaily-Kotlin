package com.example.shenjack.zhihudailyreader.data.source.local;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.shenjack.zhihudailyreader.MyApplication;
import com.example.shenjack.zhihudailyreader.data.BeforePosts;
import com.example.shenjack.zhihudailyreader.data.DaoSession;
import com.example.shenjack.zhihudailyreader.data.Detail;
import com.example.shenjack.zhihudailyreader.data.Post;
import com.example.shenjack.zhihudailyreader.data.PostDao;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.data.source.StoryDataSource;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by ShenJack on 2017/6/3.
 */

public class StoryLocalDataSource implements StoryDataSource {

    private static StoryLocalDataSource instance;

    private DaoSession daoSession;

    private SQLiteDatabase mDb;

    private PostDao mPostDao;


    public StoryLocalDataSource() {
        MyApplication myApplication  = MyApplication.getInstance();
        daoSession = myApplication.getDaoSession();
        mPostDao = daoSession.getPostDao();
    }




//    @Nullable
//    @Override
//    public Observable<TodayPosts> getTodayStories() {
//        return makeObservableList(Util.getTodayDate());
//    }
//
//    @Override
//    public Observable<List<TodayPosts.TopStoriesBean>> getTopStories() {
//        return null;
//    }
//
//
//    @Override
//    public Observable<BeforePosts> getBeforeStories(String  date) {
//        return makeObservableList(date);
//    }
//
//    @Nullable
//    @Override
//    public Observable<Detail> loadStoryDetail(@NonNull String postId) {
//        return makeObservable(postId);
//    }

//    @Override
//    public void savePost(@NonNull Post post) {
//        mPostDao.insert(post);
//    }

//    @Override
//    public void readPost(@NonNull Post post) {
//        post.setHasBeenReaden(true);
//        mPostDao.update(post);
//    }
//
//    @Override
//    public void readPost(@NonNull String postId) {
//        Post post = getPostbyId(postId);
//        post.setHasBeenReaden(true);
//        mPostDao.update(post);
//}

    @Override
    public Observable<TodayPosts> getTodayStories() {
        return null;
    }

    @Override
    public Observable<List<TodayPosts.TopStoriesBean>> getTopStories() {
        return null;
    }

    @Override
    public Observable<BeforePosts> getBeforeStories(String date) {
        return null;
    }

    @Override
    public Observable<Detail> getStoryDetail(@NonNull String postId) {
        return null;
    }

    @Override
    public void savePost(@NonNull StoriesBean post) {

    }

    @Override
    public void readPost(@NonNull StoriesBean post) {

    }

    @Override
    public void readPost(@NonNull int postId) {

    }

    @Override
    public void refreshPosts() {

    }

    public static StoryLocalDataSource getInstance(){
        if(instance == null){
            instance = new StoryLocalDataSource();
        }
        return instance;
    }

//    private Observable<BeforePosts> makeObservableList(final String date){
//        return Observable.create(new OnSubscribe<BeforePosts>(){
//
//            @Override
//            public void call(Subscriber<? super BeforePosts> subscriber) {
//                TodayPosts posts = mPostDao.queryBuilder()
//                        .where(PostDao.Properties.Date.eq(date))
//                        .orderAsc(PostDao.Properties.Id)
//                        .list();
//
//                subscriber.onNext(posts);
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io());
//    }


//    private Observable<Post> makeObservable(final String id){
//        return Observable.create(new OnSubscribe<Post>(){
//
//            @Override
//            public void call(Subscriber<? super Post> subscriber) {
//                Post post= mPostDao.loadByRowId(Integer.parseInt(id));
//                subscriber.onNext(post);
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io());
//    }

    private Post getPostbyId(final String id){
        return mPostDao.loadByRowId(Integer.parseInt(id));
    }

}
