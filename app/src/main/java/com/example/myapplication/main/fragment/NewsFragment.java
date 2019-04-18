package com.example.myapplication.main.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.main.Main2Activity;
import com.example.myapplication.main.bean.NewsCategory;
import com.example.myapplication.main.news.NewsPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.newsViewPager)
    ViewPager newsViewPager;

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Main2Activity activity = (Main2Activity) getActivity();
        List<NewsCategory.DataBean.ChildrenBean> children = activity.getChildren();
        NewsPagerAdapter adapter = new NewsPagerAdapter(children);
        newsViewPager.setAdapter(adapter);
    }
}
