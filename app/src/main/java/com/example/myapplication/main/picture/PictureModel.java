package com.example.myapplication.main.picture;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.utils.NewsConstants;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/29
 * Time: 13:41
 * Describe: ${as}
 */
public class PictureModel extends BaseModel {

    public void getPictureData(final PictureCallback callback){
        // retryOnConnectionFailure 配置此客户端是否在遇到连接问题时重试
        OkHttpClient client = new OkHttpClient.Builder().retryOnConnectionFailure(true).build();

        Retrofit retrofit = new Retrofit.Builder().client(client)
                .baseUrl(NewsConstants.NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PictureService pictureService = retrofit.create(PictureService.class);

        Call<PictureBean> call = pictureService.getPictureData();

        call.enqueue(new Callback<PictureBean>() {
            @Override
            public void onResponse(Call<PictureBean> call, Response<PictureBean> response) {
                if (callback != null){
                    PictureBean bean = response.body();
                    callback.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Call<PictureBean> call, Throwable t) {
                if (callback != null){
                    callback.onFailure(t.getMessage());
                }
            }
        });


    }

}
