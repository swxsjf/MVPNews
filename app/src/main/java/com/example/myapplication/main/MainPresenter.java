package com.example.myapplication.main;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.base.ModelManager;
import com.example.myapplication.main.bean.AvatarBean;
import com.example.myapplication.main.bean.NewsCategory;
import com.example.myapplication.main.callback.MainCallback;
import com.example.myapplication.main.callback.NewsCategoryCallback;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/11
 * Time: 16:51
 * Describe: ${as}
 */
public class MainPresenter extends BasePresenter<MainView> {

    public void upload(String path){
        MainModel model = ModelManager.getInstance().getModel(MainModel.class);
        model.upload(path, new MainCallback() {
            @Override
            public void onSuccess(AvatarBean data) {
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

    public void getNewsCategory(){
        MainModel model = ModelManager.getInstance().getModel(MainModel.class);
        model.getNewsCategory(new NewsCategoryCallback() {
            @Override
            public void onSuccess(NewsCategory data) {
                if (isAttach()){
                    view.onNewsCategory(data);
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
