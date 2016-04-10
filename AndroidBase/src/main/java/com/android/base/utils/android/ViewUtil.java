package com.android.base.utils.android;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.android.base.utils.compat.AppVersion;

/**
 * @author Ztiany
 *         email 1169654504@qq.com & ztiany3@gmail.com
 *         date 2016-03-17 11:19
 *         description View相关工具类
 *         vsersion
 */
public class ViewUtil {

    private ViewUtil() {

    }

    private static final int ACTION_VISIBLE = 0x01;
    private static final int ACTION_GONE = 0x02;
    private static final int ACTION_INVISIBLE = 0x03;


    public static void gone(View view1, View view2) {
        view1.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
    }

    public static void gone(View view1, View view2, View view3) {
        view1.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
    }

    public static void gone(View view1, View view2, View view3, View... views) {
        view1.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
        doVisibleAction(ACTION_GONE, views);
    }

    public static void visible(View view1, View view2) {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);
    }

    public static void visible(View view1, View view2, View view3) {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);
        view3.setVisibility(View.VISIBLE);
    }

    public static void visible(View view1, View view2, View view3, View... views) {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);
        view3.setVisibility(View.VISIBLE);
        doVisibleAction(ACTION_VISIBLE, views);
    }


    public static void invisible(View view1, View view2) {
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
    }

    public static void invisible(View view1, View view2, View view3) {
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
    }

    public static void invisible(View view1, View view2, View view3, View... views) {
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        doVisibleAction(ACTION_INVISIBLE, views);
    }


    private static void doVisibleAction(int action, View... views) {
        for (View view : views) {
            if (action == ACTION_GONE) {
                view.setVisibility(View.GONE);
            } else if (action == ACTION_INVISIBLE) {
                view.setVisibility(View.INVISIBLE);
            } else if (action == ACTION_VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

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
