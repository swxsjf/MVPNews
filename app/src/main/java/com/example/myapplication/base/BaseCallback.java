package com.example.myapplication.base;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/10
 * Time: 15:50
 * Describe: ${as}
 */
public interface BaseCallback<D> {

    void onSuccess(D data);

    void onFailure(String message);

}
