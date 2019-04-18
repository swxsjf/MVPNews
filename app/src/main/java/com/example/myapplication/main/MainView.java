package com.example.myapplication.main;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.main.bean.AvatarBean;
import com.example.myapplication.main.bean.NewsCategory;
import com.example.myapplication.translate.WordBean;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/11
 * Time: 16:51
 * Describe: ${as}
 */
public interface MainView extends BaseView<AvatarBean> {
    void onNewsCategory(NewsCategory category);
}
