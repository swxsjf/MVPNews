package com.example.myapplication.main;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.main.bean.AvatarBean;
import com.example.myapplication.main.bean.NewsCategory;
import com.example.myapplication.main.callback.MainCallback;
import com.example.myapplication.main.callback.NewsCategoryCallback;
import com.example.myapplication.main.service.MianService;
import com.example.myapplication.utils.NewsConstants;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
public class MainModel extends BaseModel {

    public void upload(String path, final MainCallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sm.ms/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MianService service = retrofit.create(MianService.class);

        MediaType mediaType = MediaType.parse("multipart/form-data");
        MediaType fileType = MediaType.parse("application/otcet-stream");
        RequestBody format = RequestBody.create(mediaType,"json");
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(fileType, file);
        String name = null;
        try {
            name = URLEncoder.encode(file.getName(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MultipartBody.Part smfile = MultipartBody.Part.createFormData("smfile", name, fileBody);
        Call<AvatarBean> call = service.uploadImage(format, smfile);
        call.enqueue(new Callback<AvatarBean>() {
            @Override
            public void onResponse(Call<AvatarBean> call, Response<AvatarBean> response) {
                if (callback != null){
                    return;
                }
                if (response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onFailure(response.message());
                }

            }

            @Override
            public void onFailure(Call<AvatarBean> call, Throwable t) {
                if (callback == null){
                    return;
                }
                callback.onFailure(t.getMessage());
            }
        });

    }

    public void getNewsCategory(final NewsCategoryCallback callback){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NewsConstants.NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MianService service = retrofit.create(MianService.class);
        Call<NewsCategory> call = service.getNewsCategory();
        call.enqueue(new Callback<NewsCategory>() {
            @Override
            public void onResponse(Call<NewsCategory> call, Response<NewsCategory> response) {
                if (callback != null){
                    if (response.isSuccessful()){
                        List<NewsCategory.DataBean> data = response.body().getData();
                        for(int i=0;i<data.size();i++){
                            Log.i("weixing",data.get(i).getTitle());
                        }

                        callback.onSuccess(response.body());
                    }
                    else {
                        callback.onFailure(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsCategory> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(t.getMessage());
                }
            }
        });


    }


}
