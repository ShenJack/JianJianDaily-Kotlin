package com.example.shenjack.zhihudailyreader.nights

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.shenjack.zhihudailyreader.R
import com.example.shenjack.zhihudailyreader.data.NightStories
import com.example.shenjack.zhihudailyreader.data.StoriesBean
import com.example.shenjack.zhihudailyreader.storydetail.DetailActivity
import com.example.shenjack.zhihudailyreader.stoylist.StoryListAdapterOnClickListener
import com.example.shenjack.zhihudailyreader.viewholder.StoryViewHolder
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by ShenJack on 2017/5/30.
 */

class NightStoriesAdapter : RecyclerView.Adapter<StoryViewHolder> {

    internal var mContext: Context?
    internal var mStoryList: List<NightStories.StoriesBean>
    private val storyListAdapterOnClickListener: StoryListAdapterOnClickListener

    constructor(mContext: Context?, mStoryList: List<NightStories.StoriesBean>, storyListAdapterOnClickListener: StoryListAdapterOnClickListener) : super() {
        this.mContext = mContext
        this.mStoryList = mStoryList
        this.storyListAdapterOnClickListener = storyListAdapterOnClickListener
    }

    fun setStoryList(nightStores: List<NightStories.StoriesBean>) {
        mStoryList = nightStores
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
        }catch (e : Exception){
            e.printStackTrace()
        }

        Glide.with(mContext).load(url).into(holder.imageView)

        holder.storyId = storiesBean.id
        holder.itemView.onClick {
            DetailActivity.startActivity(mContext!!,storiesBean.id,storiesBean.title);
        }
    }


    override fun getItemCount(): Int {
        return mStoryList.size
    }


}