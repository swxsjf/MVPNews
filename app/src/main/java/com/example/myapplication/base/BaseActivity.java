package com.example.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/12
 * Time: 16:23
 * Describe: ${as}
 */
public class BaseActivity extends FragmentActivity {
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
    }
}
