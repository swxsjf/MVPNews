package com.example.myapplication.main.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;

import com.example.myapplication.utils.ContextHelper;
import com.example.myapplication.utils.NewsConstants;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 11:44
 * Describe: ${as}
 */
public class ServerCache implements BaseCache{
    private static ServerCache serverCache = new ServerCache();

    private ServerCache() {
    }

    public static ServerCache getInstance(){
        return serverCache;
    }

    @Override
    public void setBitmap(String url, Bitmap bitmap) {
        throw new IllegalStateException(this.getClass().getSimpleName()+ "不支持该方法！");
    }

    @Override
    public Bitmap getBitmap(String url) {
        throw new IllegalStateException(this.getClass().getSimpleName() + "不支持该方法！");
    }

    public void getImageFromServer(final String imageUrl, final ImageCallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsConstants.NEWS_BASE_URL)
                .build();

        ImageService service = retrofit.create(ImageService.class);
        Call<ResponseBody> call = service.getImageFromServer(imageUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                Executors.newCachedThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        InputStream inputStream = response.body().byteStream();
                        DiskCache diskCache = DiskCache.getInstance();
                        diskCache.savaBitmap(imageUrl,inputStream);

                        // 计算图片宽高
                        File directory = Environment.getExternalStorageDirectory();
                        String fileName = Md5Utils.md5(imageUrl);
                        File file = new File(directory, fileName);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(file.getAbsolutePath(),options);
                        int outWidth = options.outWidth;
                        int outHeight = options.outHeight;

                        //计算手机屏幕的宽高
                        Context context = ContextHelper.getContext();
                        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                        int screenWidth = metrics.widthPixels;
                        int screenHeight = metrics.heightPixels;

                        //计算图片压缩比例
                        float ratioWidth = outWidth * 1.0f / screenWidth;
                        float ratioHeight = outHeight * 1.0f / screenHeight;
                        float ratio = Math.max(ratioWidth, ratioHeight);

                        //对图片进行二次采样
                        options.inJustDecodeBounds = false;
                        options.inSampleSize = (int) Math.ceil(ratio);
                        final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.getBitmap(bitmap);
                            }
                        });

                    }
                });
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}
