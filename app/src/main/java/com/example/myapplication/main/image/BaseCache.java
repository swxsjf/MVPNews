package com.example.myapplication.main.image;

import android.graphics.Bitmap;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 11:43
 * Describe: ${as}
 */
public interface BaseCache {
    void setBitmap(String url, Bitmap bitmap);

    Bitmap getBitmap(String url);
}
