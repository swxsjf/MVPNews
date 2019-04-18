package com.example.myapplication.main.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.main.bean.NewsCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/18
 * Time: 20:53
 * Describe: ${as}
 */
public class NewsPagerAdapter extends PagerAdapter {
    List<NewsCategory.DataBean.ChildrenBean> children  = new ArrayList<>();

    public NewsPagerAdapter(List<NewsCategory.DataBean.ChildrenBean> children) {
        this.children = children;
    }

    @Override
    public int getCount() {
        return children.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return children.get(position).getTitle();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pager = inflater.inflate(R.layout.pager_news, container, false);
        container.addView(pager);

        return pager;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
