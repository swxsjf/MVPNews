package com.example.myapplication.guide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/12
 * Time: 19:28
 * Describe: ${as}
 */
public class GuidePagerAdapter extends PagerAdapter {
    private List<Integer> guideResList = new ArrayList<>();

    public GuidePagerAdapter() {
        guideResList.add(R.drawable.guide_1);
        guideResList.add(R.drawable.guide_2);
        guideResList.add(R.drawable.guide_3);
    }

    @Override
    public int getCount() {
        return guideResList.size();
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

        View view = inflater.inflate(R.layout.pager_guide, container,false);
        ImageView imageViewId = view.findViewById(R.id.imageView);
        Button exprienceButton = view.findViewById(R.id.exprienceButton);

        if (guideResList.size()-1 == position){
            exprienceButton.setVisibility(View.VISIBLE);
        }
        else {
            exprienceButton.setVisibility(View.INVISIBLE);
        }

        Integer resId = guideResList.get(position);
        imageViewId.setImageResource(resId);

        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
