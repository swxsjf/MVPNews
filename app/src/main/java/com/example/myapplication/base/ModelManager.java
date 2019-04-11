package com.example.myapplication.base;

import java.lang.reflect.Constructor;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/11
 * Time: 18:53
 * Describe: ${as}
 */
public class ModelManager {
    private static final ModelManager ourInstance = new ModelManager();

    public static ModelManager getInstance() {
        return ourInstance;
    }

    private ModelManager() {
    }

    public <M> M getModel(Class<M> modelClass){
        try {
            Constructor<M> constructor = modelClass.getConstructor();
            M instance = constructor.newInstance();
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
