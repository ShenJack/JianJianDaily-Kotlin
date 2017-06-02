package com.example.shenjack.zhihudailyreader.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shenjack.zhihudailyreader.data.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ShenJack on 2017/6/1.
 */

public class PostRepository implements PostDataSource{

    private static PostRepository instance = null;

    private final PostDataSource mPostLocalDataSource;

    private final PostDataSource mPostRemoteDataSource;

    private List<PostRepositoryBObserver> mObservers = new ArrayList<PostRepositoryBObserver>();

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





    @Nullable
    @Override
    public List<Post> getPosts() {
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

    public interface PostRepositoryBObserver {

        void onPostChanged();

    }
}
