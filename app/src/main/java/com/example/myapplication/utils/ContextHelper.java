package com.example.myapplication.utils;

import android.content.Context;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/16
 * Time: 16:40
 * Describe: 获取上下文对象的帮助类；<br>
 *  * 引用者需要先通过ContextHelper.setContext(context)方法把上下文传入<br>
 *  * HelperLibrary中的帮助类再通过ContextHelper.getContext()获取上下文；<br>
 *  * 强烈建议在目标项目的Application.onCreate()方法中设置上下文；<br>
 */
public class ContextHelper {
    private static Context context;
    public static void setContext(Context context){
        // 通过用户设置的context获取ApplicationContext，避免内在泄漏；
        ContextHelper.context = context.getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

}
