package com.xtc.multimap;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * 应用application
 * <p/>
 * Created by hzj on 2016/5/10.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
    }

}
