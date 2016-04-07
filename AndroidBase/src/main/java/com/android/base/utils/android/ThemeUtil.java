package com.android.base.utils.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;

import com.android.base.utils.compat.AppVersion;


/**
 * @author Ztiany
 *         email 1169654504@qq.com & ztiany3@gmail.com
 *         date 2016-03-18 11:38
 *         description
 *         vsersion
 */
public class ThemeUtil {

    public static void recreateActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            activity.recreate();
        } else {
            Intent intent = activity.getIntent();
            intent.setClass(activity, activity.getClass());
            activity.startActivity(intent);
            activity.finish();
            activity.overridePendingTransition(0, 0);
        }
    }

    public static int getThemeAttrColor(@NonNull Context context, @AttrRes int attr) {
        TypedArray a = context.obtainStyledAttributes(null, new int[]{attr});
        try {
            return a.getColor(0, 0);
        } finally {
            a.recycle();
        }
    }


    public static Drawable getThemeAttrDrawable(@NonNull Context context, @AttrRes int attr) {
        TypedArray a = context.obtainStyledAttributes(null, new int[]{attr});
        try {
            return a.getDrawable(0);
        } finally {
            a.recycle();
        }
    }


    public static int getPrimaryDarkColorId(Activity activity) {
        if (AppVersion.afterLollipop()) {
            try {
                TypedArray typedArray = activity.getTheme().obtainStyledAttributes(new int[]{android.R.attr.colorPrimaryDark});
                return typedArray.getResourceId(0, -1);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return -1;
    }

    public static int getPrimaryColorId(Activity activity) {
        if (AppVersion.afterLollipop()) {
            try {
                TypedArray typedArray = activity.getTheme().obtainStyledAttributes(new int[]{android.R.attr.colorPrimary});
                return typedArray.getResourceId(0, -1);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return -1;
    }



}
