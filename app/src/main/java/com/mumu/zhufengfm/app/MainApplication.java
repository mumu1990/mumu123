package com.mumu.zhufengfm.app;

import android.app.Application;
import com.mumu.zhufengfm.app.cache.FileCache;

/**
 * Created by Administrator on 2015/7/31.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FileCache.createInstance(getApplicationContext());
    }
}
