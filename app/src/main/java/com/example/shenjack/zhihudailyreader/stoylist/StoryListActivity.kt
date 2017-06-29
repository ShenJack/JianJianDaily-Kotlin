package com.example.shenjack.zhihudailyreader.stoylist

import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu

import com.example.shenjack.zhihudailyreader.R
import com.example.shenjack.zhihudailyreader.data.StoriesBean
import com.example.shenjack.zhihudailyreader.data.TodayStories
import com.example.shenjack.zhihudailyreader.topstories.TopFragmentPagerAdapter
import com.example.shenjack.zhihudailyreader.topstories.TopStoreisPresenter
import com.example.shenjack.zhihudailyreader.topstories.TopStoriesContract

import java.util.ArrayList

import butterknife.ButterKnife

import android.content.ContentValues.TAG
import com.example.shenjack.zhihudailyreader.Util.Util.getBeforeDate

class StoryListActivity : AppCompatActivity(), TopStoriesContract.View, StoryListAdapterOnClickListener {
    private var appBarLayout: AppBarLayout? = null
    private var mTopStories: List<TodayStories.TopStoriesBean>? = null
    private var mTopFragmentPagerAdapter: TopFragmentPagerAdapter? = null
    private var mTopStoriesPresenter: TopStoriesContract.Presenter? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        instance = this

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_post_list)

        val toolbar = ButterKnife.findById<Toolbar>(this, R.id.toolbar)
        setSupportActionBar(toolbar)

//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)

//        supportActionBar!!.setHomeButtonEnabled(true)




        initView()


        mTopStoriesPresenter = TopStoreisPresenter(this)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun initView() {


        val storyListFragments = ArrayList<Fragment>()
        for (i in 0..6) {

            val date = getBeforeDate(-i)
            val storyListFragment = StoryListFragment.newInstance(date)
            storyListFragments.add(storyListFragment)
        }


        val fragmentManager = supportFragmentManager
        val daysViewPager = ButterKnife.findById<ViewPager>(this, R.id.viewpager)

        val storyListViewPagerAdapter = StoryListViewPagerAdapter(fragmentManager, storyListFragments, this)

        daysViewPager.adapter = storyListViewPagerAdapter
        daysViewPager.offscreenPageLimit = 1

        val tabs = ButterKnife.findById<TabLayout>(this, R.id.tabs)

        tabs.setupWithViewPager(daysViewPager)




        appBarLayout = ButterKnife.findById<AppBarLayout>(this, R.id.list_app_bar)


        mTopStories = ArrayList<TodayStories.TopStoriesBean>()


        viewPager = ButterKnife.findById<ViewPager>(this, R.id.topviewpager)
        mTopFragmentPagerAdapter = TopFragmentPagerAdapter(supportFragmentManager, this, mTopStories, this)
        viewPager!!.adapter = mTopFragmentPagerAdapter
        viewPager!!.offscreenPageLimit = 1

    }

    override fun setPresenter(presenter: TopStoriesContract.Presenter) {

    }

    override fun showLoadingIndicator(visible: Boolean) {

    }

    override fun showStories(stories: List<TodayStories.TopStoriesBean>) {
        mTopStories = stories
        setStories(stories)
    }

    override fun showStoryDetail(story: StoriesBean) {

    }

    override fun setStories(stories: List<TodayStories.TopStoriesBean>) {
        Log.d(TAG, "setStories: " + stories.toString())
        mTopFragmentPagerAdapter!!.setTopStories(stories)
    }

    override fun onClick(storyId: Int, title: String) {
        mTopStoriesPresenter!!.openStoryDetail(storyId, title)
    }

    override fun onResume() {
        mTopStoriesPresenter!!.loadTopStories()
        super.onResume()
    }

    companion object {

        var instance:StoryListActivity? = null
            get() {
                if(field==null)field = StoryListActivity()
                return field
            }

    }



}
