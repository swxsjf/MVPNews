package com.example.myapplication.guide;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.main.Main2Activity;
import com.example.myapplication.utils.NewsConstants;
import com.example.myapplication.utils.PreferenceUtils;

public class GuideActivity extends BaseActivity {

    private ViewPager guideViewPager;
    private View redDotView;
    private ConstraintLayout daLayout;
    private float distance;
    private GuidePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guide);

        initView();

        adapter = new GuidePagerAdapter();
        adapter.setGuideButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideActivity.this.startActivity(new Intent(GuideActivity.this, Main2Activity.class));
                PreferenceUtils.putBoolean(NewsConstants.START_GUIDE_ACTIVITY,false);
                GuideActivity.this.finish();
            }
        });
        guideViewPager.setAdapter(adapter);

        daLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                View childAt0 = daLayout.getChildAt(0);
                View childAt1 = daLayout.getChildAt(1);
                distance = childAt1.getX() - childAt0.getX();
                System.out.println("小圆点间距："+distance);
                daLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        guideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

                redDotView.setTranslationX((i+v) * distance);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_guide;
    }

    private void initView() {
        guideViewPager = (ViewPager) findViewById(R.id.guideViewPager);
        redDotView = (View) findViewById(R.id.redDotView);
        daLayout = (ConstraintLayout) findViewById(R.id.daLayout);
    }
}
