package com.example.shenjack.zhihudailyreader.topstories

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.shenjack.zhihudailyreader.R
import com.example.shenjack.zhihudailyreader.data.TodayStories
import com.example.shenjack.zhihudailyreader.stoylist.StoryListAdapterOnClickListener

import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

import butterknife.ButterKnife


/**
 * Created by ShenJack on 2017/6/21.
 */

class TopFragmentPagerAdapter(fm: FragmentManager, internal var mContext: Context, private var topStories: List<TodayStories.TopStoriesBean>?, private val storyListAdapterOnClickListener: StoryListAdapterOnClickListener) : FragmentPagerAdapter(fm) {
    private val views: MutableList<View>

    init {
        views = ArrayList<View>()
        initPages()
    }

    override fun getItem(position: Int): Fragment {
        return TopFragment.newInstance(views[position])

    }

    override fun getCount(): Int {
        return topStories!!.size
    }

    fun setTopStories(topStories: List<TodayStories.TopStoriesBean>) {
        this.topStories = topStories
        initPages()
        notifyDataSetChanged()
    }

    private fun initPages() {
        for (topStoriesBean in topStories!!) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.top_thumbnail, null)
            val headerImage = ButterKnife.findById<ImageView>(view, R.id.thumbnail_image)
            val title = topStoriesBean.title
            val titleTextView = ButterKnife.findById<TextView>(view, R.id.title)
            val storyId = topStoriesBean.id
            var url: URL? = null
            try {
                url = URL(topStoriesBean.image)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }

            titleTextView.text = title

            Glide.with(mContext).load(url).into(headerImage)

            view.setOnClickListener { storyListAdapterOnClickListener.onClick(storyId, title!!) }


            views.add(view)
        }
    }
}
