package com.example.myapplication.translate;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.base.ModelManager;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/11
 * Time: 16:51
 * Describe: ${as}
 */
public class TranslatePresenter extends BasePresenter<TranslateView> {

    public void translate(String doctype, String type, String i){
        TranslateModel model = ModelManager.getInstance().getModel(TranslateModel.class);
        model.translate(doctype, type, i, new TranslateCallback() {
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
