package com.example.shenjack.zhihudailyreader.storydetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.UriLoader;
import com.example.shenjack.zhihudailyreader.R;
import com.example.shenjack.zhihudailyreader.Util.Util;
import com.example.shenjack.zhihudailyreader.data.Detail;
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity
        implements DetailContract.View{

    private static final String KEY_DATE = "DATE";
    private static final String KEY_TITLE = "TITLE";
    private static final String CSS_LOCATION="file:///android_asset/";
    private static final String ENCODING = "utf-8";
    private static final String MINE_TYPE= "text/html";

    private int  storyId;
    private String title;

    private DetailPresenter mDetailPresenter;

    private WebView mWebView;
    private Toolbar toolbar;
    private ImageView headerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);
        toolbar = ButterKnife.findById(this,R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        storyId = getIntent().getIntExtra(KEY_DATE,9476259);
        title = getIntent().getStringExtra(KEY_TITLE);
        setTitle(title);

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

    }

    @Override
    public void showStoryDetail(Detail detail){
        String newHtml = detail.getBody();
        URL imageUrl;
        try {
            imageUrl = new URL(detail.getImage());
            Glide.with(this).load(imageUrl).into(headerImage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        newHtml = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />" + newHtml;
//        System.out.println(newHtml);
        mWebView.loadDataWithBaseURL(CSS_LOCATION, newHtml, MINE_TYPE, ENCODING, null);

    }

    private void initView(){
        mWebView = ButterKnife.findById(this,R.id.webview_content);
        headerImage = ButterKnife.findById(this,R.id.header_img);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public static void startActivity(Context context, int id, String title) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_DATE, id);
        intent.putExtra(KEY_TITLE,title);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_acticity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(Util.generateUrl(storyId)));
                startActivity(intent);
        }
        return true;
    }
}