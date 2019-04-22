package com.example.myapplication.main.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.main.bean.SubNews;

import java.util.List;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/19
 * Time: 21:02
 * Describe: ${as}
 */
public class SubNewsPagerAdapter extends PagerAdapter {
    private List<SubNews.DataBean.TopnewsBean> topnews;

    public SubNewsPagerAdapter(List<SubNews.DataBean.TopnewsBean> topnews) {
        this.topnews = topnews;
    }

    @Override
    public int getCount() {
        return topnews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pager = inflater.inflate(R.layout.pager_subnews, container, false);
        String imageUrl = topnews.get(position).getTopimage();

        String befImgUrl = imageUrl.substring(0, 7);
        String latImgUrl = imageUrl.substring(15);
        String comImagUrl = befImgUrl+"169.254.179.62"+latImgUrl;
        ImageView imageView = pager.findViewById(R.id.topNewsImageView);
        Glide.with(context).load(comImagUrl).into(imageView);
        container.addView(pager);

        return pager;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(container);
    }
}
