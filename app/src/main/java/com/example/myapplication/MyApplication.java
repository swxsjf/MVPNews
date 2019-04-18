package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.utils.ContextHelper;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/16
 * Time: 16:38
 * Describe: ${as}
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextHelper.setContext(this);
    }
}
