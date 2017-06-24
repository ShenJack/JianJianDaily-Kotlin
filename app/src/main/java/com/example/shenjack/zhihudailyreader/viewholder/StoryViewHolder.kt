package com.example.shenjack.zhihudailyreader.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.example.shenjack.zhihudailyreader.R
import com.example.shenjack.zhihudailyreader.stoylist.StoryListAdapterOnClickListener

import butterknife.ButterKnife

/**
 * Created by ShenJack on 2017/6/18.
 */

class StoryViewHolder(itemView: View, internal var mStoryListAdapterOnClickListener: StoryListAdapterOnClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var storyId: Int = 0
    var title: TextView
    var imageView: ImageView

    init {
        title = ButterKnife.findById<TextView>(itemView, R.id.title)
        imageView = ButterKnife.findById<ImageView>(itemView, R.id.thumbnail_image)
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        mStoryListAdapterOnClickListener.onClick(storyId, title.text as String)
    }

}
