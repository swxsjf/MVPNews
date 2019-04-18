package com.example.myapplication.translate;

import com.example.myapplication.base.BaseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/11
 * Time: 16:49
 * Describe: ${as}
 */
public class TranslateModel extends BaseModel {

    public void translate(String doctype, String type, String i, final TranslateCallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TranslateService service = retrofit.create(TranslateService.class);
        Call<WordBean> call = service.translate(doctype, type, i);
        call.enqueue(new Callback<WordBean>() {
            @Override
            public void onResponse(Call<WordBean> call, Response<WordBean> response) {
                if (callback != null){
                    if (response.isSuccessful()){
                        WordBean bean = response.body();
                        callback.onSuccess(bean);
                    }
                    else {
                        callback.onFailure(response.message());
                    }
                }
                else {
                    throw new NullPointerException("callback can not be null");
                }
            }

            @Override
            public void onFailure(Call<WordBean> call, Throwable t) {

                if (callback!= null){
                    callback.onFailure(t.getMessage());
                }
                else {
                    throw new NullPointerException("callback can not be null");
                }
            }
        });
    }
}
