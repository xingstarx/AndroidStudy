package com.android.base.utils.android;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

import com.android.base.utils.BaseUtils;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-16 21:36      <br/>
 * Description：资源工具类
 */
public class ResourceUtil {

    private ResourceUtil() {
    }


    public static int getColor(int id, Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return activity.getResources().getColor(id);
        } else {
            return activity.getResources().getColor(id, activity.getTheme());
        }
    }

    public static ColorStateList getColorStateList(int id,Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return activity.getResources().getColorStateList(id);
        } else {
            return activity.getResources().getColorStateList(id, activity.getTheme());
        }
    }


    public static String getString(@StringRes int id) {
        return BaseUtils.getResources().getString(id);
    }

    public static String[] getStringArray(@ArrayRes int id) {
        return BaseUtils.getResources().getStringArray(id);
    }


    public static int[] getIntArray(@ArrayRes int id) {
        return BaseUtils.getResources().getIntArray(id);
    }


    public static Drawable getDrawable(int drawableId, Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return activity.getResources().getDrawable(drawableId);
        } else {
            return activity.getResources().getDrawable(drawableId, activity.getTheme());
        }
    }

}
