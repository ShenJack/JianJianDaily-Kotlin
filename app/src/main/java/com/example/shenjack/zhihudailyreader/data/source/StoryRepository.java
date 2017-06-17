package com.example.shenjack.zhihudailyreader.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.data.BeforePosts;
import com.example.shenjack.zhihudailyreader.data.Detail;
import com.example.shenjack.zhihudailyreader.data.Post;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.TodayPosts;
import com.example.shenjack.zhihudailyreader.data.source.local.StoryLocalDataSource;
import com.example.shenjack.zhihudailyreader.data.source.remote.StoryRemoteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.shenjack.zhihudailyreader.Util.Util.checkNotNull;


/**
 * Created by ShenJack on 2017/6/1.
 */

public class StoryRepository implements StoryDataSource {

    private static StoryRepository instance = null;

    private final StoryDataSource mStoryLocalDataSource;

    private final StoryDataSource mStoryRemoteDataSource;

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
                mStoryLocalDataSource.savePost(post);
            }
        }
    }

    private void processLoadedTasks(List<Post> posts){
        // TODO: 2017/6/2 not yet
    }


    public static StoryRepository getInstance(StoryLocalDataSource storyLocalDataSource,
                                              StoryRemoteDataSource postRemoteDataSource){
        if(instance == null){
            instance = new StoryRepository(storyLocalDataSource,postRemoteDataSource);
        }

        return instance;
    }

    public static StoryRepository getInstance(){
        if(instance == null){
            instance = new StoryRepository(StoryLocalDataSource.getInstance(), StoryRemoteDataSource.getInstance());
        }

        return instance;
    }

    public StoryRepository(StoryDataSource postLocalDataSource, StoryDataSource postRemoteDataSource) {
        mStoryLocalDataSource = checkNotNull(postLocalDataSource);
        mStoryRemoteDataSource = checkNotNull(postRemoteDataSource);
    }



    @Nullable
    @Override
    public io.reactivex.Observable<TodayPosts> getTodayStories() {
        return mStoryRemoteDataSource.getTodayStories();
    }

    @Override
    public io.reactivex.Observable<BeforePosts> getBeforeStories(String date) {
        return mStoryRemoteDataSource.getBeforeStories(date);
    }

    @Override
    public io.reactivex.Observable<List<TodayPosts.TopStoriesBean>> getTopStories() {
        return mStoryRemoteDataSource.getTopStories();
    }

    @Nullable
    @Override
    public io.reactivex.Observable<Detail> getStoryDetail(@NonNull String storyId) {
        return mStoryRemoteDataSource.getStoryDetail(storyId);
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
