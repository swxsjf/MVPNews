package com.example.myapplication.main.news;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.base.ModelManager;
import com.example.myapplication.main.bean.SubNews;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/19
 * Time: 21:00
 * Describe: ${as}
 */
public class NewsPresenter extends BasePresenter<NewsView> {

    public void getTopNews(String url){
        NewsModel model = ModelManager.getInstance().getModel(NewsModel.class);
        model.getTopNews(url, new NewsCallback() {
            @Override
            public void onSuccess(SubNews data) {
                if (isAttach()){
                    view.onSuccess(data);
                }
            }

            @Override
            public void onFailure(String message) {
                if (isAttach()){
                    view.onFailure(message);
                }
            }
        });
    }

}
