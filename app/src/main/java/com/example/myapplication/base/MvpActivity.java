package com.example.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/10
 * Time: 15:50
 * Describe: ${as}
 */
public abstract class MvpActivity<P extends BasePresenter,V extends BaseView,D> extends BaseActivity implements BaseView<D> {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = createPresenter();
        presenter.attachView(this);

        initView();
        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
