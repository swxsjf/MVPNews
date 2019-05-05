package com.example.myapplication.main.image;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 11:44
 * Describe: ${as}
 */
public class MemoryCache implements BaseCache {

    private static MemoryCache memoryCache = new MemoryCache();

    public static LruCache<String,Bitmap> lruCache;

    private MemoryCache() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        int maxSize = (int) (totalMemory / 8);
        if (lruCache == null){
            lruCache = new LruCache<String,Bitmap>(maxSize){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };
        }
    }

    public static MemoryCache getInstance(){
        return memoryCache;
    }

    @Override
    public void setBitmap(String url, Bitmap bitmap) {
        String fileName = Md5Utils.md5(url);
        lruCache.put(fileName,bitmap);
    }

    @Override
    public Bitmap getBitmap(String url) {
        String fileName = Md5Utils.md5(url);
        Bitmap bitmap = lruCache.get(fileName);
        return bitmap;
    }
}
