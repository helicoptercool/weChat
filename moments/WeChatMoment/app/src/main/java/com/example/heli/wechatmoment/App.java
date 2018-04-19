package com.example.heli.wechatmoment;

import android.app.Application;

/**
 * Created by heli on 2018/4/19.
 */

public class App extends Application {
    static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp=this;
    }

    public static App getInstance() {
        return mApp;
    }
}
