package com.example.shenjack.zhihudailyreader.storydetail

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout

import com.bumptech.glide.Glide
import com.example.shenjack.zhihudailyreader.R
import com.example.shenjack.zhihudailyreader.Util.Util
import com.example.shenjack.zhihudailyreader.data.Detail
import com.example.shenjack.zhihudailyreader.data.source.StoryRepository

import java.net.MalformedURLException
import java.net.URL

import butterknife.ButterKnife
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.alertDialogLayout
import org.jetbrains.anko.appcompat.v7.tintedImageButton

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private var storyId: Int = 0
    private var title: String? = null

    private var mDetailPresenter: DetailPresenter? = null

    private var mWebView: WebView? = null
    private var toolbar: Toolbar? = null
    private var headerImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activity)
        toolbar = ButterKnife.findById<Toolbar>(this, R.id.toolbar)
        setSupportActionBar(toolbar)

        initView()

        storyId = intent.getIntExtra(KEY_DATE, 9476259)
        title = intent.getStringExtra(KEY_TITLE)
        setTitle(title)

        mDetailPresenter = DetailPresenter(StoryRepository.instance!!, this)


        mDetailPresenter!!.loadStoryDetail(Integer.toString(storyId))

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


    }

    override fun onResume() {
        super.onResume()
        //        mDetailPresenter.loadStoryDetail(Integer.toString(storyId));
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun setPresenter(presenter: DetailContract.Presenter) {

    }

    override fun showLoadingIndicator(visible: Boolean) {

    }

    override fun showStoryDetail(detail: Detail) {
        var newHtml = detail.body
        val imageUrl: URL
        try {
            imageUrl = URL(detail.image)
            Glide.with(this).load(imageUrl).into(headerImage!!)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        newHtml = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />$newHtml"

        mWebView!!.loadDataWithBaseURL(CSS_LOCATION, newHtml, MINE_TYPE, ENCODING, null)

    }

    private fun initView() {
        mWebView = ButterKnife.findById<WebView>(this, R.id.webview_content)
        headerImage = ButterKnife.findById<ImageView>(this, R.id.header_img)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail_acticity, menu)
        return true
    }

    private val REQUEST_TO_SHARE_LINK: Int = 12421
    var alertDialog:AlertDialog? = null


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,Util.generateUrl(storyId))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivityForResult(Intent.createChooser(intent,"Send to"),REQUEST_TO_SHARE_LINK)
                alertDialog = alert{
                    customView {
                        include<RelativeLayout>(R.layout.recyclerview_loading_layout){
                            padding = dip(10)
                        }
                    }
                }.build() as AlertDialog
                alertDialog!!.show()
            }

            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    companion object {

        private val KEY_DATE = "DATE"
        private val KEY_TITLE = "TITLE"
        private val CSS_LOCATION = "file:///android_asset/"
        private val ENCODING = "utf-8"
        private val MINE_TYPE = "text/html"

        fun startActivity(context: Context, id: Int, title: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(KEY_DATE, id)
            intent.putExtra(KEY_TITLE, title)
            context.startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_TO_SHARE_LINK -> {
                alertDialog!!.hide()
            }
        }
    }


}