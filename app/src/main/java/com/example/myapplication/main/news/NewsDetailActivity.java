package com.example.myapplication.main.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

public class NewsDetailActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_news_detail);

        initView();
        Intent intent = getIntent();
        String newsUrl = intent.getStringExtra("newsUrl");
        String substring = newsUrl.substring(0, 7);
        String substring1 = newsUrl.substring(15);
        String url = substring+"169.254.179.62"+substring1;
        webView.loadUrl(url);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_news_detail;
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
    }
}
