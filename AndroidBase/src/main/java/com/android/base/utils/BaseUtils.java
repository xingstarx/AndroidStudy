package com.android.base.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-19 23:09      <br/>
 * Description：工具类门面
 */
public class BaseUtils {


    private static Context mApplication;

    public static void init(Context application) {
        if (mApplication == null) {
            mApplication = application;
        }
    }

    public static Context getAppContext() {
        return mApplication;
    }


    public static Resources getResources() {
        return BaseUtils.getAppContext().getResources();
    }

    public static Resources.Theme getTheme() {
        return BaseUtils.getAppContext().getTheme();
    }

    public static AssetManager getAssets() {
        return BaseUtils.getAppContext().getAssets();
    }

    public static Configuration getConfiguration() {
        return BaseUtils.getResources().getConfiguration();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return BaseUtils.getResources().getDisplayMetrics();
    }

}
