package com.example.shenjack.zhihudailyreader.postlist;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shenjack.zhihudailyreader.R;

import butterknife.ButterKnife;

/**
 * Created by ShenJack on 2017/5/30.
 */

public class PostListRecyclerViewAdapter extends RecyclerView.Adapter<PostListRecyclerViewAdapter.PostViewHolder> {

    Context mContext;

    public PostListRecyclerViewAdapter() {

    }

    public PostListRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null) Log.e("error","error");
        Log.d("pass","pass");
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_post_thumbnail,null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.intro.setText(Integer.toString(position));
    }


    @Override
    public int getItemCount() {
        return 100;
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView intro;
        public PostViewHolder(View itemView) {
            super(itemView);
            intro = ButterKnife.findById(itemView, R.id.intro);
        }
    }
}
