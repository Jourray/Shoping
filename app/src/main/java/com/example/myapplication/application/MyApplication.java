package com.example.myapplication.application;

import android.app.Application;

import client.example.text.dao.DaoMaster;
import client.example.text.dao.DaoSession;

public class MyApplication extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = new DaoMaster(new DaoMaster.DevOpenHelper(this, "user.db").getWritableDatabase()).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
