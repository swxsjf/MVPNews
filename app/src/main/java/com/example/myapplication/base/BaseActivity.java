package com.example.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/10
 * Time: 15:50
 * Describe: ${as}
 */
public abstract class BaseActivity<P extends BasePresenter,V extends BaseView,D> extends FragmentActivity implements BaseView<D> {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = initPresenter();
        presenter.attachView(this);

    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
