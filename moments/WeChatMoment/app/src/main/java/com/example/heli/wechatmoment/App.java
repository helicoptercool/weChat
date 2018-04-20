package com.example.heli.wechatmoment;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by heli on 2018/4/19.
 */

public class App extends Application {
    static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp=this;
        initImageLoader();
    }

    public static App getInstance() {
        return mApp;
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

    }
}
