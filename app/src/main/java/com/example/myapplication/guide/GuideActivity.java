package com.example.myapplication.guide;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

public class GuideActivity extends BaseActivity {

    private ViewPager guideViewPager;
    private View redDotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
        guideViewPager.setAdapter(new GuidePagerAdapter());
        guideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int pixels = metrics.widthPixels;
                Log.e("aaa","屏幕宽度："+pixels);

                redDotView.setTranslationX((i+v)*67);

                Log.e("aaa","i"+i);
                Log.e("aaa","v"+v);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void initView() {
        guideViewPager = (ViewPager) findViewById(R.id.guideViewPager);
        redDotView = (View) findViewById(R.id.redDotView);
    }
}
