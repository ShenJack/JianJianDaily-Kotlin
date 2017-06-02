package com.example.shenjack.zhihudailyreader;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.shenjack.zhihudailyreader.data.DaoMaster;
import com.example.shenjack.zhihudailyreader.data.DaoSession;

/**
 * Created by ShenJack on 2017/6/1.
 */

public class MyApplication extends Application {
    private DaoMaster.DevOpenHelper mDaoHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setDatabase();
    }

    public static MyApplication getInstance(){
        return instance;
    }

    private void setDatabase() {
        mDaoHelper = new DaoMaster.DevOpenHelper(this,"notes-db", null);
        db = mDaoHelper.getWritableDatabase();

        mDaoMaster = new DaoMaster(db);

        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
