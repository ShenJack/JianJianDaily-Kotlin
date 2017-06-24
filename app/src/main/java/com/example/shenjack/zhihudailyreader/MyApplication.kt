package com.example.shenjack.zhihudailyreader

import android.app.Application

/**
 * Created by ShenJack on 2017/6/1.
 */

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        setDatabase()
    }

    private fun setDatabase() {

    }

    companion object {

    }
}
