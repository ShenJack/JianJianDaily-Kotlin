package com.example.shenjack.zhihudailyreader.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.data.Post;
import com.example.shenjack.zhihudailyreader.data.source.local.PostLocalDataSource;
import com.example.shenjack.zhihudailyreader.data.source.remote.PostRemoteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.shenjack.zhihudailyreader.Util.Util.checkNotNull;


/**
 * Created by ShenJack on 2017/6/1.
 */

public class PostRepository implements PostDataSource{

    private static PostRepository instance = null;

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

    private void saveTaskInLocalDataSource(List<Post> posts){
        if(posts !=null){
            for (Post post:posts
                    ){
                mPostLocalDataSource.savePost(post);
            }
        }
    }

    private void processLoadedTasks(List<Post> posts){
        // TODO: 2017/6/2 not yet
    }


    public static PostRepository getInstance(PostLocalDataSource postLocalDataSource,
                                             PostRemoteDataSource postRemoteDataSource){
        if(instance == null){
            instance = new PostRepository(postLocalDataSource,postRemoteDataSource);
        }

        return instance;
    }

    public PostRepository(PostDataSource postLocalDataSource, PostDataSource postRemoteDataSource) {
        mPostLocalDataSource = checkNotNull(postLocalDataSource);
        mPostRemoteDataSource = checkNotNull(postRemoteDataSource);
    }


    @Override
    public void init() {

    }

    @Nullable
    @Override
    public List<Post> getTodayPosts() {
        return null;
    }

    @Override
    public List<Post> getTopPosts() {
        return null;
    }

    @Override
    public List<Post> getBeforePosts(String date) {
        return null;
    }

    @Nullable
    @Override
    public Post getPost(@NonNull String postId) {
        return null;
    }

    @Override
    public void savePost(@NonNull Post post) {

    }

    @Override
    public void readPost(@NonNull Post post) {

    }

    @Override
    public void readPost(@NonNull String postId) {

    }

    @Override
    public void refreshPosts() {

    }

    public interface PostRepositoryObserver {

        void onPostChanged();

    }
}
