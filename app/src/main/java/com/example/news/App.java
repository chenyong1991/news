package com.example.news;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;

/**
 * Created by c8469 on 2016/12/13.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);

    }
}
