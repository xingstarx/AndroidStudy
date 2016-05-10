package com.android.base.utils.resource;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;


/**
 * @author Ztiany
 *         email 1169654504@qq.com & ztiany3@gmail.com
 *         date 2016-03-18 11:38
 *         description
 *         vsersion
 */
public class ThemeUtil {

    public static int getThemeAttrColor(@NonNull Context context, @AttrRes int attr) {
        TypedArray a = context.obtainStyledAttributes(null, new int[]{attr});
        try {
            return a.getColor(0, 0x000000);
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


}
