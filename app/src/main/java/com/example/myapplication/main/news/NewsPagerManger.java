package com.example.myapplication.main.news;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.main.bean.SubNews;

import java.util.List;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/19
 * Time: 21:01
 * Describe: ${as}
 */
public class NewsPagerManger implements NewsView{
    private ViewGroup container;
    private NewsPresenter presenter;
    private ViewPager viewPager;

    public NewsPagerManger(ViewGroup container) {
        this.container = container;
        presenter = new NewsPresenter();
    }

    public View createPager() {
        Context context = container.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pager = inflater.inflate(R.layout.pager_news, container, false);
        viewPager = pager.findViewById(R.id.news_viewPager);

        presenter.attachView(this);
        return pager;
    }

    public void initData(String url){
        presenter.getTopNews(url);
    }

    @Override
    public void onSuccess(SubNews data) {
        List<SubNews.DataBean.TopnewsBean> topnews = data.getData().getTopnews();
        viewPager.setAdapter(new SubNewsPagerAdapter(topnews));
    }

    @Override
    public void onFailure(String message) {

    }
}
