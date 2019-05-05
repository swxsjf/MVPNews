package com.example.myapplication.main.picture;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/29
 * Time: 13:40
 * Describe: ${as}
 */
public interface PictureService {

    @GET("photos/photos_1.json")
    Call<PictureBean> getPictureData();

}
