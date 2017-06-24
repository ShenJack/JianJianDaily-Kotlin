package com.example.shenjack.zhihudailyreader.stoylist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.example.shenjack.zhihudailyreader.viewholder.StoryViewHolder
import com.example.shenjack.zhihudailyreader.R
import com.example.shenjack.zhihudailyreader.data.StoriesBean

import java.net.MalformedURLException
import java.net.URL

import android.content.ContentValues.TAG

/**
 * Created by ShenJack on 2017/5/30.
 */

class StoryListRecyclerViewAdapter(internal var mContext: Context?, internal var mStoryList: List<StoriesBean>, private val storyListAdapterOnClickListener: StoryListAdapterOnClickListener) : RecyclerView.Adapter<StoryViewHolder>() {

    fun setStoryList(newStoryList: List<StoriesBean>) {
        mStoryList = newStoryList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.single_post_thumbnail, null)
        return StoryViewHolder(view, storyListAdapterOnClickListener)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val storiesBean = mStoryList[position]
        holder.title.text = storiesBean.title
        var url: URL? = null
        try {
            url = URL(storiesBean.images!![0])
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        Glide.with(mContext).load(url).into(holder.imageView)


        holder.storyId = storiesBean.id
    }


    override fun getItemCount(): Int {
        return mStoryList.size
    }


}
