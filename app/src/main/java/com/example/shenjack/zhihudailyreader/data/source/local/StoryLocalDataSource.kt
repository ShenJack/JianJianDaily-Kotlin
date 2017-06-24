package com.example.shenjack.zhihudailyreader.data.source.local

import android.database.sqlite.SQLiteDatabase

import com.example.shenjack.zhihudailyreader.MyApplication
import com.example.shenjack.zhihudailyreader.data.BeforeStories
import com.example.shenjack.zhihudailyreader.data.Detail
import com.example.shenjack.zhihudailyreader.data.StoriesBean
import com.example.shenjack.zhihudailyreader.data.TodayStories
import com.example.shenjack.zhihudailyreader.data.source.StoryDataSource

import io.reactivex.Observable


/**
 * Created by ShenJack on 2017/6/3.
 */

class StoryLocalDataSource : StoryDataSource {

//    private val daoSession: DaoSession?
//
//    private val mDb: SQLiteDatabase? = null
//
//    private val mPostDao: PostDao
//
//    init {
//        val myApplication = MyApplication.instance
//        daoSession = myApplication!!.daoSession
//        mPostDao = daoSession!!.postDao
//    }


    //    @Nullable
    //    @Override
    //    public Observable<TodayStories> getTodayStories() {
    //        return makeObservableList(Util.getTodayDate());
    //    }
    //
    //    @Override
    //    public Observable<List<TodayStories.TopStoriesBean>> getTopStories() {
    //        return null;
    //    }
    //
    //
    //    @Override
    //    public Observable<BeforeStories> getBeforeStories(String  date) {
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

    override val todayStories: Observable<TodayStories>?
        get() = null

    override val topStories: Observable<List<TodayStories.TopStoriesBean>>?
        get() = null

    override fun getBeforeStories(date: String): Observable<BeforeStories>? {
        return null
    }

    override fun getStoryDetail(postId: String): Observable<Detail>? {
        return null
    }

    //    private Observable<BeforeStories> makeObservableList(final String date){
    //        return Observable.create(new OnSubscribe<BeforeStories>(){
    //
    //            @Override
    //            public void call(Subscriber<? super BeforeStories> subscriber) {
    //                TodayStories posts = mPostDao.queryBuilder()
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



    companion object {

         var instance: StoryLocalDataSource? = null
            get() {
                    if (field == null) instance = StoryLocalDataSource()
                    return field

            }
    }

}
