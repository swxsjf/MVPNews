package com.example.myapplication.main.news;

import com.example.myapplication.main.bean.SubNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/19
 * Time: 20:56
 * Describe: ${as}
 */
public interface NewsService {

    @GET
    Call<SubNews> getTopNews(@Url String url);

}
