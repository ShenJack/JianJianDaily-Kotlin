package com.example.shenjack.zhihudailyreader.stoylist

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.ButterKnife
import com.example.shenjack.zhihudailyreader.R
import com.example.shenjack.zhihudailyreader.Util.Util
import com.example.shenjack.zhihudailyreader.data.NightStories
import com.example.shenjack.zhihudailyreader.data.StoriesBean
import com.example.shenjack.zhihudailyreader.data.TodayStories
import com.example.shenjack.zhihudailyreader.data.source.remote.ZhihuDailyService
import com.example.shenjack.zhihudailyreader.nights.NightStoriesAdapter
import com.example.shenjack.zhihudailyreader.storydetail.DetailActivity
import com.example.shenjack.zhihudailyreader.topstories.TopFragmentPagerAdapter
import com.example.shenjack.zhihudailyreader.topstories.TopStoreisPresenter
import com.example.shenjack.zhihudailyreader.topstories.TopStoriesContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.Android
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.drawerListener
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toolbar
import java.util.ArrayList

/**
 * Created by sjjkk on 2017/10/15.
 */
class StoryListContainerFragment : Fragment(), StoryListAdapterOnClickListener , TopStoriesContract.View {
    override fun setPresenter(presenter: TopStoriesContract.Presenter) {
    }

    override fun showLoadingIndicator(visible: Boolean) {
    }

    override fun showStories(stories: List<TodayStories.TopStoriesBean>) {
        mTopStories = stories
        mTopFragmentPagerAdapter!!.setTopStories(stories)
        pagerIndicator.text = StringBuilder()
                .append(1)
                .append("/")
                .append(stories.size)
                .toString()
    }

    override fun showStoryDetail(story: StoriesBean) {
    }

    override fun setStories(stories: List<TodayStories.TopStoriesBean>) {
    }

    override fun onClick(storyId: Int, title: String) {
        DetailActivity.startActivity(activity,storyId,title)
    }

    lateinit private var mContext: AppCompatActivity

    private var mTopStories: List<TodayStories.TopStoriesBean>? = null
    private var mTopFragmentPagerAdapter: TopFragmentPagerAdapter? = null
    private var mTopStoriesPresenter: TopStoriesContract.Presenter? = null
    private var viewPager: ViewPager? = null
    lateinit var pagerIndicator: TextView
    var storyListFragments: ArrayList<StoryListFragment> = ArrayList()


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context as AppCompatActivity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_daily_container, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        mTopStoriesPresenter!!.loadTopStories()


    }

    private fun initViews() {
        val toolbar = find<Toolbar>(R.id.toolbar)
        mContext.setSupportActionBar(toolbar)

        mTopStoriesPresenter = TopStoreisPresenter(this)


        storyListFragments = ArrayList<StoryListFragment>()
        for (i in 0..6) {
            val date = Util.getBeforeDate(-i)
            val storyListFragment = StoryListFragment.newInstance(date)
            storyListFragments.add(storyListFragment)
        }
        val daysViewPager = find<ViewPager>(R.id.viewpager)
        val storyListViewPagerAdapter = StoryListViewPagerAdapter(mContext.supportFragmentManager, storyListFragments, mContext)

        daysViewPager.adapter = storyListViewPagerAdapter
        daysViewPager.offscreenPageLimit = 1

        val tabs = find<TabLayout>(R.id.tabs)

        tabs.setupWithViewPager(daysViewPager)

        pagerIndicator = find<TextView>(R.id.viewpager_indicator)
        mTopStories = ArrayList<TodayStories.TopStoriesBean>()
        viewPager = find<ViewPager>(R.id.topviewpager)
        mTopFragmentPagerAdapter = TopFragmentPagerAdapter(mContext.supportFragmentManager, mContext, mTopStories, this)
        viewPager!!.adapter = mTopFragmentPagerAdapter
        viewPager!!.offscreenPageLimit = 1
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                pagerIndicator.text = ((position + 1).toString() + "/" + mTopStories!!.size)
            }

        })



    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStart() {
        super.onStart()
    }

}