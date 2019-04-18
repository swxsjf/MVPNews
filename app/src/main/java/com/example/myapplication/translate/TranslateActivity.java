package com.example.myapplication.translate;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.MvpActivity;

public class TranslateActivity extends MvpActivity<TranslatePresenter, TranslateView,WordBean> implements TranslateView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        presenter.translate("json","auto","code");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected TranslatePresenter createPresenter() {
        return new TranslatePresenter();
    }

    @Override
    public void onSuccess(WordBean data) {
        String result = data.getTranslateResult().get(0).get(0).getTgt();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        System.out.println(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
