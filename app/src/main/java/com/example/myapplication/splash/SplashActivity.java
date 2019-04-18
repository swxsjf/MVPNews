package com.example.myapplication.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.guide.GuideActivity;
import com.example.myapplication.main.Main2Activity;
import com.example.myapplication.utils.NewsConstants;
import com.example.myapplication.utils.PreferenceUtils;

public class SplashActivity extends BaseActivity {

    private ImageView animationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);

        initView();

        startAnimatorSet(animationLayout);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    private void startAnimatorSet(ImageView animationLayout) {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(animationLayout, "ScaleX", 0, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(animationLayout, "ScaleY", 0, 1);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(animationLayout, "Rotation", 0, 1080);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(animationLayout, "Alpha", 0, 1.0f);

        animatorSet.setDuration(3000);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                boolean aBoolean = PreferenceUtils.getBoolean(NewsConstants.START_GUIDE_ACTIVITY, true);

                if (aBoolean){
                    Intent intent = new Intent(context, GuideActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
                else {
                    Intent intent = new Intent(context, Main2Activity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }

            }
        });

        animatorSet.playTogether(scaleX,scaleY,rotation,alpha);
        animatorSet.start();
    }

    private void initView() {
        animationLayout = (ImageView) findViewById(R.id.animationLayout);
    }
}
