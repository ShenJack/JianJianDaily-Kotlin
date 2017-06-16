package com.example.shenjack.zhihudailyreader.stoylist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.data.StoriesBean;
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class StoryListFragment extends Fragment implements StoryListContract.View
        , SwipeRefreshLayout.OnRefreshListener
        , StoryListAdapterOnClickListener{

    private List<StoriesBean> mStories;

    private SwipeRefreshLayout  mSwipeRefreshLayout;

    private String date;

    Context mContext;

    private StoryListPresenter mStoryListPresenter;


    private RecyclerView recyclerView;


    private StoryListRecyclerViewAdapter storyListRecyclerViewAdapter;

    public StoryListFragment() {
        mStoryListPresenter = new StoryListPresenter(StoryRepository.getInstance(),this);
    }

    public static StoryListFragment newInstance(String date) {

        Bundle args = new Bundle();
        args.putString("date",date);
        Log.d("date","newInstance: "+date);
        StoryListFragment fragment = new StoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            Bundle bundle = getArguments();
            date = bundle.getString("date");

        }
        if(date == null){
            Log.d("date","= null");
        }
        else {
            Log.d("date","passed");
        }
        Log.d("date",":"+date);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);


        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mStoryListPresenter.loadStories(date);
    }

    private void initView(View view){
        recyclerView = ButterKnife.findById(view, R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mStories = new ArrayList<>();
        storyListRecyclerViewAdapter = new StoryListRecyclerViewAdapter(mContext, mStories, this);
        recyclerView.setAdapter(storyListRecyclerViewAdapter);

        Log.d("Adapter","pass");
        mSwipeRefreshLayout = ButterKnife.findById(view,R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    public void setPresenter(StoryListContract.Presenter presenter) {

    }

    @Override
    public void showLoadingIndicator(boolean visible) {
        mSwipeRefreshLayout.setRefreshing(visible);
    }

    @Override
    public void showStories(List<StoriesBean> stories) {
        storyListRecyclerViewAdapter.setStoryList(stories);
    }

    @Override
    public void showStoryDetail(StoriesBean story) {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void showDateSelector() {

    }

    @Override
    public void setStories(List<StoriesBean> mStories) {
        storyListRecyclerViewAdapter.setStoryList(mStories);
    }


    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void notifyLoadingComplete() {
//        Snackbar.make(getView(),"Completed",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void notifyLoadingError() {
//        Snackbar.make(getView(),"Error",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
//        mStoryListPresenter.refreshStoryList();
//
//  mSwipeRefreshLayout.setRefreshing(false);
        mStoryListPresenter.loadStories(date);
    }

    @Override
    public void onClick(int storyId) {
        mStoryListPresenter.openStoryDetail(storyId);
    }

    void makeSnack(String text){
        View view = getView();
    }



}
