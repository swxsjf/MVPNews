package com.example.myapplication.base;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/10
 * Time: 15:50
 * Describe: ${as}
 */
public class BasePresenter<V extends BaseView> {

    private V view;

    public void attachView(V view){
        this.view = view;
    }

    public void detachView(){
        this.view = null;
    }

}
