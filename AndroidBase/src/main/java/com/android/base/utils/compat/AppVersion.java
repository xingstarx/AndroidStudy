package com.android.base.utils.compat;

import android.os.Build;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-16 21:32      <br/>
 * Description：App兼容性的处理
 */
public class AppVersion {

    private AppVersion() {

    }

    public static boolean afterLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean afterM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isKitkat() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT;
    }

    public static boolean afterKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }



    /**
     * @param level minimum API level version that has to support the device
     * @return true when the caller API version is at least level
     */
    public static boolean require(int level) {
        return Build.VERSION.SDK_INT >= level;
    }

}
