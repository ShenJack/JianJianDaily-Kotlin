package com.example.shenjack.zhihudailyreader.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.data.BeforePosts;
import com.example.shenjack.zhihudailyreader.data.Detail;
import com.example.shenjack.zhihudailyreader.data.Post;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.data.source.local.PostLocalDataSource;
import com.example.shenjack.zhihudailyreader.data.source.remote.PostRemoteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.shenjack.zhihudailyreader.Util.Util.checkNotNull;


/**
 * Created by ShenJack on 2017/6/1.
 */

public class StoryRepository implements PostDataSource{

    private static StoryRepository instance = null;

    private final PostDataSource mPostLocalDataSource;

    private final PostDataSource mPostRemoteDataSource;

    private List<PostRepositoryObserver> mObservers = new ArrayList<PostRepositoryObserver>();

    Map<String, Post> mCachedPosts;

    public List<Post> getCachedPosts(){
        return mCachedPosts == null? null: new ArrayList<>(mCachedPosts.values());
    }

    public Post getCachedPost(String postId){
        return mCachedPosts.get(postId);
    }

    private void saveTaskInLocalDataSource(List<StoriesBean> posts){
        if(posts !=null){
            for (StoriesBean post:posts
                    ){
                mPostLocalDataSource.savePost(post);
            }
        }
    }

    private void processLoadedTasks(List<Post> posts){
        // TODO: 2017/6/2 not yet
    }


    public static StoryRepository getInstance(PostLocalDataSource postLocalDataSource,
                                              PostRemoteDataSource postRemoteDataSource){
        if(instance == null){
            instance = new StoryRepository(postLocalDataSource,postRemoteDataSource);
        }

        return instance;
    }

    public static StoryRepository getInstance(){
        if(instance == null){
            instance = new StoryRepository(PostLocalDataSource.getInstance(),PostRemoteDataSource.getInstance());
        }

        return instance;
    }

    public StoryRepository(PostDataSource postLocalDataSource, PostDataSource postRemoteDataSource) {
        mPostLocalDataSource = checkNotNull(postLocalDataSource);
        mPostRemoteDataSource = checkNotNull(postRemoteDataSource);
    }



    @Nullable
    @Override
    public io.reactivex.Observable<TodayPosts> getTodayPosts() {
        return mPostRemoteDataSource.getTodayPosts();
    }

    @Override
    public io.reactivex.Observable<BeforePosts> getBeforePosts(String date) {
        return mPostRemoteDataSource.getBeforePosts(date);
    }

    @Override
    public io.reactivex.Observable<List<TodayPosts.TopStoriesBean>> getTopPosts() {
        return null;
    }

    @Nullable
    @Override
    public io.reactivex.Observable<Detail> getPostDetail(@NonNull String postId) {
        return null;
    }

    @Override
    public void savePost(@NonNull StoriesBean storiesBean) {

    }

    @Override
    public void readPost(@NonNull StoriesBean storiesBean) {

    }

    @Override
    public void readPost(@NonNull int postId) {

    }


    @Override
    public void refreshPosts() {

    }

    public interface PostRepositoryObserver {

        void onPostChanged();

    }
}
