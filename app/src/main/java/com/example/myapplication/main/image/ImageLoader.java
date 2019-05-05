package com.example.myapplication.main.image;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.myapplication.utils.MyLog;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 11:43
 * Describe: ${as}
 */
public class ImageLoader {
    private static ImageLoader imageLoader = new ImageLoader();
    private MemoryCache memoryCache;
    private DiskCache diskCache;
    private ServerCache serverCache;

    private ImageLoader() {
        memoryCache = MemoryCache.getInstance();
        diskCache = DiskCache.getInstance();
        serverCache = ServerCache.getInstance();
    }

    public static ImageLoader getInstance() {
        return imageLoader;
    }

    public void load(final String imageUrl, final ImageView imageView){
        Bitmap bitmap = memoryCache.getBitmap(imageUrl);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
            MyLog.info("内存缓存", bitmap.getByteCount());
            return;
        }

        bitmap = diskCache.getBitmap(imageUrl);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
            memoryCache.setBitmap(imageUrl,bitmap);
            MyLog.info("本地缓存", bitmap.getByteCount());
            return;
        }

        serverCache.getImageFromServer(imageUrl, new ImageCallback() {
            @Override
            public void getBitmap(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
                MyLog.info("网络缓存", bitmap.getByteCount());
                // 保存到本地
                diskCache.setBitmap(imageUrl,bitmap);
                //添加内存缓存
                memoryCache.setBitmap(imageUrl,bitmap);
            }
        });

    }

}
