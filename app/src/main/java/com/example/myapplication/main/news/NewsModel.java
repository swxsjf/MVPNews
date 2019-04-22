package com.example.myapplication.main.news;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.main.bean.SubNews;
import com.example.myapplication.utils.NewsConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/19
 * Time: 20:52
 * Describe: ${as}
 */
public class NewsModel extends BaseModel {

    public void getTopNews(String url, final NewsCallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsConstants.NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsService service = retrofit.create(NewsService.class);
        Call<SubNews> call = service.getTopNews(url);
        call.enqueue(new Callback<SubNews>() {
            @Override
            public void onResponse(Call<SubNews> call, Response<SubNews> response) {
                SubNews subNews = response.body();
                if (callback != null){
                    if (response.isSuccessful()){
                        callback.onSuccess(subNews);
                    }
                    else {
                        callback.onFailure(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<SubNews> call, Throwable t) {
                if (callback != null){
                    callback.onFailure(t.getMessage());
                }
            }
        });

    }

}
