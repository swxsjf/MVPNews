package com.example.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/23
 * Time: 14:06
 * Describe: ${as}
 */
public class WrapHeightViewPager extends ViewPager {
    public WrapHeightViewPager(@NonNull Context context) {
        this(context,null);
    }

    public WrapHeightViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int viewPagerHeight = 0;
        for (int i = 0; i < this.getChildCount(); i++){
            View child = this.getChildAt(i);
            int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            child.measure(widthMeasureSpec,newHeightMeasureSpec);
            int childMeasuredHeight = child.getMeasuredHeight();
            if (viewPagerHeight < childMeasuredHeight){
                viewPagerHeight = childMeasuredHeight;
            }
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(viewPagerHeight,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
