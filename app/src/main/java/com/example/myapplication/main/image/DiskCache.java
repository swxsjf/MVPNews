package com.example.myapplication.main.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 11:43
 * Describe: ${as}
 */
public class DiskCache implements BaseCache {
    private static DiskCache diskCache = new DiskCache();

    private DiskCache() {
    }

    public static DiskCache getInstance(){
        return diskCache;
    }

    @Override
    public void setBitmap(String url, Bitmap bitmap) {
        OutputStream outputStream = null;

        try {
            File directory = Environment.getExternalStorageDirectory();
            String fileName = Md5Utils.md5(url);
            File file = new File(directory, fileName);
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Bitmap getBitmap(String url) {
        File directory = Environment.getExternalStorageDirectory();
        String fileName = Md5Utils.md5(url);
        File file = new File(directory, fileName);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

        return bitmap;
    }

    public void savaBitmap(String url, InputStream inputStream){
        OutputStream outputStream = null;
        try {
            File directory = Environment.getExternalStorageDirectory();
            String fileName = Md5Utils.md5(url);
            File imageFile = new File(directory, fileName);
            outputStream = new FileOutputStream(imageFile);
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(bytes))!= -1){
                outputStream.write(bytes,0,length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
