package com.android.base.utils.android;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.base.utils.BaseUtils;

/**
 * @author Ztiany
 * @email 1169654504@qq.com & ztiany3@gmail.com
 * @date 2015-11-03 11:38
 * @description
 * @vsersion
 */
public class ToastUtil {


    /**
     * 显示一个短土司
     *
     * @param msg
     */
    public static void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个长土司
     *
     * @param msg
     */
    public static void showLongToast(String msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }


    private static void showToast(String msg, int time) {
        if (!TextUtils.isEmpty(msg))
            Toast.makeText(getContext(), msg, time).show();
    }

    /**
     * 显示一个短土司
     *
     * @param strId
     */
    public static void showToast(@StringRes int strId) {
        showToast(getString(strId), Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个长土司
     *
     * @param strId
     */
    public static void showLongToast(@StringRes int strId) {
        showToast(getString(strId), Toast.LENGTH_LONG);
    }


    private static String getString(@StringRes int strId) {
        return getContext().getString(strId);
    }


    private static Context getContext() {
        return BaseUtils.getAppContext();
    }

}
