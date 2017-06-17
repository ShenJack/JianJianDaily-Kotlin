package com.example.shenjack.zhihudailyreader.storydetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;

import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.data.Detail;
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity
        implements DetailContract.View{

    private static final String KEY_DATE = "DATE";
    private static final String CSS_LOCATION="file:///android_asset/";
    private static final String ENCODING = "utf-8";
    private static final String MINE_TYPE= "text/html";

    private int  storyId;

    private DetailPresenter mDetailPresenter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private WebView mWebView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);
        toolbar = ButterKnife.findById(this,R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        storyId = getIntent().getIntExtra(KEY_DATE,9476259);

        mDetailPresenter = new DetailPresenter(StoryRepository.getInstance(),this);


        mDetailPresenter.loadStoryDetail(Integer.toString(storyId));

        Log.d("id",":"+storyId);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        mDetailPresenter.loadStoryDetail(Integer.toString(storyId));

        Log.d("onResume","PASS");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {

    }

    @Override
    public void showLoadingIndicator(boolean visible) {
        mSwipeRefreshLayout.setRefreshing(visible);
    }

    @Override
    public void showStoryDetail(Detail detail){
        String newHtml = detail.getBody();
        newHtml = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />" + newHtml;
        System.out.println(newHtml);

        mWebView.loadDataWithBaseURL(CSS_LOCATION, newHtml, MINE_TYPE, ENCODING, null);

    }

    private void initView(){
        mSwipeRefreshLayout = ButterKnife.findById(this,R.id.detail_swipe_refresh_layout);
        mWebView = ButterKnife.findById(this,R.id.webview_content);

    }

    public static void startActivity(Context context, int id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_DATE, id);
        context.startActivity(intent);
    }
}