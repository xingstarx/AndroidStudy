package com.android.base.utils.compat;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-16 21:35      <br/>
 * Description：
 */
public class ViewCompat {


    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setElevation(View view, int elevation) {
        if (AppVersion.afterLollipop()) {
            view.setElevation(elevation);
        }
    }


}
