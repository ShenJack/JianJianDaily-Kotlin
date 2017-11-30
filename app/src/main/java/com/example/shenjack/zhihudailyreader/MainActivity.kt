package com.example.shenjack.zhihudailyreader

import android.os.Build
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu

import com.example.shenjack.zhihudailyreader.data.TodayStories
import com.example.shenjack.zhihudailyreader.topstories.TopStoriesContract

import java.util.ArrayList

import butterknife.ButterKnife

import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.MenuItem
import android.view.WindowManager
import com.example.shenjack.zhihudailyreader.Util.Util.getBeforeDate
import com.example.shenjack.zhihudailyreader.nights.NightStoriesFragment
import com.example.shenjack.zhihudailyreader.stoylist.StoryListContainerFragment
import com.example.shenjack.zhihudailyreader.stoylist.StoryListFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.drawerListener
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
//    var mainContainerFragment : StoryListContainerFragment = StoryListContainerFragment()
//    var nightFragment : NightStoriesFragment = NightStoriesFragment()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_daily -> switchToDaily()
            R.id.nav_shitang -> switchToNight()
            R.id.nav_before -> toast("switch to Before days")
        }
        find<DrawerLayout>(R.id.drawer_layout).closeDrawer(Gravity.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        instance = this

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun initView() {

//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
//            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//                    ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//        }

        val toolbar = ButterKnife.findById<Toolbar>(this, R.id.toolbar)
        setSupportActionBar(toolbar)

//        val storyListFragments = ArrayList<Fragment>()
//        for (i in 0..6) {
//            val date = getBeforeDate(-i)
//            val storyListFragment = StoryListFragment.newInstance(date)
//            storyListFragments.add(storyListFragment)
//        }

        val drawer = find<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.drawer_toggle_text,R.string.drawer_toggle_text)
        drawer.drawerListener { toggle }
        toggle.syncState()

        val navigationView = find<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        initFragments()
        switchToDaily()

    }

    private fun initFragments() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.add(R.id.content_layout,mainContainerFragment)
//        fragmentTransaction.add(R.id.content_layout,nightFragment)
//        hideFragment(fragmentTransaction)
        fragmentTransaction.replace(R.id.content_layout,StoryListContainerFragment()).commit()
    }

    override fun onBackPressed() {
        var drawer = find<DrawerLayout>(R.id.drawer_layout)
        if(drawer.isDrawerOpen(Gravity.START)){
            drawer.closeDrawer(Gravity.START);
        }else{
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun switchToBefore() {
        title = "附近茶馆"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //        hideFragment(fragmentTransaction);
        //        fragmentTransaction.show(nearbyFragment);
//        fragmentTransaction.replace(R.id.content_layout, nearbyFragment)

        fragmentTransaction.commit()
    }

    private fun switchToNight() {
        title = "深夜食堂"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //        hideFragment(fragmentTransaction);
        //        fragmentTransaction.show(recommendFragment);
//        hideFragment(fragmentTransaction)
//        fragmentTransaction.show(nightFragment)
        fragmentTransaction.replace(R.id.content_layout,NightStoriesFragment()).commit()
    }

    private fun switchToDaily() {
        title = "Daily"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        hideFragment(fragmentTransaction)
//        fragmentTransaction.show(mainContainerFragment)

        //        hideFragment(fragmentTransaction);
        //        fragmentTransaction.show(dailyFragment);


        fragmentTransaction.replace(R.id.content_layout,StoryListContainerFragment()).commit()
    }

//    private fun hideFragment(transaction: FragmentTransaction) {
//            transaction.hide(mainContainerFragment)
//            transaction.hide(nightFragment)
////            transaction.hide(recommendFragment)
//    }

    companion object {

        var instance: MainActivity? = null
            get() {
                if(field==null)field = MainActivity()
                return field
            }

    }



}
