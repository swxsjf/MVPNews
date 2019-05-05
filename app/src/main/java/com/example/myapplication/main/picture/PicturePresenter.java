package com.example.myapplication.main.picture;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.base.ModelManager;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/29
 * Time: 13:43
 * Describe: ${as}
 */
public class PicturePresenter extends BasePresenter<PictureView> {

    public void getPictureData(){
        PictureModel model = ModelManager.getInstance().getModel(PictureModel.class);
        model.getPictureData(new PictureCallback() {
            @Override
            public void onSuccess(PictureBean data) {
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
