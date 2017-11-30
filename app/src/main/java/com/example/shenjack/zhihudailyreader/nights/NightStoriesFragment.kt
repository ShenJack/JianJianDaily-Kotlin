package com.example.shenjack.zhihudailyreader.nights

import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.example.shenjack.zhihudailyreader.BaseFragment
import com.example.shenjack.zhihudailyreader.BasePresenter
import com.example.shenjack.zhihudailyreader.R
import com.example.shenjack.zhihudailyreader.data.NightStories
import com.example.shenjack.zhihudailyreader.data.source.remote.ZhihuDailyService
import com.example.shenjack.zhihudailyreader.extention.HeaderAndFooterWrapper
import com.example.shenjack.zhihudailyreader.stoylist.StoryListAdapterOnClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onScrollChange
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import java.util.*
import java.util.logging.Handler

/**
 * sjjkk on 2017/10/13 in Beijing.
 */

class NightStoriesFragment : BaseFragment() {
    override fun setPresenter(presenter: BasePresenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var nightStoriesAdapter: NightStoriesAdapter? = null
    var toolBar : Toolbar ? =null
//    lateinit var bigImage: ImageView
//    lateinit var bigTitle: TextView
    var nextNightNumber: Int = 0
    lateinit var storiesRecyclerview: RecyclerView
    var nightStoriesList: MutableList<NightStories.StoriesBean> = ArrayList<NightStories.StoriesBean>()
    var wrapperAdapter: HeaderAndFooterWrapper? = null

    override fun getLayoutId(): Int = R.layout.fragment_night_stories

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun initViews(rootView: View) {
//        bigImage = rootView.find(R.id.thumbnail_image)
//        bigTitle = rootView.find(R.id.title)
        storiesRecyclerview = rootView.find(R.id.list)
        toolBar = find(R.id.toolbar)
        (mContext as AppCompatActivity).setSupportActionBar(toolBar)

        swipeRefreshLayout = rootView.find<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                makeRequest()
            }
        })
    }

    override fun initData() {
        var storyListAdapterOnClickListener: StoryListAdapterOnClickListener = object : StoryListAdapterOnClickListener {
            override fun onClick(storyId: Int, title: String) {

            }
        }
        nightStoriesAdapter = NightStoriesAdapter(
                mContext,
                nightStoriesList,
                storyListAdapterOnClickListener
        )
        wrapperAdapter = HeaderAndFooterWrapper(
                context,
                nightStoriesAdapter!! as RecyclerView.Adapter<in RecyclerView.ViewHolder>
        )

        storiesRecyclerview.adapter = wrapperAdapter
        storiesRecyclerview.layoutManager = LinearLayoutManager(context)

        storiesRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var previousTotal = 0
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                var lastVisibleItem = (recyclerView!!.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (lastVisibleItem == wrapperAdapter?.itemCount!! - 1) {
                    makeAdditionalRequest()
                    swipeRefreshLayout.isRefreshing = true
                }
            }
        })

        makeRequest()
    }

    private fun makeAdditionalRequest() {
        var snackBar = Snackbar.make(view!!,"Loading",Snackbar.LENGTH_INDEFINITE)
        snackBar.show()
        ZhihuDailyService.zhihuDailyServiceInstance!!
                .getNightStories(nextNightNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeBy(onNext = { nightStories ->
                    nightStoriesList.addAll(nightStories.stories)
                    wrapperAdapter!!.notifyDataSetChanged()
//                        nightStoriesAdapter!!.notifyDataSetChanged()
                    nextNightNumber = nightStories.timestamp
                },onComplete = {
                    android.os.Handler().postDelayed({snackBar.dismiss()},500)
                    swipeRefreshLayout.isRefreshing = false
                    fillBigPic(nightStoriesList[Random().nextInt(nightStoriesList.size)])

                })
    }

    private fun makeRequest() {
            ZhihuDailyService.zhihuDailyServiceInstance!!
                    .getFirstNightStories()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribeBy(onNext = {

                        nightStories ->
                        nightStoriesList = nightStories.stories
                        nextNightNumber = nightStories.timestamp
                        nightStoriesAdapter!!.setStoryList(nightStoriesList)
//                        nightStoriesAdapter!!.notifyDataSetChanged()
                        wrapperAdapter!!.notifyDataSetChanged()
//                        nightStoriesAdapter!!.notifyItemRangeInserted(nightStoriesAdapter!!.itemCount, nightStories.stories.size)
                        fillBigPic(nightStoriesList[Random().nextInt(nightStoriesList.size)])
                    },
                            onComplete = {
                                swipeRefreshLayout.isRefreshing = false
                            }
                    )
    }

    fun fillBigPic(story: NightStories.StoriesBean) {
//        Glide.with(this).load(story.images[0]).into(bigImage)
//        bigTitle.text = story.title
    }
}