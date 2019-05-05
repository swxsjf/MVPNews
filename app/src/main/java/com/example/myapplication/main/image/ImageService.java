package com.example.myapplication.main.image;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 11:44
 * Describe: ${as}
 */
public interface ImageService {

    @GET
    @Streaming
    Call<ResponseBody> getImageFromServer(@Url String url);

}
