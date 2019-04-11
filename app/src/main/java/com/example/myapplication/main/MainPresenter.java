package com.example.myapplication.main;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.base.ModelManager;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/11
 * Time: 16:51
 * Describe: ${as}
 */
public class MainPresenter extends BasePresenter<MainView> {

    public void translate(String doctype, String type, String i){
        MainModel model = ModelManager.getInstance().getModel(MainModel.class);
        model.translate(doctype, type, i, new MainCallback() {
            @Override
            public void onSuccess(WordBean data) {
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
