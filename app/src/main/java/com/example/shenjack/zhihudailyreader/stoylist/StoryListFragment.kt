package com.example.shenjack.zhihudailyreader.stoylist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.shenjack.zhihudailyreader.R
import com.example.shenjack.zhihudailyreader.data.StoriesBean
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository


import butterknife.ButterKnife


class StoryListFragment : Fragment(), StoryListContract.View, SwipeRefreshLayout.OnRefreshListener, StoryListAdapterOnClickListener {

    private var mStories: List<StoriesBean> = ArrayList()

    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    override var date: String = ""

    internal var mContext: Context? = null

    private val mStoryListPresenter: StoryListPresenter


    private var recyclerView: RecyclerView? = null


    private var storyListRecyclerViewAdapter: StoryListRecyclerViewAdapter? = null

    init {
        mStoryListPresenter = StoryListPresenter(StoryRepository.instance, this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val bundle = arguments
            date = bundle.getString("date")

        }
        if (date == null) {
            Log.d("date", "= null")
        } else {
            Log.d("date", "passed")
        }
        Log.d("date", ":" + date!!)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_page, container, false)


        initView(view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mStoryListPresenter.loadStories(date)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initView(view: View) {
        recyclerView = ButterKnife.findById<RecyclerView>(view, R.id.list)
        val linearLayoutManager = LinearLayoutManager(mContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = linearLayoutManager

        storyListRecyclerViewAdapter = StoryListRecyclerViewAdapter(mContext, mStories, this)
        recyclerView!!.adapter = storyListRecyclerViewAdapter

        Log.d("Adapter", "pass")
        mSwipeRefreshLayout = ButterKnife.findById<SwipeRefreshLayout>(view, R.id.swipe_refresh_layout)
        mSwipeRefreshLayout!!.setOnRefreshListener(this)
        mSwipeRefreshLayout!!.setColorSchemeResources(R.color.colorPrimary)
    }

    override fun setPresenter(presenter: StoryListContract.Presenter) {

    }

    override fun showLoadingIndicator(visible: Boolean) {
        mSwipeRefreshLayout!!.isRefreshing = visible
    }

    override fun showStories(stories: List<StoriesBean>) {
        storyListRecyclerViewAdapter!!.setStoryList(stories)
    }

    override fun showStoryDetail(story: StoriesBean) {

    }

    override fun showLoadingError() {

    }

    override fun showDateSelector() {

    }

    override fun setStories(mStories: List<StoriesBean>) {
        storyListRecyclerViewAdapter!!.setStoryList(mStories)
    }

    override fun notifyLoadingComplete() {
        //        Snackbar.make(getView(),"Completed",Snackbar.LENGTH_SHORT).show();
    }

    override fun notifyLoadingError() {
        //        Snackbar.make(getView(),"Error",Snackbar.LENGTH_SHORT).show();
    }

    override fun onRefresh() {
        //        mStoryListPresenter.refreshStoryList();
        //
        //  mSwipeRefreshLayout.setRefreshing(false);
        mStoryListPresenter.loadStories(date)
    }

    override fun onClick(storyId: Int, title: String) {
        mStoryListPresenter.openStoryDetail(storyId, title)
    }

    internal fun makeSnack(text: String) {
        val view = view
    }

    companion object {

        fun newInstance(date: String): StoryListFragment {

            val args = Bundle()
            args.putString("date", date)
            Log.d("date", "newInstance: " + date)
            val fragment = StoryListFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
