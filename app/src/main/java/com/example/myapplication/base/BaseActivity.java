package com.example.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/12
 * Time: 16:23
 * Describe: ${as}
 */
public abstract class BaseActivity extends FragmentActivity {
    protected Context context;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        context = getApplicationContext();
    }

    protected abstract int getContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
